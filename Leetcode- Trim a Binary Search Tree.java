//https://leetcode.com/explore/featured/card/february-leetcoding-challenge-2021/584/week-1-february-1st-february-7th/3626/
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
    private void insertBST(TreeNode root, int value) {
        if (root == null) {
            return;
        }
        if (value < root.val) {
            if (root.left != null) {
                insertBST(root.left, value);
            } else {
                root.left = new TreeNode(value);
            }
        } else {
            if (root.right != null) {
                insertBST(root.right, value); 
            } else {
                root.right = new TreeNode(value);
            }
        }
    }
    
    private void doBFS(TreeNode root, List<Integer> validList, int low, int high) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        while (queue.size() > 0) {
            TreeNode current = queue.poll();
            if (current.val >= low && current.val <= high) {
                validList.add(current.val);
            } 
            
            if (current.left != null) {
                queue.add(current.left);
            } 
            if (current.right != null) {
                queue.add(current.right);
            }
        }
    }
    
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return root;
        }
        
        List<Integer> validList = new ArrayList<>();
        doBFS(root, validList, low, high); // level-order. inorder will fill validlist in sorted fashion, result will be skewed tree to the right(linked list like)
        
        root = new TreeNode(validList.get(0));
        
        for (int i = 1; i < validList.size(); i++) {
            insertBST(root, validList.get(i));
        }
        
        return root;
    }
}
