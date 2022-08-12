// https://leetcode.com/problems/remove-nth-node-from-end-of-list/

// O(N) One pass solution, O(1) Space
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

// Take a pointer second and put it at (n+1)th position from the beginning
//         Take pointer first and move it forward till second reaches Last Node and second.next points to null
//         At that point we would have reached the (n-1)th node from the end using the pointer first
//        remove that node
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode first = dummy, second = dummy;
        
       
        while (n > 0) {
            second = second.next;
            n--;
        }
        
        // now when second is exhausted, first will point node where to delete, i.e nth from end
        while (second.next != null) {
            first = first.next;
            second = second.next;
        }
        first.next = first.next.next;
        return dummy.next;
    }
}
