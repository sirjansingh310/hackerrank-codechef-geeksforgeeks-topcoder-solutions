//// https://leetcode.com/explore/challenge/card/january-leetcoding-challenge-2021/580/week-2-january-8th-january-14th/3603/
class Solution {
    public int minOperations(int[] nums, int x) {
        // You are given an integer array nums and an integer x. In one operation, you can either remove the leftmost or the rightmost element from the array nums and subtract its value from x. Note that this modifies the array for future operations.
        
        // instead of removing elements, try finding maximum sub array equal to sum(nums) - x
        
        int total = 0;
        for (int i = 0; i < nums.length; i++) {
            total += nums[i];
        }
        
        if (x > total) {
            return -1;
        }
        
        int left = 0, right = 0;
        int currentSum = 0, bestLength = -1;
        
        while (right < nums.length && left <= right) {
            currentSum += nums[right];
            right++;
            
            while (currentSum > total - x) {
                currentSum -= nums[left];
                left++;
            }
            
            if (currentSum == total - x) {
                bestLength = Math.max(bestLength, right - left);
            }
        }
        
        return bestLength == -1 ? -1 : nums.length - bestLength;

    }
}
