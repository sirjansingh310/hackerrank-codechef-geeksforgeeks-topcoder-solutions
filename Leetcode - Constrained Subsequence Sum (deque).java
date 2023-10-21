// https://leetcode.com/problems/constrained-subsequence-sum/description/
// original DP solution, TLE
class Solution {
    public int constrainedSubsetSum(int[] nums, int k) {
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            dp[i] = nums[i];
        }
        int max = Integer.MIN_VALUE;
        for (int i = nums.length - 2; i >= 0; i--) {
            for (int j = i + 1; j <= i + k && j < nums.length; j++) {
                dp[i] = Math.max(dp[i], nums[i] + dp[j]);
            }
        }

        for (int i = 0; i < dp.length; i++) {
            max = Math.max(dp[i], max);
        }
        return max;
    }
}

// improved solution with deque
class Solution {
    public int constrainedSubsetSum(int[] nums, int k) {
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            dp[i] = nums[i];
        }

        Deque<Integer> deque = new LinkedList<>();// for given window of size K, 
        // get the maximum element. This way we avoid the commented for loop. Note: deque is of indexes to maintain window. 
        // Deque is monotonic in nature here, the maximum we want is on front. 
        // Elements are added from back, and the useless ones which are smaller than current(which don't contribute) are also removed. 
        // This means the "freshness" of an element is from right to left on the dequeue. So when queue size is more than k and we want to cleanup, we do from front. 


        deque.add(dp.length - 1);
        int max = Integer.MIN_VALUE;
        for (int i = nums.length - 2; i >= 0; i--) {
            // for (int j = i + 1; j <= i + k && j < nums.length; j++) {
            //     dp[i] = Math.max(dp[i], nums[i] + dp[j]);
            // }
            
            // kick out useless values
            while (!deque.isEmpty() && deque.peekFirst() > i + k) {
                deque.removeFirst();
            }

            dp[i] = Math.max(dp[i], nums[i] + dp[deque.peekFirst()]);
            // remove useless comparing to current in window
            while (!deque.isEmpty() && dp[deque.peekLast()] < dp[i]) {
                deque.removeLast();
            }
            deque.addLast(i);

        }

        for (int i = 0; i < dp.length; i++) {
            max = Math.max(dp[i], max);
        }
        return max;
    }
}
