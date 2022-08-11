// validate a binary search tree. https://leetcode.com/problems/validate-binary-search-tree/
// solution 1, inorder search and see if sorted
// solution 2, for each subtree, store max and min at that subtree, including the root of subtree into consideration. Now check for each node, if it is valid
// by checking max at left and min at right with root.val. Repeat it for left and right subtree also

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
    public boolean isValidBST(TreeNode root) {
        List<Integer> inorder = new ArrayList<>();
        inorder(root, inorder);
        for (int i = 0; i < inorder.size() - 1; i++) {
            if (inorder.get(i) >= inorder.get(i + 1)) {
                return false;
            }
        }
        
        return true;
    }
    
    private void inorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inorder(root.left, list);
        list.add(root.val); 
        inorder(root.right, list);
    }
}

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
    private Map<TreeNode, Long> maxMap = new HashMap<>();// max at that subtree, include subtree root in calculation
    private Map<TreeNode, Long> minMap = new HashMap<>();// min at that subtree, include subtree root in calculation
    
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        dfs(root);
        return validateTree(root);
    }
    
    private void dfs(TreeNode root) {
        if (root.left == null && root.right == null) {
            maxMap.put(root, (long)root.val);
            minMap.put(root, (long)root.val);
            return;
        }
        long max = root.val;
        long min = root.val;
        if (root.left != null) {
            dfs(root.left);
            max = Math.max(max, maxMap.get(root.left));
            min = Math.min(min, minMap.get(root.left));
        }
        
        if (root.right != null) {
            dfs(root.right);
            max = Math.max(max, maxMap.get(root.right));
            min = Math.min(min, minMap.get(root.right));
        }
        maxMap.put(root, max);
        minMap.put(root, min);
    }
    
    private boolean validateTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        long maxFromLeftSubtree = maxMap.getOrDefault(root.left, Long.MIN_VALUE);
        long minFromRightSubtree = minMap.getOrDefault(root.right, Long.MAX_VALUE);
        boolean isValid = root.val > maxFromLeftSubtree && root.val < minFromRightSubtree;
        return isValid && validateTree(root.left) && validateTree(root.right);
    }
}
