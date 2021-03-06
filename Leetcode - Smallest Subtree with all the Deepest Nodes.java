
/// https://leetcode.com/explore/challenge/card/december-leetcoding-challenge/570/week-2-december-8th-december-14th/3563/
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
    private static final int MAX_SIZE = 500;
    private final Map<TreeNode, TreeNode> childToParent = new HashMap();
    private List<TreeNode> leafNodes;
    
    private void doBFS(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        Map<Integer, List<TreeNode>> levelOrder = new HashMap<>();
        
        queue.add(root);
        
        int[] distance = new int[MAX_SIZE + 1];
        int maxDistance = Integer.MIN_VALUE;
        
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            int parentDistance = distance[current.val];
            if (levelOrder.containsKey(parentDistance)) {
                levelOrder.get(parentDistance).add(current);
            } else {
                levelOrder.put(parentDistance, new ArrayList<>(
                    Collections.singletonList(current)
                ));
            }
            maxDistance = Math.max(maxDistance, parentDistance) ;
            
            if (current.left != null) {
                queue.add(current.left);
                distance[current.left.val] = parentDistance + 1;
                childToParent.put(current.left, current);
            }
            
            if (current.right != null) {
                queue.add(current.right);
                distance[current.right.val] = parentDistance + 1;
                childToParent.put(current.right, current);
            }
        }   
        leafNodes = levelOrder.get(maxDistance);

    }
    
    private boolean commonParentReached(List<TreeNode> nodes) {
        return new HashSet(nodes).size() == 1;
    }
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        doBFS(root);
        
        while(!commonParentReached(leafNodes)) {
            for (int i = 0; i < leafNodes.size(); i++) {
                leafNodes.set(i, childToParent.get(leafNodes.get(i)));                
            }
        }
        
        return leafNodes.get(0);
    }
}


// Approach 2: DFS, recursion. For a node n, if n.left can go to leaves and n.right goes to leaves (i.e deepestlevel), then node n is the common ancestor
// class Solution {
    
//     int deepestLevel = 0;
//     TreeNode res = null;
    
//     public TreeNode subtreeWithAllDeepest(TreeNode root) {
//         dfs(root, 0);
//         return res;
//     }
    
//     private int dfs(TreeNode root, int level) {
//         if (root == null) return level;
        
//         int leftLevel = dfs(root.left, level + 1);
//         int rightLevel = dfs(root.right, level + 1);
        
//         int curLevel = Math.max(leftLevel, rightLevel);
//         deepestLevel = Math.max(deepestLevel, curLevel);
//         if (leftLevel == deepestLevel && rightLevel == deepestLevel)
//             res = root;
//         return curLevel;
//     }
// }
