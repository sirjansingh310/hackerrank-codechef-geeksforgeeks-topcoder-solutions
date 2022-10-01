// https://leetcode.com/problems/sort-list/
// Merge sort, O(NlogN) time, O(1) space, as no new ListNode is created for the sorted list, all done in place
// But recursion itself takes space for recursion call stack
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
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        int size = getSize(head);
        
        ListNode right = getRight(size / 2, head); // we disconnect from mid point into two linkedlists
        ListNode left = head;
        
        left = sortList(left);
        right = sortList(right);
        return merge(left, right);
    }
    
    private int getSize(ListNode node) {
        int s = 0;
        while (node != null) {
            s++;
            node = node.next;
        }
        return s;
    }
    
    
    private ListNode getRight(int size, ListNode head) {
        ListNode temp = head;
        ListNode prev = null;
        while (size-- > 0) {
            prev = temp;
            temp = temp.next;
        }
        ListNode result = temp;
        prev.next = null; // disconnect
        return result;
    }
    
    private ListNode merge(ListNode node1, ListNode node2) {
        ListNode dummy = new ListNode(-1);
        ListNode temp = dummy;
        
        while (node1 != null && node2 != null) {
            if (node1.val < node2.val) {
                temp.next = node1;
                node1 = node1.next;
                temp = temp.next;
            } else {
                temp.next = node2;
                node2 = node2.next;
                temp = temp.next;
            }
        }
        
        if (node1 != null) {
            temp.next = node1;
        }
        
        if (node2 != null) {
            temp.next = node2;
        }
        
        return dummy.next;
    }
}
