// https://leetcode.com/explore/challenge/card/august-leetcoding-challenge-2021/617/week-5-august-29th-august-31st/3958/
// TC: O(logN) using modified version of binary search

class Solution {
    public int findMin(int[] nums) {
        int low = 0, high = nums.length - 1;
        
        while (low <= high) {
            int mid = (low + high) / 2;
            if (mid > 0 && nums[mid - 1] > nums[mid]) {
                return nums[mid];
            } else if (mid < nums.length - 1 && nums[mid + 1] < nums[mid]) {
                return nums[mid + 1];
            }
            if (nums[mid] > nums[low]) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return nums[0];
    }
}
