// O(N) Time
// https://leetcode.com/problems/swap-nodes-in-pairs/
// Iterative and recursive solutions: 
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
    public ListNode swapPairs(ListNode head) {
        ListNode temp = head;
        ListNode prev = null;
        
        while (temp != null && temp.next != null) {
            ListNode second = temp.next;
            ListNode third = second.next;
            
            if (prev != null) {
                prev.next = second;
            }
            
            second.next = temp;
            temp.next = third;
            
            if (temp == head) {
                head = second;
            }
            prev = temp;
            temp = third;
        }
        
        return head;
    }
}


// Recursive
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
    public ListNode swapPairs(ListNode head) {
       if (head == null || head.next == null) {
           return head;
       }
        
        ListNode second = head.next;
        ListNode third = second.next;
        second.next = head;
        head.next = swapPairs(third);        
        return second;
        
    }
}
