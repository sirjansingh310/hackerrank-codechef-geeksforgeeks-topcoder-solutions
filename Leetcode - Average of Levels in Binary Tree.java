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
/// Approach 1 : BFS + Level order, but not using space to store all level's in a list like how I used to do(traditional bfs with Map<Integer, List<TreeNode>> levelToNode )
// instead traversing / polling from queue in batch for one level at a time :)
class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
    
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (queue.size() > 0) {
            int currentLevelSize = queue.size();// traverse all in this go of while loop
            double sum = 0;
            int counter = 0;
            while (counter < currentLevelSize) {
                TreeNode current = queue.poll();
                sum += current.val;
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
                counter++;
            }
            result.add(sum / currentLevelSize);
        }
        return result;
    }
}


// app 2 dfs, can be optimized more like the bfs approach
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
    private Map<Integer, List<Integer>> levelToNodes = new HashMap<>();
    private void dfs(TreeNode root, int level) {
        if (root == null) {
            return;
        }
        if (levelToNodes.containsKey(level)) {
            levelToNodes.get(level).add(root.val);
        } else {
            levelToNodes.put(level, new ArrayList<>(Arrays.asList(root.val)));
        }
        
        dfs(root.left, level + 1);
        dfs(root.right, level + 1);
        
    }
    public List<Double> averageOfLevels(TreeNode root) {
        dfs(root, 1);
        return levelToNodes.values().stream()
            .map(list -> {
                double sum = 0;
                for (int i = 0; i < list.size(); i++) {
                    sum += list.get(i);
                }
                return sum / list.size();
            })
            .collect(Collectors.toList());
    }
}
