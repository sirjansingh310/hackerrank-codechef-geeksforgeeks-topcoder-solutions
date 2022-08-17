// https://leetcode.com/problems/ugly-number-ii/
// number should have only 2,3,5 has its set of prime factors
// given n, return nth such number

// solution 1-> Min Heap
// We know we start with 1, multiply it by 2, 3, 5. Now for each multiply by 2, 3, 5.. until we reach nth number.
// If we do recursively, we will calculate in depth. 1 -> 2,3,5. now for each of 2,3,5 multiply by 2... we will reach n but all our numbers will be big
// so we need to do bfs like, go by breadth. We can use Queue. The problem with regular queue is the order is not maintained, and we might pick a bigger number 
// first than a smaller number inside the queue. So we use min-heap / PriorityQueue to pick numbers. As soon as we picked nth number, we got our ans
class Solution {
    public int nthUglyNumber(int n) {
        
        PriorityQueue<Long> pq = new PriorityQueue<Long>(Long::compareTo);
        
        int[] mul = {2, 3, 5};
        int polledCount = 0;
        pq.add(1L);
        Set<Long> seen = new HashSet<>();
        seen.add(1L);
        int ans = -1;
        
        while (!pq.isEmpty()) {
            long polled = pq.poll();
            polledCount++;
            if (polledCount == n) {
                ans = (int)polled;
                break;
            }
            
            for (int i : mul) {
                if (!seen.contains(polled * i)) {
                    seen.add(polled * i);
                    pq.add(polled * i);
                }
            }
        }
        
        
        return ans;
    }
}


// Solution 2. Enhanced the above to run linearly, by doing dp
// We maintain 3 pointers, ptr3, ptr4, ptr5. Indicating when last minimum number which was picked was multiple of 2 or 3 or 5
// we increment the pointer as soon as we find that the current number has 2 or 3 or 5 as its factor. 
// next time when we have to pick the number which was formed in the previous iteration, we have updated the pointers to point to latest one.
// it is same solution as PQ but smarter

class Solution {
    public int nthUglyNumber(int n) {
        int[] dp = new int[n + 1];
        int ptr2 = 1, ptr3 = 1, ptr5 = 1;
        dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            int minimumPicked = Math.min(dp[ptr2] * 2, Math.min(dp[ptr3] * 3, dp[ptr5] * 5));
            dp[i] = minimumPicked;
            
            if (dp[ptr2] * 2 == minimumPicked) {
                ptr2++;
            }
            if (dp[ptr3] * 3 == minimumPicked) {
                ptr3++;
            }
            if (dp[ptr5] * 5 == minimumPicked) {
                ptr5++;
            }
        }
        
        return dp[n];
    }
}
