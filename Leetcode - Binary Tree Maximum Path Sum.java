/**
 * Definition for a binary tree node.
//  https://leetcode.com/problems/binary-tree-maximum-path-sum/
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
    private int maxSum = -10000;
    
    public int maxPathSum(TreeNode root) {
        maxPathSumHelper(root);
        return maxSum;
    }
    
    private int maxPathSumHelper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftSum = root.val + maxPathSumHelper(root.left);
        int rightSum = root.val + maxPathSumHelper(root.right);
        int totalSum = leftSum + rightSum - root.val;
        // LocalSum considers a path from left -> root -> right(totalSum), which we consider for maxSum
        // we don't return this sum from left -> root -> right to parent recur call because it means we are traversing a node twice there. Ex we came from root -> root.left. Now we can not do deep recursion of root.left, come back to root.left and explore root.left.right. This is visiting a node twice.
        // only expore left or right can be returned as it is visit node at once.
        int localMaxSum = Math.max(root.val, Math.max(totalSum, Math.max(leftSum, rightSum)));
        maxSum = Math.max(maxSum, localMaxSum);
      
        // return best path from root to either left or right, visiting each node once.
        return Math.max(root.val, Math.max(leftSum, rightSum));
    }
}
