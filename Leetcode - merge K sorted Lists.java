//https://leetcode.com/problems/merge-k-sorted-lists/
// Min Heap solution. Put all values of all the lists in a min heap.
//Time complexity : O(Nlogk) where k is the number of linked lists.
//The comparison cost will be reduced to O(logk) for every pop and insertion to priority queue. But finding the node with the smallest value just costs O(1) time.

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
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> a - b); // pass lamdba of comparator, how to compare 2 values, for heapify and maintaining heap order in the data structure
        
        for (ListNode node : lists) {
            ListNode temp = node;
            while (temp != null) {
                minHeap.add(temp.val);
                temp = temp.next;
            }
        }
        
        ListNode dummy = new ListNode(), temp = dummy;
        
        while (!minHeap.isEmpty()) {
            temp.next = new ListNode(minHeap.poll());
            temp = temp.next;
        }
        
        return dummy.next;
    }
}


// my first solution, n2 sol

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
    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(); // points to a dummy before head of result. In this way
        // we don't have to deal with special head case
        ListNode temp = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                temp.next = new ListNode(l1.val);
                l1 = l1.next;
            } else {
                temp.next = new ListNode(l2.val);
                l2 = l2.next;
            }
            temp = temp.next;
        }
        
        if (l1 == null) {
            temp.next = l2; // point to remaining list
        }
        
        if (l2 == null) {
            temp.next = l1;
        }
        
        return dummy.next;
    }
    public ListNode mergeKLists(ListNode[] lists) {
        
        ListNode result = null;
        
        for (ListNode node: lists) {
            result = merge(result, node);
        }
        
        return result;
        
    }
}
