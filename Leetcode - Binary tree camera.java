//// https://leetcode.com/explore/challenge/card/may-leetcoding-challenge-2021/600/week-3-may-15th-may-21st/3745/


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
/*

bottom up technique, recursive, we are doing postorder traversal on tree, meaning visiting bottom first
and going to top


the leaf level will be covered if we install all cameras on leaf(expensive) or install cameras 
on their parent(i.e one level up)

after doing this, we need to go to top and decide do we need to install camera on the node or not
*/



enum NodeStatus {
    COVERED, NOT_COVERED, CAMERA_INSTALLED;
}
class Solution {
    
    private int cameraInstalls = 0;
    
    public int minCameraCover(TreeNode root) {
        if (root == null) {
            return 1;
        }
        NodeStatus parentStatus = postOrder(root);
        if (NodeStatus.NOT_COVERED.equals(parentStatus)) {
            cameraInstalls++;
        }
        return cameraInstalls;
    }
    
    
    public NodeStatus postOrder(TreeNode root) {
        if (root == null) {
            return NodeStatus.COVERED;
        }
        
        NodeStatus left = postOrder(root.left);
        NodeStatus right = postOrder(root.right);
        
        if (left == NodeStatus.NOT_COVERED || right == NodeStatus.NOT_COVERED) {
            cameraInstalls++;
            return NodeStatus.CAMERA_INSTALLED;
        }
        
        return NodeStatus.CAMERA_INSTALLED.equals(left) || NodeStatus.CAMERA_INSTALLED.equals(right) ?
            NodeStatus.COVERED : NodeStatus.NOT_COVERED;
    }
}
