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
    public ListNode partition(ListNode head, int x) {
        ListNode temp = head;
        ListNode left = null;
        ListNode right = null;
        ListNode leftHead = null;
        ListNode rightHead = null;
        
        while (temp != null) {
            if (temp.val < x) {
                if (left == null) {
                    left = new ListNode(temp.val);
                    leftHead = left;
                } else {
                    left.next = new ListNode(temp.val);
                    left = left.next;
                }
            } else {
                if (right == null) {
                    right = new ListNode(temp.val);
                    rightHead = right;
                } else {
                    right.next = new ListNode(temp.val);
                    right = right.next;
                }
            }
            temp = temp.next;
        }
        
        if (leftHead != null) {
            left.next = rightHead;
        } else {
            leftHead = rightHead;
        }
        
        return leftHead;
    }
}
