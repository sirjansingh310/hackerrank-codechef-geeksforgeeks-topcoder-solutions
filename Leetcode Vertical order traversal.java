////https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/
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
    private class ValHeightMapping {
        int val;
        int height;
        
        ValHeightMapping(int val, int height) {
            this.val = val;
            this.height = height;
        }
    }
    private Map<Integer, List<ValHeightMapping>> indexToNodeMap = new HashMap<>();
    
    private void populateIndexMap(TreeNode root, int index, int height) {
        if (root == null) {
            return;
        }
        if (indexToNodeMap.containsKey(index)) {
            indexToNodeMap.get(index).add(new ValHeightMapping(root.val, height));
        } else {
            indexToNodeMap.put(index, new ArrayList<>(Collections.singletonList(new ValHeightMapping(root.val, height))));
        }
        populateIndexMap(root.left, index - 1, height + 1);
        populateIndexMap(root.right, index + 1, height + 1);
    }
    
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        populateIndexMap(root, 0, 0);
        List<List<ValHeightMapping>> sortedByIndexLists =  indexToNodeMap.entrySet().stream()
            .sorted(Map.Entry.comparingByKey())
            .map(entry -> entry.getValue())
            .collect(Collectors.toList());
        
        List<List<Integer>> results = new ArrayList<>();
        
        for (List<ValHeightMapping> list : sortedByIndexLists) {
            Collections.sort(list, (w1, w2) -> {
                if (w1.height == w2.height) {
                    return w1.val - w2.val;
                }
                return w1.height - w2.height;
            });
            
            List<Integer> sortedList = new ArrayList<>();
            for (ValHeightMapping mapping : list) {
                sortedList.add(mapping.val);
            }
            results.add(sortedList);
        }
        
        return results;
    }
}
