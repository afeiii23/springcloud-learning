package letcode;


import javax.swing.tree.TreeNode;
import java.util.ArrayList;

/**
 * 给你二叉搜索树的根节点 root ，该树中的两个节点被错误地交换。请在不改变其结构的情况下，恢复这棵树。
 * <p>
 * 进阶：使用 O(n) 空间复杂度的解法很容易实现。你能想出一个只使用常数空间的解决方案吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/recover-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Package com.curry.warrior.letcode
 * @author: xule
 * @date: 2021/3/11 17:11
 */
public class Le99 {

    private TreeNode falseOne;
    private TreeNode falseTwo;

    public void recoverTree(TreeNode root) {
        new ArrayList<TreeNode>();
        myRecoverTree(root);
    }

    private void inOrderListNode(TreeNode root) {

    }

    private void myRecoverTree(TreeNode root) {
        if (root == null) {
            return;
        }
//
//        myRecoverTree(root.left);
//
//        myRecoverTree(root.right);
//
//        if (root.left != null && root.left.val > root.val) {
//
////            int temp = root.left.val;
////            root.left.val= root.val;
////            root.val = temp;
//        }
//
//        if (root.right != null && root.right.val < root.val) {
//            int temp = root.right.val;
//            root.right.val= root.val;
//            root.val = temp;
//        }



    }
//    private TreeNode maxTwoNode(TreeNode left, TreeNode right) {
//        if (left == null) {
//            return right;
//        }
//        if (right == null) {
//            return left;
//        }
//        return left.val > right.val ? left : right;
//    }

}



