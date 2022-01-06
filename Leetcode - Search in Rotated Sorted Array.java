// Leetcode - Search in Rotated Sorted Array
// O(logN), 2 times binary search. We can modify the if conditions and do in one go too.
// https://leetcode.com/problems/search-in-rotated-sorted-array/
class Solution {
    public int search(int[] nums, int target) {
        int firstNumberIndex = 0;
        int low = 0, high = nums.length - 1;
        
        while (low <= high) {
            int mid = (low + high) / 2;
            if (mid + 1 < nums.length && nums[mid] > nums[mid + 1]) {
                firstNumberIndex = mid + 1;
                break;
            } else if (mid > 0 && nums[mid - 1] > nums[mid]) {
                firstNumberIndex = mid;
                break;
            } else if (nums[low] < nums[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        
        if (target <= nums[nums.length - 1]) {
            low = firstNumberIndex;
            high = nums.length - 1;
        } else {
            low = 0;
            high = firstNumberIndex;
        }
        
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (target > nums[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        
        return -1;
    }
}
