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
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        
        Map<Integer, List<Integer>> levelToList = new HashMap<>();
        Map<TreeNode, Integer> nodeToLevel = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        nodeToLevel.put(root, 0);
        
        while (queue.size() > 0) {
            TreeNode current = queue.poll();
            int level = nodeToLevel.get(current);
            
            if (levelToList.containsKey(level)) {
                    levelToList.get(level).add(current.val);
                } else {
                    levelToList.put(level, new ArrayList<>(Collections.singletonList(current.val)));
                }
            
            if (current.left != null) {
                queue.add(current.left);
                nodeToLevel.put(current.left, level + 1);
            }
            if (current.right != null) {
                queue.add(current.right);
                nodeToLevel.put(current.right, level+ 1);
            }
        }
        List<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer, List<Integer>> entry : levelToList.entrySet()) {
            result.add(entry.getValue().get(entry.getValue().size() - 1));
        }
        
        return result;
    }
}
