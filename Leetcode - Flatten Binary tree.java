//https://leetcode.com/problems/flatten-binary-tree-to-linked-list/submissions/

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
    private TreeNode findDeepest(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode temp = root;
        while (temp.right != null) {
            temp = temp.right;
        }
        
        return temp;
    }
    public void flatten(TreeNode root) {
        // in place flatten
        if (root == null) {
            return;
        }
        flatten(root.left);
        flatten(root.right);
        
        TreeNode leftTree = root.left;
        root.left = null;
        TreeNode rightTree = root.right;
        
        
        if (leftTree != null) {
            root.right = leftTree;
            TreeNode deepest = findDeepest(leftTree);
            if (deepest != null) {
                deepest.right = rightTree;
            }
        }
        
        
    }
}
