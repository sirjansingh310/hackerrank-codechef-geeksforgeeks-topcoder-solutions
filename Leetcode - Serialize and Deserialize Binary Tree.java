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

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return serialize(root, 0);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        return deserialize(data, 0);
    }

    private String serialize(TreeNode root, int recusionNumber) {
        if (root == null) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder("");
        sb.append("{");
        sb.append(root.val);
        sb.append(getRecursionSeparator(recusionNumber));
        sb.append(serialize(root.left, recusionNumber + 1));
        sb.append(getRecursionSeparator(recusionNumber));
        sb.append(serialize(root.right, recusionNumber + 1));
        sb.append("}");
        return sb.toString();
    }

    private TreeNode deserialize(String encoded, int recursionNumber) {
        if (encoded == null || encoded.equals("") || encoded.equals("{}")) {
            return null;
        }
        String[] tokens = encoded.substring(1, encoded.length() - 1).split(getRecursionSeparator(recursionNumber));
        TreeNode root = new TreeNode(Integer.parseInt(tokens[0]));
        root.left = deserialize(tokens[1], recursionNumber + 1);
        root.right = deserialize(tokens[2], recursionNumber + 1);
        return root;
    }

    public String getRecursionSeparator(int recusionNumber) {
        return "_SEPARATOR_" + recusionNumber + "_";
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
