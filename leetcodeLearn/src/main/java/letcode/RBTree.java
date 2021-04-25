package letcode;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Package com.curry.warrior.letcode
 * @author: xule
 * @date: 2021/3/2 16:46
 */
public class RBTree<T extends Comparable<T>> {

    /**
     * 根节点
     */
    private RBTreeNode<T> root;

    private AtomicLong size = new AtomicLong(0);

    public RBTree() {
    }

    public long getSize() {
        return size.get();
    }

    private RBTreeNode<T> getRoot() {
        return root;
    }

    public T find(T value){
        RBTreeNode<T> dataRoot = getRoot();
        while(dataRoot!=null){
            int cmp = dataRoot.getValue().compareTo(value);
            if(cmp<0){
                dataRoot = dataRoot.getRight();
            }else if(cmp>0){
                dataRoot = dataRoot.getLeft();
            }else{
                return dataRoot.getValue();
            }
        }
        return null;
    }


    /**
     * 添加节点
     * @param value
     * @return
     */
    public T addNode(T value) {
        RBTreeNode<T> node = new RBTreeNode<>(value);
        return addNode(node);
    }

    /**
     * 删除操作
     * @param value
     * @return
     */
    private T remove(T value) {
        RBTreeNode<T> dataRoot = getRoot();
        RBTreeNode<T> parent = null;

        while (dataRoot!= null) {
            int i = dataRoot.getValue().compareTo(value);
            if (i > 0) {
                parent = dataRoot;
                dataRoot = dataRoot.getLeft();

            }else if (i < 0) {
                parent = dataRoot;
                dataRoot = dataRoot.getRight();
            }else {
                // find it
                if (dataRoot.getRight() != null) {
                    // 右子树不为空需要把右子树的最小值取来
                    RBTreeNode<T> min = removeMin(dataRoot.getRight());
                    // x用于修复颜色平衡
                    RBTreeNode<T> x = min.getRight() == null ? min.getParent() : min.getRight();
                    boolean isParent = min.getRight() == null;

                    // 填补到要移除的节点去
                    min.setLeft(dataRoot.getLeft());
                    setParent(dataRoot.getLeft(), min);
                    if (parent == null) {
                        // 说明是根节点
                        root = min;
                    }else {
                        if (parent.getLeft() == dataRoot) {
                            parent.setLeft(min);
                        }else {
                            parent.setRight(min);
                        }
                    }
                    setParent(min, parent);

                    // 最小节点的颜色
                    boolean curMinIsBlack = min.isBlack();
                    // 最小节点补充到要移除的节点
                    min.setRed(dataRoot.isRed());
                    if (min != dataRoot.getRight()) {
                        // 如果最小节点不为移除节点的右孩子  说明存在右子树 最小节点需继承其右节点
                        min.setRight(dataRoot.getRight());
                        setParent(dataRoot.getRight(), min);
                    }
                    // 移除黑色节点会影响平衡性， 需要修复颜色
                    if (curMinIsBlack) {
                        if (min != dataRoot.getRight()) {
                            // 这个时候用x去修复
                            fixRemove(x, isParent);
                        }else if (min.getRight() != null) {
                            fixRemove(min.getRight(), false);
                        }else {
                            fixRemove(min, true);
                        }
                    }
                }else {
                    // 不存在右子树 直接让左孩子继承就好
                    setParent(dataRoot.getLeft(), parent);
                    if (parent.getLeft() == dataRoot) {
                        parent.setLeft(dataRoot.getLeft());
                    }else {
                        parent.setRight(dataRoot.getRight());
                    }
                    if (dataRoot.isBlack() && root != null) {
                        // 需移除的节点为黑色且树不为空 需要修复颜色
                        RBTreeNode<T> x = dataRoot.getLeft() == null ? parent : dataRoot.getLeft();
                        boolean isParent = dataRoot.getLeft() == null;
                        fixRemove(x, isParent);
                    }
                }
                // 移除当前节点
                setParent(dataRoot, null);
                dataRoot.setLeft(null);
                dataRoot.setRight(null);
                if (root != null) {
                    root.makeBlack();
                    root.setParent(null);
                }
                size.decrementAndGet();
                return dataRoot.getValue();
            }
        }
        return null;
    }

