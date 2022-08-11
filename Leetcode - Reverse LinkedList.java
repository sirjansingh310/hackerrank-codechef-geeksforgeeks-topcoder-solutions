/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    private ListNode mainHead;
    public ListNode reverseList(ListNode head) {
        reverse(head);
        return mainHead;
    }
    
    private ListNode reverse(ListNode node) {
        if (node == null || node.next == null) {
            mainHead = node;
            return node;
        } else {
            ListNode returnedNode = reverse(node.next);
            node.next = null;
            returnedNode.next = node;
            return node;
        }
    }
}
