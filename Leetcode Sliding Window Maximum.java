// https://leetcode.com/problems/sliding-window-maximum/
// Todo: add O(N) Solution using deque (deque internally works using doubly linked list)
class Solution {
    private class Node implements Comparable<Node> {
        int number;
        int index;
        
        public Node(int number, int index) {
            this.number = number;
            this.index = index;
        }
        
        @Override
        public int compareTo(Node other) {
            return other.number - this.number;
        }
    }
    
    public int[] maxSlidingWindow(int[] nums, int k) {
        PriorityQueue<Node> queue = new PriorityQueue<>();// max heap
        List<Integer> result = new ArrayList<>();
        int idx = 0;
        
        while (idx < k) {
            queue.add(new Node(nums[idx], idx));
            idx++;
        }
        result.add(queue.peek().number);
        Set<Integer> removedIndexes = new HashSet<>();
        for (idx = k; idx < nums.length; idx++) {
            int removedIndex = idx - k;
            removedIndexes.add(removedIndex);
            // dont remove removed element just from the priority queue, remove only if
            // it is at top since we are only interested in top value. And if we don't remove it, it will stay
            // on top of queue for long time, leading for wrong results. 
            while (!queue.isEmpty() && removedIndexes.contains(queue.peek().index)) {
                queue.poll();
            }
            queue.add(new Node(nums[idx], idx));
            result.add(queue.peek().number);
        }
        
        int[] ret = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            ret[i] = result.get(i);
        }
        return ret;
    }
}
