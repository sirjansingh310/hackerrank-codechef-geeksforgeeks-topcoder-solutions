// https://leetcode.com/explore/challenge/card/july-leetcoding-challenge-2021/611/week-4-july-22nd-july-28th/3824/
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
    private boolean prune(TreeNode root) {
        if (root == null) {
            return false;
        }
        
        boolean leftHasOne = prune(root.left);
        if (!leftHasOne) {
            root.left = null;
        }
        boolean rightHasOne = prune(root.right);
        if (!rightHasOne) {
            root.right = null;
        }
        
        return root.val == 1 || leftHasOne || rightHasOne;
    }
    
    
    public TreeNode pruneTree(TreeNode root) {
        boolean hasOne = prune(root);
        return hasOne ?  root : null;
    }
}
