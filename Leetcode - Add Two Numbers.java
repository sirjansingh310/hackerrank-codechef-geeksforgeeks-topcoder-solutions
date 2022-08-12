// https://leetcode.com/problems/add-two-numbers/
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode temp = dummy;
        int carry = 0;
        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + carry;
            if (sum > 9) {
                carry = (sum / 10) % 10;
                sum = sum % 10;
            } else {
                carry = 0;
            }
            temp.next = new ListNode(sum);
            l1 = l1.next;
            l2 = l2.next;
            temp = temp.next;
        }
        
        while (l1 != null) {
            int sum = l1.val + carry;
            if (sum > 9) {
                carry = (sum / 10) % 10;
                sum = sum % 10;
            } else {
                carry = 0;
            }
            temp.next = new ListNode(sum);
            l1 = l1.next;
            temp = temp.next;
        }
        
        while (l2 != null) {
            int sum = l2.val + carry;
            if (sum > 9) {
                carry = (sum / 10) % 10;
                sum = sum % 10;
            } else {
                carry = 0;
            }
            temp.next = new ListNode(sum);
            l2 = l2.next;
            temp = temp.next;
        }
        
        if (carry > 0) {
            temp.next = new ListNode(carry);
        }
        
        return dummy.next;
    }
}
