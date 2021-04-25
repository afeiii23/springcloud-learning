package letcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 前序遍历 preorder =[3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Package com.curry.warrior.letcode
 * @author: xule
 * @date: 2021/3/11 16:06
 */
public class Le105 {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
//        Integer leftNum = inMap.get(preorder[0]);
//
//        Integer rootIndexIn = inMap.get(preorder[0]);

        return buildTree1(preorder, 0, preorder.length - 1, inorder, 0, preorder.length - 1 , inMap);
    }

    private TreeNode buildTree1(int[] preorder, int preStart, Integer preEnd, int[] inorder, Integer inStart, Integer inEnd, Map<Integer, Integer> inMap) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        Integer rootInIndex = inMap.get(root.val);
        int leftNum = rootInIndex - inStart;
        root.left = buildTree1(preorder, preStart + 1, preStart + leftNum, inorder, inStart, rootInIndex - 1, inMap);
        root.right = buildTree1(preorder, preStart + leftNum + 1, preEnd, inorder, rootInIndex + 1, inEnd, inMap);
        return root;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
