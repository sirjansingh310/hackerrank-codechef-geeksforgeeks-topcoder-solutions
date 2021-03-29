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

////https://leetcode.com/problems/rotate-list/
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
       if (k == 0 || head == null || head.next == null) return head;
        
        ListNode end = head;
        
        int len = 0;
        while (end.next != null){
            len++;
            end = end.next;
        }
        
        len++;
        
        k = k % len;
        
        ListNode tmp = head;
        for (int i = 1; i < len-k; i++) tmp = tmp.next;
        
        end.next = head;
        
        head = tmp.next;
        tmp.next = null;
        
        return head;
    }
}
