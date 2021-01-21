/// https://leetcode.com/problems/recover-binary-search-tree/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private TreeNode invalid1, invalid2, prev;
    
    private void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        if (prev != null && prev.val > root.val) { // invalid case found
            invalid1 = invalid1 == null ? prev : invalid1;
            invalid2 = root;
        }
        prev = root;
        inorder(root.right);
    }
    
    public void recoverTree(TreeNode root) {
        inorder(root);
        try {
            int temp = invalid1.val;
            invalid1.val = invalid2.val;
            invalid2.val = temp;
        } catch (NullPointerException e) {
            System.out.println("no invalid nodes found");
            return;
        }
        
    }
}
