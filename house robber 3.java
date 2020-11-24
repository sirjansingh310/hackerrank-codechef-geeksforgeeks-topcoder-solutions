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
//https://leetcode.com/problems/house-robber-iii/
    Map<TreeNode, Integer> considerMemo = new HashMap<>();
    Map<TreeNode, Integer> notConsiderMemo = new HashMap<>();
    
    private int bestProfit(TreeNode root) {
        if(root == null) {
            return 0;
        }
        
        if(considerMemo.containsKey(root)) { // best profit already computed
            return Math.max(considerMemo.get(root), notConsiderMemo.get(root));
        }
        
        int consider = root.val;
        if(root.left != null) {
            consider += bestProfit(root.left.left) + bestProfit(root.left.right);
        }
        if(root.right != null) {
            consider += bestProfit(root.right.left) + bestProfit(root.right.right);
        }
        considerMemo.put(root, consider);
        
        int notConsider = bestProfit(root.left) + bestProfit(root.right);
        notConsiderMemo.put(root, notConsider);
        
        return Math.max(consider, notConsider);
    }
    
    public int rob(TreeNode root) {
        return bestProfit(root);
    }
}
