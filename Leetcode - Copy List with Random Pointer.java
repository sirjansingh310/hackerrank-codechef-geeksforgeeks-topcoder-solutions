/// https://leetcode.com/explore/challenge/card/february-leetcoding-challenge-2021/585/week-2-february-8th-february-14th/3635/
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Map<Node, Node> originalToCopy = new HashMap<>();
        Node newHead = null, prev = null, temp = head;
        
        while (temp != null) {
            if (prev == null) {
                newHead = new Node(temp.val);
                prev = newHead;
                temp = temp.next;
                originalToCopy.put(head, newHead);
                continue;
            } else {
                prev.next = new Node(temp.val);
                originalToCopy.put(temp, prev.next);
            }
            temp = temp.next;
            prev = prev.next;
        }
        
        for (Map.Entry<Node, Node> entry : originalToCopy.entrySet()) {
            Node original = entry.getKey();
            Node copy = entry.getValue();
            copy.random = originalToCopy.containsKey(original.random) ? originalToCopy.get(original.random) : null;
        }
        
        return newHead;
    }
}
