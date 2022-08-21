// https://leetcode.com/problems/clone-graph/
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        Map<Node, Node> oldToNew = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        Set<Node> visited = new HashSet<>();
        queue.add(node);
        visited.add(node);
        
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            Node copy = new Node(current.val);
            oldToNew.put(current, copy);
            
            for (Node child : current.neighbors) {
                if (!visited.contains(child)) {
                    visited.add(child);
                    queue.add(child);
                }
            }
        }
        
        for (Map.Entry<Node, Node> entry : oldToNew.entrySet()) {
            Node old = entry.getKey();
            Node newNode = entry.getValue();
            
            for (Node oldChild : old.neighbors) {
                newNode.neighbors.add(oldToNew.get(oldChild));
            }
        }
        
        return oldToNew.get(node);
    }
}
