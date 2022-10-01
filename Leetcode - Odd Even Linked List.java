// https://leetcode.com/problems/odd-even-linked-list/
// O(N) Time O(1) Space
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
    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode odd = head;
        ListNode even = head.next;
        ListNode temp = head;
        
        ListNode oddTemp = odd;
        ListNode evenTemp = even;
        
        while (oddTemp != null && evenTemp != null) {
            ListNode tempNext = temp.next;
            
            if (tempNext != null) {
                oddTemp.next = tempNext.next;
                oddTemp = oddTemp.next;
            }
            
            if (oddTemp != null && evenTemp != null) {
                evenTemp.next = oddTemp.next;
                evenTemp = evenTemp.next;
            }
            
            temp = oddTemp;
        }
        
        oddTemp = odd;
        while (oddTemp.next != null) {
            oddTemp = oddTemp.next;
        }
        oddTemp.next = even;
        
        return odd;
    }
}
