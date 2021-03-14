//https://leetcode.com/explore/challenge/card/march-leetcoding-challenge-2021/589/week-2-march-8th-march-14th/3671
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
// O(N) - single pass solution
class Solution {
    public ListNode swapNodes(ListNode head, int k) {
        ListNode kFromStart = head, kFromEnd = head, temp = head;
        int counter = 1;
        while (temp != null) {
            if (counter == k) {
                kFromStart = temp;
                kFromEnd = head; // when temp becomes null, kFromEnd will point at kth node from ending
            } else if (counter > k) {
                kFromEnd = kFromEnd.next;
            }
            temp = temp.next;
            counter++;
        }
        int tempVal = kFromStart.val;
        kFromStart.val = kFromEnd.val;
        kFromEnd.val = tempVal;
        return head;
    }
}
