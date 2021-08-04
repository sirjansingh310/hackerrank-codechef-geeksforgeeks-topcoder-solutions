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
// TC: O(N^2). If the binary tree has all paths till leaf leading to target sum, creating a copy to add to result will also count. (Why copy? since we backtrack
// to original state of list after current recursion call is done and parent recursion call's list state doesn't have side effect)

// Another way was to send a copy down to child recursive calls and just add reference to this list when target is 0. This will require too much memory as not all
// paths are valid
class Solution {
    private final List<List<Integer>> allPaths = new ArrayList<>();
    
    private void dfs(TreeNode root, int target, List<Integer> path) {
        if (root == null) {
            return;
        }
        path.add(root.val);
        target -= root.val;
        
        if (target == 0 && root.left == null && root.right == null) {
            allPaths.add(new ArrayList<>(path)); // save a copy in result
        } else {
            dfs(root.left, target, path);
            dfs(root.right, target, path);
        }
        path.remove(path.size() - 1); // backtrack
    }
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        dfs(root,targetSum, new ArrayList<>());
        return allPaths;
    }
}
