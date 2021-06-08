// https://leetcode.com/explore/challenge/card/june-leetcoding-challenge-2021/604/week-2-june-8th-june-14th/3772/
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
    private int preorderIndex;
    private Map<Integer, Integer> inorderMap;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        inorderMap = new HashMap<>();
        
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return create(preorder, inorder, 0, inorder.length - 1);
    }
    
    private TreeNode create(int[] preorder, int[] inorder, int inorderLeft, int inorderRight) {
        if (inorderRight < inorderLeft) {
            return null;
        }
        
        TreeNode root = new TreeNode(preorder[preorderIndex++]);
        // since values are unique, we can use a lookup hashmap. 
        int inorderIndex = inorderMap.get(root.val);
        
        // for (int i = inorderLeft; i <= inorderRight; i++) {
        //     if (inorder[i] == root.val) {
        //         inorderIndex = i;
        //         break;
        //     }
        //  }
        
        root.left = create(preorder, inorder, inorderLeft, inorderIndex - 1);
        root.right = create(preorder, inorder, inorderIndex + 1, inorderRight);
        
        return root;
    }
}
