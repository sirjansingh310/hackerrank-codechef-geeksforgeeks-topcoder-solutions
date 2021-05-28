//https://leetcode.com/explore/challenge/card/may-leetcoding-challenge-2021/601/week-4-may-22nd-may-28th/3758/
class Solution {
    public int maximumUniqueSubarray(int[] nums) {
        // maximum sub array sum of unique elements
        // sliding window
        Set<Integer> set = new HashSet<>();
        int sum = 0, maxSum = nums[0], left = 0, right = 0;
        
        while (left <= right && right < nums.length) {
            sum += nums[right];
            // remove till previous seen
            while (set.contains(nums[right])) {
                set.remove(nums[left]);
                sum -= nums[left];
                left++;
            }
            // update maxSum
            maxSum = Math.max(maxSum, sum);
            // add newly added number to set
            set.add(nums[right]);
            right++;
        }
        return maxSum;
    }
}
