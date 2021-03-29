////https://leetcode.com/explore/challenge/card/march-leetcoding-challenge-2021/592/week-5-march-29th-march-31st/3689/
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
    private int preorderIndex = 0;
    private List<Integer> flipped = new ArrayList<>();
    
    private void recur(TreeNode root, int[] voyage) {
        if (root == null || preorderIndex == voyage.length) {
            return;
        }
        
        if (root.val != voyage[preorderIndex]) {
            flipped = new ArrayList(Arrays.asList(-1));
            return;
        }
        
        if (root.left != null) {
            if (root.left.val != voyage[preorderIndex + 1]) {
                swap(root);
                flipped.add(root.val);
            }
        } else if(root.right != null) {
            if (root.right.val != voyage[preorderIndex + 1]) {
                swap(root);
                flipped.add(root.val);
            }
        }
        preorderIndex++;
        recur(root.left, voyage);
        recur(root.right, voyage);
    }
    
    private void swap(TreeNode root) {
        TreeNode left = root.left;
        root.left = root.right;
        root.right = left;
    }
    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        recur(root, voyage);
        if (flipped.contains(-1)) {
            return Arrays.asList(-1);
        }
        return flipped;
    }
}
