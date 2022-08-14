// https://leetcode.com/problems/kth-smallest-element-in-a-bst/
//O(N) Time, O(1) Space (ignoring recursion internal space)
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
    private int visitIndex = 1;// 1 based
    private int result = -1;
    
    public int kthSmallest(TreeNode root, int k) {
        dfs(root, k);
        return result;
    }
    
    private void dfs(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        dfs(root.left, k);
        if (visitIndex == k) {
            result = root.val;
        }
        visitIndex++;
        dfs(root.right, k);
    }
}
