package disconnection;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class Remember {
    /**
     * 二叉树深度搜索遍历
     */
//    public void DFS(TreeNode root){
//        if(root == null){
//            return;
//        }
//        if (root.left != null){
//            DFS(root.left);
//        }
//        if (root.right != null){
//            DFS(root.right);
//        }
//        System.out.println(root.val);
//    }

    private Map<Integer, Integer> inOrderIndexMap;

    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x){
            val = x;
        }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        inOrderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inOrderIndexMap.put(inorder[i], i);
        }

        return buildTree(preorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode buildTree(int[] preorder, int preLeft, int preRight, int inLeft, int inRight) {

        if (preLeft > preRight || inLeft > inRight) {
            return null;
        }

        int rootValue = preorder[preLeft];
        TreeNode root = new TreeNode(rootValue);

        int pIndex = inOrderIndexMap.get(rootValue);

        root.left = buildTree(preorder, preLeft + 1, preLeft + pIndex - inLeft, inLeft, pIndex - 1);
        root.right = buildTree(preorder, preLeft + pIndex - inLeft + 1, preRight, pIndex + 1, inRight);

        return root;
    }

    @Test
    public void test(){
        int[] first = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        TreeNode treeNode = buildTree(first, inorder);
        System.out.println(treeNode.left.left);
    }
}
