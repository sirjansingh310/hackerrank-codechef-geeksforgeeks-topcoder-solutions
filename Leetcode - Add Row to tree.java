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
// Level Order Traversal solution. https://leetcode.com/explore/challenge/card/march-leetcoding-challenge-2021/589/week-2-march-8th-march-14th/3666/
class Solution {
    private void addNewRow(int value, final Queue<TreeNode> queue, int currentLevelSize) {
        while (currentLevelSize-- > 0) {
            TreeNode current = queue.poll();
            TreeNode oldLeft = current.left;
            TreeNode oldRight = current.right;
            TreeNode newLeft = new TreeNode(value);
            TreeNode newRight = new TreeNode(value);
            newLeft.left = oldLeft;
            newRight.right = oldRight;
            current.left = newLeft;
            current.right = newRight;
        }
    }
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (d == 1) {
            TreeNode newRoot = new TreeNode(v);
            newRoot.left = root;
            return newRoot;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;
        while (!queue.isEmpty()) {
            int currentLevelSize = queue.size();
            level++;
            if (level == d - 1) {
                addNewRow(v, queue, currentLevelSize);
                break;
            }
            while (currentLevelSize-- > 0) {
                TreeNode current = queue.poll();
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
        }
        return root;
    }
}
