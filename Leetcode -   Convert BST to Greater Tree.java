//https://leetcode.com/explore/challenge/card/february-leetcoding-challenge-2021/585/week-2-february-8th-february-14th/3634/

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
    private void inorder(final List<TreeNode> allNodes, final TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(allNodes, root.left);
        allNodes.add(root);
        inorder(allNodes, root.right);
    }
    
    public TreeNode convertBST(TreeNode root) {
        
        List<TreeNode> allNodes = new ArrayList<>();
        inorder(allNodes, root);
        
        int totalSum = 0, valueTillNow = 0;
        
        for (TreeNode node : allNodes) {
            totalSum += node.val;
        }

        for (int i = 0; i < allNodes.size(); i++) {
            int oldValue = allNodes.get(i).val;
            allNodes.get(i).val = totalSum - valueTillNow;
            valueTillNow += oldValue;
        }
        
        return root;
    }
}