    /**
     *
     * @param node 移除元素的父节点或者替补节点
     * @param isParent 是否为移除元素的父节点
     */
    private void fixRemove(RBTreeNode<T> node, boolean isParent) {
        RBTreeNode<T> cur = isParent ? null : node;
        boolean isRed = !isParent && node.isRed();
        RBTreeNode<T> parent = isParent ? node : node.getParent();

        while (!isRed && !isRoot(cur)) {
            RBTreeNode<T> sibling = getSibling(cur, parent);

            // 兄弟节点是左节点
            boolean isLeft = parent.getLeft() == sibling;
            if (sibling.isRed()) {
                // case1 待删除的兄弟节点是红色的
                parent.makeRed();
                sibling.makeBlack();
                if (!isLeft) {
                    // 兄弟节点在右边
                    leftRotate(parent);
                }else {
                    // 兄弟节点在左边
                    rightRotate(parent);
                }
            }else if (isBlack(sibling.getRight()) && isBlack(sibling.getLeft())) {
                // case 2 兄弟节点为黑色 且兄弟节点的子节点都为黑色
                sibling.makeRed();
                cur = parent;
                isRed = cur.isRed();
                parent = parent.getParent();
            }else if (!isLeft && !isBlack(sibling.getLeft()) && isBlack(sibling.getRight())) {
                // case 3 兄弟节点在右边 兄弟节点的左节点为红右节点为黑
                sibling.makeRed();
                sibling.getLeft().makeBlack();
                rightRotate(sibling);
            }else if (isLeft && isBlack(sibling.getLeft()) && !isBlack(sibling.getRight())) {
                // case 3 兄弟节点在左边 兄弟节点的左节点为黑右节点为红
                sibling.makeRed();
                sibling.getRight().makeBlack();
                leftRotate(sibling);
            }else if (!isLeft && !isBlack(sibling.getRight())) {
                // case4 兄弟节点在右边 兄弟节点的右子节点为红色
                sibling.setRed(parent.isRed());
                parent.makeBlack();
                sibling.getRight().makeBlack();
                leftRotate(parent);
                cur = root;
            }else if (isLeft && !isBlack(sibling.getLeft())) {
                // case4 兄弟节点在左边 兄弟节点的左子节点为红色
                sibling.setRed(parent.isRed());
                parent.makeBlack();
                sibling.getLeft().makeBlack();
                rightRotate(parent);
                cur = root;
            }
        }
        if (isRed) {
            // 原移除节点是黑色 走到这里说明补上来的节点是红色的 涂黑就好了
            cur.makeBlack();
        }
        if (root != null) {
            root.setRed(false);
            root.setParent(null);
        }
    }

    /**
     * 取该节点下最小的节点，并把该节点从原来位置解除
     * @param node
     * @return
     */
    private RBTreeNode<T> removeMin(RBTreeNode<T> node) {
        RBTreeNode<T> parent = node;
        while (node != null && node.getLeft() != null) {
            parent = node;
            node = node.getLeft();
        }
        if (node == parent) {
            // 说明当前节点无左子树，当前就是最小
            return node;
        }
        parent.setLeft(node.getRight());
        setParent(node, parent);
        return node;
    }

    private T addNode(RBTreeNode<T> node) {
        node.setLeft(null);
        node.setRight(null);
        node.setRed(true);
        if (root == null) {
            // 根节点需为黑色
            node.makeBlack();
            root = node;
            size.incrementAndGet();
            return node.getValue();
        }
        RBTreeNode<T> parentNode = findParentNode(node);
        int cmp = parentNode.getValue().compareTo(node.getValue());
        if (cmp == 0) {
            System.out.println("当前值" + node.getValue() + "已存在,将其覆盖");
            // 覆盖
            parentNode.setValue(node.getValue());
            return node.getValue();
        }
        setParent(node, parentNode);
        if (cmp > 0) {
            parentNode.setLeft(node);
        } else {
            parentNode.setRight(node);
        }
        // 插入修复
        fixInsert(node);
        size.incrementAndGet();
        return node.getValue();
    }

