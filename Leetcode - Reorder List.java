// https://leetcode.com/problems/reorder-list/
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
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        Deque<ListNode> dq = new LinkedList<>();// we can solve this with a Map<Integer, ListNode> with index as key.
        
        ListNode temp = head;
        while (temp != null) {
            dq.add(temp);// addFirst() is also same. addLast() is on other end
            temp = temp.next;
        }
        // remove head from queue
        dq.poll(); // pollFirst() is also same.add() and poll() follow fifo
        
        head.next = dq.pollLast();
        temp = head.next;
        
        while (!dq.isEmpty()) {
            ListNode first = dq.poll();
            ListNode second = dq.pollLast();
            first.next = second;
            temp.next = first;
            if (second != null)
                second.next = null;
            temp = second;
        }
        
    }
}
