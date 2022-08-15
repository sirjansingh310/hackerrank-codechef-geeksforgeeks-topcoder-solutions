// https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
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
    private static final String SEPARATOR = "_end";// something node regex reserved
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder encodedBuilder = new StringBuilder(""); // root to be on first in this.
        serialize(root, encodedBuilder);
        return encodedBuilder.toString();
    }

    private void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return;
        }
        // given root with left and right, lets say root is 3
        // we are going to encode it as "3#hashCode[leftHashCode,rightHashCode]"
        // if they are null, we put the word null
        sb.append(root.val);
        sb.append("#");
        sb.append(root.hashCode());
        sb.append("[");
        if (root.left != null) {
            sb.append(root.left.hashCode());
            sb.append(",");
        } else {
            sb.append("null,");
        }

        if (root.right != null) {
            sb.append(root.right.hashCode());
        } else {
            sb.append("null");
        }
        sb.append("]");
        sb.append(SEPARATOR); 

        serialize(root.left, sb);
        serialize(root.right, sb);
    }



    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.equals("")) {
            return null;
        }
        String[] nodes = data.split(SEPARATOR);
        Map<String, String> nodesMap = getNodesMap(nodes);
        
        String rootNodeString = nodes[0];
        return buildTree(rootNodeString, nodesMap);
        
    }
    
    private TreeNode buildTree(String rootString, Map<String, String> map) {
        if (rootString == null || rootString.equals("")) {
            return null;
        }
        String nodeData = rootString.substring(0, rootString.indexOf("["));
        String value = nodeData.split("#")[0];
        TreeNode root = new TreeNode(Integer.parseInt(value));
        
        String childrenKeys = rootString.substring(rootString.indexOf("[") + 1, rootString.indexOf("]"));
        String leftKey = childrenKeys.split(",")[0];
        String rightKey = childrenKeys.split(",")[1];
        
        if (!leftKey.equals("null")) {
            String leftData = map.get(leftKey);
            root.left = buildTree(leftData, map);
        }
        
        if (!rightKey.equals("null")) {
            String rightData = map.get(rightKey);
            root.right = buildTree(rightData, map);
        }
        
        return root;
    }

    private Map<String, String> getNodesMap(String[] nodes) {
        Map<String, String> map = new HashMap<>();
        for (String node : nodes) {
            String hashCode = node.substring(node.indexOf("#") + 1, node.indexOf("["));
            map.put(hashCode, node);
        }
        return map;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