    /**
     * 插入修复
     * @param node
     */
    private void fixInsert(RBTreeNode<T> node) {
        RBTreeNode<T> parent = node.getParent();
        while (parent != null && parent.isRed()) {
            // 获取叔叔节点
            RBTreeNode<T> uncle = getUncle(node);
            if (uncle == null || uncle.isBlack()) {
                // 父亲为红色 叔叔为黑色或不存在

                RBTreeNode<T> grandfather = parent.getParent();

                if (parent == grandfather.getLeft()) {
                    // 父节点为左孩子
                    boolean isRight = node == parent.getRight();
                    // 当前节点为右孩子
                    if (isRight) {
                        // case3 "<" 这种情况 需要转换为case2
                        leftRotate(parent);
                    }
                    // 此时为case2的"/" 直接 对祖父右旋
                    rightRotate(grandfather);

                    if (isRight) {
                        // 说明是从case3 左旋过来的 父节点置为黑色 此时的父节点其实是当前节点了
                        node.makeBlack();
                        // 结束循环
                        parent = null;
                    }else {
                        // 直接从"/" 过来的 此时的父节点就是父节点
                        parent.makeBlack();
                    }
                    grandfather.makeRed();
                }else {
                    // 父节点为右孩子
                    boolean isLeft = node == parent.getLeft();
                    // 当前节点为右孩子
                    if (isLeft) {
                        // case3 ">" 这种情况 需要转换为case2
                        rightRotate(parent);
                    }
                    // 此时为case2的"\" 直接 对祖父右旋
                    leftRotate(grandfather);
                    if (isLeft) {
                        // 说明是从case3 左旋过来的 父节点置为黑色 此时的父节点其实是当前节点了
                        node.makeBlack();
                        // 结束循环
                        parent = null;
                    }else {
                        // 直接从"/" 过来的 此时的父节点就是父节点
                        parent.makeBlack();
                    }
                    grandfather.makeRed();
                }
            }else {
                //  case 1 叔叔节点是红色
                parent.makeBlack();
                uncle.makeBlack();
                RBTreeNode<T> grandfather = parent.getParent();
                grandfather.makeRed();
                node = grandfather;
                parent = grandfather.getParent();
            }
        }
        getRoot().makeBlack();
        getRoot().setParent(null);
    }

    /**
     * 查找父节点，如果父节点的值等于当前就返回父节点的值
     * @param node
     * @return
     */
    private RBTreeNode<T> findParentNode(RBTreeNode<T> node) {
        RBTreeNode<T> dataRoot = getRoot();
        RBTreeNode<T> child = dataRoot;
        while (child != null) {
            int i = child.getValue().compareTo(node.getValue());
            if (i == 0) {
                return child;
            }
            dataRoot = child;
            if (i > 0) {
                child = child.getLeft();
            }
            if (i <0) {
                child = child.getRight();
            }
        }
        return dataRoot;
    }

    /**
     * 左旋
     *
     * @param node
     */
    private void leftRotate(RBTreeNode<T> node) {
        RBTreeNode<T> right = node.getRight();
        if (right == null) {
            // 左旋 右孩子不能为空
            throw new IllegalStateException("right can not be null");
        }
        RBTreeNode<T> parent = node.getParent();
        // 右孩子的左子树变为当前节点的右子树
        node.setRight(right.getLeft());
        setParent(right.getLeft(), node);
        // 右孩子的左孩子变为当前节点
        right.setLeft(node);
        setParent(node, right);

        if (parent != null) {
            if (parent.getLeft() == node) {
                parent.setLeft(right);
            } else {
                parent.setRight(right);
            }
            setParent(right, parent);
        } else {
            root = right;
            setParent(right, null);
        }
    }

    /**
     * 右旋
     *
     * @param node
     */
    private void rightRotate(RBTreeNode<T> node) {
        RBTreeNode<T> left = node.getLeft();
        if (left == null) {
            // 右旋 右孩子不能为空
            throw new IllegalStateException("right can not be null");
        }
        RBTreeNode<T> parent = node.getParent();
        // 左孩子的右子树变为当前节点的做子树
        node.setLeft(left.getRight());
        setParent(left.getRight(), node);
        // 左孩子的右孩子变为当前节点
        left.setRight(node);
        setParent(node, left);

        if (parent != null) {
            if (parent.getLeft() == node) {
                parent.setLeft(left);
            } else {
                parent.setRight(left);
            }
            setParent(left, parent);
        } else {
            root = left;
            setParent(left, null);
        }
    }

    /**
     * 指定父节点
     * @param node
     * @param parent
     */
    private void setParent(RBTreeNode<T> node, RBTreeNode<T> parent) {
        if (node != null) {
            node.setParent(parent);
        }
    }

