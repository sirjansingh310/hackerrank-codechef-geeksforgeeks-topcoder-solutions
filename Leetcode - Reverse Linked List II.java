// https://leetcode.com/explore/challenge/card/june-leetcoding-challenge-2021/606/week-4-june-22nd-june-28th/3789/
// Reverse linked list between provided left and right values

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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null) {
            return null;
        } else if (left == right) {
            return head;
        }
        
        try {
            // lets create a dummy node so we don't have to explicitly handle case where left = 1
            // and with this dummy node, left and right are 0 based index instead of 1 based as per question
            ListNode dummy = new ListNode();
            dummy.next = head;
            
            ListNode temp = dummy, prev = null;
            int index = 0;
            while (index < left) {
                prev = temp;
                temp = temp.next;
                index++;
            }
            Stack<ListNode> stack = new Stack<>();
            while (index <= right) {
                stack.add(temp);
                temp = temp.next;
                index++;
            }
            
            ListNode next = temp;
            
            while (!stack.isEmpty()) {
                prev.next = stack.pop();
                prev = prev.next;
            }
            
            prev.next = next;
            return dummy.next;
            
        } catch (Exception e) {
            System.out.println("exception" + e.toString());
            return null;
        }
    }
}
