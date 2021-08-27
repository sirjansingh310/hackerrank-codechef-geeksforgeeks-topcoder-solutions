// https://leetcode.com/explore/challenge/card/august-leetcoding-challenge-2021/615/week-3-august-15th-august-21st/3903/

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
    private final Map<TreeNode, Integer> subtreeSum = new HashMap<>();
    private static final int MOD = 1000000007;
    private long maximumDifference = -1L;
    private int totalSum;
    
    private int findSubtreeSum(TreeNode node) {
        if (node == null) {
            return 0;
        }
          
        int fullSum = node.val + findSubtreeSum(node.left) + findSubtreeSum(node.right);
        subtreeSum.put(node, fullSum);
        return fullSum;
    }
    
    private void findMaximumDifference(TreeNode node) {
        if (node == null) {
            return;
        }
                
        if (node.left != null) {
            int rootAfterCut = totalSum - subtreeSum.get(node.left).intValue();
            long product = (long)rootAfterCut * subtreeSum.get(node.left).intValue();
            maximumDifference = Math.max(maximumDifference, product);
            findMaximumDifference(node.left);
        }
        
        if (node.right != null) {
            int rootAfterCut = totalSum - subtreeSum.get(node.right).intValue();
            long product = (long)rootAfterCut * subtreeSum.get(node.right).intValue();
            maximumDifference = Math.max(maximumDifference, product);
            findMaximumDifference(node.right);
        }
    }
    public int maxProduct(TreeNode root) {
        findSubtreeSum(root);
        totalSum = subtreeSum.get(root).intValue();
        findMaximumDifference(root);
        return (int)(maximumDifference % MOD);
    }

}
