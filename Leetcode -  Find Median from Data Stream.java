// https://leetcode.com/problems/find-median-from-data-stream/
class MedianFinder {
    // we have 2 priority queues. Think of data stream array into 2 halves
    // first half is in max heap so middle element is on top of pq
    // second half is in min heap so mid + 1 is on top
    // for data stream size even, media is avg of both
    // for odd, median is top of first heap, i.e max heap
    // Also to maintain this half and half structure, max heap size - min heap size should be <= 1
   // ex stream is 1,2,3,4,5 [1,2,3] is in left heap, [4,5] is in right heap. Median is peek of left heap i.e 3
  // stream is 4,5,6,2,1,3. left heap has [1,2,3]. right heap has [4,5,6]. Median in ordered set is avg of both peaks, i.e avg(3,4) i.e 3.5
    
    private final PriorityQueue<Integer> leftHeap;
    private final PriorityQueue<Integer> rightHeap;
    private int streamSize;
    
    public MedianFinder() {
        this.leftHeap = new PriorityQueue<>(Collections.reverseOrder());
        this.rightHeap = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        this.streamSize++;
        if (leftHeap.isEmpty()) {
            leftHeap.add(num);
        } else if (num < leftHeap.peek()) {
            leftHeap.add(num);
        }  else if (rightHeap.isEmpty()) {
            rightHeap.add(num);
        } else {
            rightHeap.add(num);
        }
        
        
        // rebalance to maintain half and half structruce
        if (this.streamSize % 2 == 1) {
            if (leftHeap.size() <= rightHeap.size()) {
                while (!rightHeap.isEmpty() && leftHeap.size() - rightHeap.size() != 1) {
                    leftHeap.add(rightHeap.poll());
                }
            } else {
                while (!leftHeap.isEmpty() && leftHeap.size() - rightHeap.size() != 1) {
                    rightHeap.add(leftHeap.poll());
                }
            }
        } else {
            if (leftHeap.size() > rightHeap.size()) {
                while (!leftHeap.isEmpty() && leftHeap.size() != rightHeap.size()) {
                    rightHeap.add(leftHeap.poll());
                }
            } else {
                while (!rightHeap.isEmpty() && leftHeap.size() != rightHeap.size()) {
                    leftHeap.add(rightHeap.poll());
                }
            }
        }
    }
    
    public double findMedian() {
        if (this.streamSize % 2 == 1) {
            return leftHeap.peek();
        } else {
            return ((double)(leftHeap.peek()) + rightHeap.peek()) / 2;
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
