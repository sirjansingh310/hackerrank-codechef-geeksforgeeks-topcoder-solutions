// https://leetcode.com/problems/maximum-width-of-binary-tree/
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
    public int widthOfBinaryTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        // MAP Wrt Full binary tree(as nulls can exist in our normal binary tree and 
        // they should be considered for width of a level with 2 non null nodes at extremes)
        Map<TreeNode, Integer> positionMap = new HashMap<>();
        positionMap.put(root, 0);
        queue.add(root);
        int maxWidth = -1;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<TreeNode> currentLevel = new ArrayList<>();
            
            while (size-- > 0) {
                TreeNode current = queue.poll();
                currentLevel.add(current);
                int position = positionMap.get(current);
                
                if (current.left != null) {
                    queue.add(current.left);
                    positionMap.put(current.left, 2 * position);
                }
                
                if (current.right != null) {
                    queue.add(current.right);
                    positionMap.put(current.right, 2 * position + 1);
                }
            }
            int diff = positionMap.get(currentLevel.get(currentLevel.size() - 1))
                - positionMap.get(currentLevel.get(0)) + 1;
            
            maxWidth = Math.max(diff, maxWidth);
        }
        
        return maxWidth;
    }
}
