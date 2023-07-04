// https://leetcode.com/problems/maximum-sum-bst-in-binary-tree/description/
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

class Node {
    int sum;
    int largest;
    int smallest;

    public Node(int sum, int largest, int smallest) {
        this.sum = sum;
        this.largest = largest;
        this.smallest = smallest;
    }

    public static Node extremeNode() {
        return new Node(-1, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }
}
class Solution {
    private int bestSum;
    public int maxSumBST(TreeNode root) {
        maxBST(root);
        return bestSum;
    }

    private Node maxBST(TreeNode root) {
        if (root == null) {
            return new Node(0, Integer.MIN_VALUE, Integer.MAX_VALUE);// valid null node. 
        }

        Node leftSide = maxBST(root.left);
        Node rightSide = maxBST(root.right);

        // valid case
        if (leftSide.largest < root.val && root.val < rightSide.smallest) {
            int myLargest = Math.max(root.val, rightSide.largest); // in case of nulls we are sending Integer.MIN_VALUE, to not send it anymore we do this. 
            int mySmallest = Math.min(root.val, leftSide.smallest);

            int mySum = root.val + leftSide.sum + rightSide.sum;
            bestSum = Math.max(bestSum, mySum);
            return new Node(mySum, myLargest, mySmallest);
        } else {// invalid bst
            return Node.extremeNode();
        }
    }
}
