// https://leetcode.com/problems/maximum-subsequence-score/
class Solution {
    
    public long maxScore(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = nums1[i];
            arr[i][1] = nums2[i];
        }
        
        Arrays.sort(arr, (a1, a2) -> a1[1] - a2[1]);
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // keeps K -1 Top elements 
        long heapSum = 0L;
        
        for (int i = n - 1; i > n - k; i--) {
            heapSum += arr[i][0];
            pq.add(arr[i][0]);
            if (pq.size() > k - 1) {
                int removed = pq.poll();
                heapSum -= removed;
            }
        }
        
        long result = 0L;
        // since numbers at index > n -k will never be chosen as minimum because we cant have k elements with index number as min
        for (int i = n - k; i >= 0; i--) {
            long current = arr[i][1];
            long sum = arr[i][0];
            sum += heapSum;
            result = Math.max(result, sum * current);
            
            if (!pq.isEmpty() && arr[i][0] > pq.peek()) {
                int removed = pq.poll();
                heapSum -= removed;
                pq.add(arr[i][0]);
                heapSum += arr[i][0];
            }
        }
        
        return result;
    }
}
