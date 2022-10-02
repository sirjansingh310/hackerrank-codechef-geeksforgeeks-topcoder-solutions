// https://leetcode.com/problems/path-sum-iii/
// DFS Solution followed by faster prefix sum solution

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
    private int count;
    private int targetSum;
    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        
        exploreSums(root, root.val);
        dfs(root.left);
        dfs(root.right);
    }
    
    private void exploreSums(TreeNode root, int sum) {
        if (sum == targetSum) {
            count++;
        }
        
        if (root.left != null) {
            exploreSums(root.left, sum + root.left.val);
        }
        
        if (root.right != null) {
            exploreSums(root.right, sum + root.right.val);
        }

    }
    
    public int pathSum(TreeNode root, int targetSum) {
        this.targetSum = targetSum;
        dfs(root);
        return count;
    }
}


// Prefix sum
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
    public int pathSum(TreeNode root, int targetSum) {
        Map<Long, Integer> prefixSumFreqMap = new HashMap<>();
        prefixSumFreqMap.put(0L, 1);
        
        return recur(root, 0, targetSum, prefixSumFreqMap);
    }
    
    private int recur(TreeNode root, long sum, int target, Map<Long, Integer> prefixSumMap) {
        if (root == null) {
            return 0;
        }
        
        sum += root.val;
        int numberOfRoots = prefixSumMap.getOrDefault(sum - target, 0); // number of roots seen till now in DFS from which the sum is equal to target till current node. It is like two sum
        
        prefixSumMap.put(sum, prefixSumMap.getOrDefault(sum, 0) + 1);
        
        int result = numberOfRoots + recur(root.left, sum, target, prefixSumMap) + 
            recur(root.right, sum, target, prefixSumMap);
        
        prefixSumMap.put(sum, prefixSumMap.get(sum) - 1); // back track, 
        // so our map is always for current dfs path
        return result;
        
    }
}
