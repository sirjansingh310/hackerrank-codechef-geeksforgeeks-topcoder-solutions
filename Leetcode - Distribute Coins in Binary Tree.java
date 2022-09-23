// Distribute Coins in Binary Tree
// https://leetcode.com/problems/distribute-coins-in-binary-tree/ 
// Very nice problem!!

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
    private int moves = 0;
    public int distributeCoins(TreeNode root) {
        Map<TreeNode, Pair<Integer, Integer>> map = new HashMap<>(); // TreeNode -> Pair. Pair key is TreeNode size, Pair value is TreeNode number of coins
        fillMap(root, map);
        
        // map.forEach((k, v) -> System.out.println(k.val + " " + v.getKey() + "  " + v.getValue()));
        
        recur(root, map, 0);
        // printTree(root);
        return moves;
    }
    
    
    private int recur(TreeNode root, Map<TreeNode, Pair<Integer, Integer>> map, int excess) {
        if (root == null) {
            return 0;
        }
        Pair<Integer, Integer> left = map.getOrDefault(root.left, new Pair<>(0, 0));
        Pair<Integer, Integer> right = map.getOrDefault(root.right, new Pair<>(0, 0));
        
        boolean leftNeedHelp = left.getValue() < left.getKey(); // less coins than subtree size
        boolean rightNeedHelp = right.getValue() < right.getKey();
        boolean parentNeedHelp = root.val == 0;
        
        // when left, right, and parent don't need help from top and can distribute on their own, let them do and parent should not send any more coins from top. We collect extra coins we get from them
        if (!leftNeedHelp) {
            excess += recur(root.left, map, 0);
        }
        
        if (!rightNeedHelp) {
            excess += recur(root.right, map, 0);
        }
        
        if (!parentNeedHelp) {
            excess += (root.val - 1);
            root.val = 1;
        }
        
        
        // now we have exactly the number of coins to fix left, right and parent if they needed help and were not self-dependent. We have collected the extra coins already we need in the above lines, just send how much is requrired to avoid waste passing of coins. 
       // It is guaranteed to be working since number of nodes == number of coins in the problem. 
        if (leftNeedHelp) {
            int coinsToPush = left.getKey() - left.getValue();
            excess -= coinsToPush; // pushing coins down, increment moves
            moves += coinsToPush;
            recur(root.left, map, coinsToPush);
        }
        
        
        if (parentNeedHelp) {
            excess--;
            root.val = 1;
        }
        
        
        if (rightNeedHelp) {
            int coinsToPush = right.getKey() - right.getValue();
            excess -= coinsToPush;
            moves += coinsToPush;
            recur(root.right, map, coinsToPush);
        }
        
        moves += excess; // pushing coins up, increment moves
        return excess;
    }
    
    private Pair<Integer, Integer> fillMap(TreeNode root, Map<TreeNode, Pair<Integer, Integer>> map) {
        if (root == null) {
            return null;
        }
        
        int size = 1, coins = root.val;
        if (root.left != null) {
            Pair<Integer, Integer> leftPair = fillMap(root.left, map);
            size += leftPair.getKey();
            coins += leftPair.getValue();
        }
        
        if (root.right != null) {
            Pair<Integer, Integer> rightPair = fillMap(root.right, map);
            size += rightPair.getKey();
            coins += rightPair.getValue();
        }
        
        Pair<Integer, Integer> p = new Pair<>(size, coins);
        map.put(root, p);
        return p;
    }
    
    private void printTree(TreeNode root) {
        if (root.left != null) {
            printTree(root.left);
        }
        
        System.out.println(root.val);
        
        if (root.right != null) {
            printTree(root.right);
        }
    }
 }
