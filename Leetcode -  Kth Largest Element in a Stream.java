// https://leetcode.com/problems/kth-largest-element-in-a-stream/
class KthLargest {
    private final PriorityQueue<Integer> pq;
    private final int k;
    
    public KthLargest(int k, int[] nums) {
        this.k = k;
        this.pq = new PriorityQueue<>((a, b) -> a.compareTo(b)); // min heap of size k. kth largest is smallest in heap and will be root
        for (int i : nums) {
            pq.add(i);
        }
        
    }
    
    public int add(int val) {
        pq.add(val);
        while (pq.size() > k) { // maintain size k
            pq.poll();
        }
        return pq.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
