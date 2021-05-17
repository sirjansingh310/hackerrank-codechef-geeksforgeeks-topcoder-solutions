//https://leetcode.com/explore/challenge/card/may-leetcoding-challenge-2021/599/week-2-may-8th-may-14th/3742/
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
    public void flatten(TreeNode root) {
        flattenTree(root);
    }
    
    private TreeNode flattenTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode leftLinkedListHead = flattenTree(root.left);
        TreeNode rightLinkedListHead= flattenTree(root.right);
        
        if (leftLinkedListHead == null) {
            root.right = rightLinkedListHead;
        } else {
            root.right = leftLinkedListHead;
            TreeNode leftLinkedListTail = getLinkedListTail(leftLinkedListHead);
            leftLinkedListTail.right = rightLinkedListHead;
        }
        root.left = null;
        return root;
    }
    
    private TreeNode getLinkedListTail(TreeNode root) {
        TreeNode tail = root;
        
        while (root != null) {
            tail = root;
            root = root.right;
        }
        return tail;
    }
}