    /**
     * 获取叔叔节点
     * @param node
     * @return
     */
    private RBTreeNode<T> getUncle(RBTreeNode<T> node) {
        RBTreeNode<T> parent = node.getParent();
        RBTreeNode<T> grandfather = parent.getParent();
        if (grandfather == null) {
            return null;
        }
        if (parent == grandfather.getRight()) {
            return grandfather.getLeft();
        }else {
            return grandfather.getRight();
        }
    }

    /**
     * 获取兄弟节点
     * @param node
     * @param parent
     * @return
     */
    private RBTreeNode<T> getSibling(RBTreeNode<T> node, RBTreeNode<T> parent) {
        parent = node == null ? parent : node.getParent();
        if (node == null) {
            return parent.getLeft() == null ? parent.getRight() : parent.getLeft();
        }
        if (node == parent.getLeft()) {
            return parent.getRight();
        }else {
            return parent.getLeft();
        }
    }

    private boolean isBlack(RBTreeNode<T> node) {
        return node == null || node.isBlack();
    }

    private boolean isRoot(RBTreeNode<T> node) {
        return root == node;
    }

    /**
     * 打印树
     * @param root
     */
    public void printTree(RBTreeNode<T> root){
        LinkedList<RBTreeNode<T>> queue =new LinkedList<RBTreeNode<T>>();
        LinkedList<RBTreeNode<T>> queue2 =new LinkedList<RBTreeNode<T>>();
        if(root==null){
            return ;
        }
        queue.add(root);
        boolean firstQueue = true;

        while(!queue.isEmpty() || !queue2.isEmpty()){
            LinkedList<RBTreeNode<T>> q = firstQueue ? queue : queue2;
            RBTreeNode<T> n = q.poll();

            if(n!=null){
                String pos = n.getParent()==null ? "" : ( n == n.getParent().getLeft()
                        ? " LE" : " RI");
                String pstr = n.getParent()==null ? "" : n.getParent().toString();
                String cstr = n.isRed()?"R":"B";
                cstr = n.getParent()==null ? cstr : cstr+" ";
                System.out.print(n+"("+(cstr)+pstr+(pos)+")"+"\t");
                if(n.getLeft()!=null){
                    (firstQueue ? queue2 : queue).add(n.getLeft());
                }
                if(n.getRight()!=null){
                    (firstQueue ? queue2 : queue).add(n.getRight());
                }
            }else{
                System.out.println();
                firstQueue = !firstQueue;
            }
        }
    }



    public static void main(String[] args) {
        RBTree<String> bst = new RBTree<String>();
        bst.addNode("d");
        bst.addNode("d");
        bst.addNode("c");
        bst.addNode("c");
        bst.addNode("b");
        bst.addNode("f");

        bst.addNode("a");
        bst.addNode("e");

        bst.addNode("g");
        bst.addNode("h");


        bst.remove("e");

        bst.printTree(bst.getRoot());
    }



}


//红黑树节点
class RBTreeNode<T extends Comparable<T>> {

    /**
     * 节点值
     */
    private T value;

    private RBTreeNode<T> parent;

    private RBTreeNode<T> right;

    private RBTreeNode<T> left;


    /**
     * 是否为红色节点
     */
    private boolean red;


    public RBTreeNode() {
    }

    public RBTreeNode(T value) {
        this.value = value;
    }

    public RBTreeNode(T value, boolean isRed) {
        this.value = value;
        this.red = isRed;
    }

    public T getValue() {
        return value;
    }

    void setValue(T value) {
        this.value = value;
    }

    RBTreeNode<T> getLeft() {
        return left;
    }

    void setLeft(RBTreeNode<T> left) {
        this.left = left;
    }

    RBTreeNode<T> getRight() {
        return right;
    }

    void setRight(RBTreeNode<T> right) {
        this.right = right;
    }

    RBTreeNode<T> getParent() {
        return parent;
    }

    void setParent(RBTreeNode<T> parent) {
        this.parent = parent;
    }

    boolean isRed() {
        return red;
    }

    boolean isBlack() {
        return !red;
    }

    /**
     * 是否为叶子节点
     **/
    boolean isLeaf() {
        return left == null && right == null;
    }

    void setRed(boolean red) {
        this.red = red;
    }

    void makeRed() {
        this.red = true;
    }

    void makeBlack() {
        this.red = false;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}