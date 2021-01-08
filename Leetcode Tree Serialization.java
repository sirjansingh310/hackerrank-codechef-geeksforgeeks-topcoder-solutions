// https://leetcode.com/explore/interview/card/top-interview-questions-medium/112/design/812/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {
    private int preorderIndex = 0;
    
    private String getInorder(TreeNode root) {
        if (root == null) {
            return "";
        }
        String inorder = getInorder(root.left);
        inorder += root.val + "_" + root.hashCode() + " "; // tree might have duplicate values, compare 
        // with exact hashcode in generateTree function to get correct split index in inorder.
        inorder += getInorder(root.right);
        
        return inorder;
    }
    
    private String getPreorder(TreeNode root) {
        if (root == null) {
            return "";
        }
        String preorder = root.val + "_" + root.hashCode() + " ";
        preorder += getPreorder(root.left);
        preorder += getPreorder(root.right);
        
        return preorder;
    }
    
    private String getValueOfNode(String s) {
        return s.split("_")[0];
    }
    
    private TreeNode generateTree(String[] inorder, String[] preorder, int startIdx, int endIdx) {
        if (startIdx > endIdx) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(getValueOfNode(preorder[preorderIndex])));
        
        if (startIdx == endIdx) {
            preorderIndex++;
            return root;
        }
        
        int splitIndex = 0;
        
        for (int i = startIdx; i <= endIdx; i++) {
            if (inorder[i].equals(preorder[preorderIndex])) { /// break when exact match with hashcode
                splitIndex = i;
                break;
            }
        }
        
        preorderIndex++;
        root.left = generateTree(inorder, preorder, startIdx, splitIndex - 1);
        root.right = generateTree(inorder, preorder, splitIndex + 1, endIdx);
        
        return root;
    }
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return null;
        }
        String inorder = getInorder(root);
        String preorder = getPreorder(root);
        
        return inorder + "," + preorder;
    }
    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        // System.out.println(data);
        if (data == null) {
            return null;
        }
        String inorder = data.split(",")[0];
        String preorder = data.split(",")[1];
        
        return generateTree(inorder.split(" "), preorder.split(" "), 0, inorder.split(" ").length - 1);
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
