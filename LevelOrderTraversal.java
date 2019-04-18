// https://leetcode.com/problems/binary-tree-level-order-traversal/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> levelOrder = new ArrayList<>();
        if(root == null)
            return levelOrder;
        
        LinkedHashMap<TreeNode,Integer> level = new LinkedHashMap<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        level.put(root,1);
        queue.add(root);
        
        while(queue.size()>0){
            TreeNode current = queue.poll();
            if(current.left != null)
            {
                level.put(current.left,level.get(current) + 1);
                queue.add(current.left);
            }
            if(current.right != null){
                level.put(current.right,level.get(current) + 1);
                queue.add(current.right);
            }
        }
        List<Integer> currentLevelList = new ArrayList<>();
        int currentLevel = 1;
        for(TreeNode current: level.keySet()){
            if(currentLevel == level.get(current)){
                currentLevelList.add(current.val);
            }
            else{
                levelOrder.add(currentLevelList);
                currentLevel++;
                currentLevelList = new ArrayList<>();
                currentLevelList.add(current.val);
            }
        }
        if(currentLevelList.size() > 0)
            levelOrder.add(currentLevelList);
        return levelOrder;
    }
}
