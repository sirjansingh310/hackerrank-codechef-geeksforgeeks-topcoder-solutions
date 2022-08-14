// https://leetcode.com/problems/count-good-nodes-in-binary-tree/
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
    public int goodNodes(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> maxPaths = new LinkedList<>();
        if (root == null) {
            return 0;
        }
        
        queue.add(root);
        maxPaths.add(root.val);
        int count = 0;
        
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            int maxPathCurrent = maxPaths.poll();
            if (current.val >= maxPathCurrent) {
                count++;
            }
            if (current.left != null) {
                queue.add(current.left);
                maxPaths.add(Math.max(maxPathCurrent, current.left.val));
            }
            
            if (current.right != null) {
                queue.add(current.right);
                maxPaths.add(Math.max(maxPathCurrent, current.right.val));
            }
        }
        
        return count;
    }
}
