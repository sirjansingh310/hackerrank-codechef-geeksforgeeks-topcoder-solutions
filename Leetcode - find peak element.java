// https://leetcode.com/problems/find-peak-element/
// brute force - O(N)
// With given conditions for the problem, we can always find a peak and return any peak
// A peak is an element which is strictly greater than its neighbours 
// Think if we have one peak only in the whole array
// if our mid in binary search is more then its right element, means we are in decent slope
// our peak is on the left for us, so we move our search space to left.

// at last low will be the peak.(even if the array is sorted in strictly ascending order, elements outside the array are -INF according the problem)

// refer this image: https://leetcode.com/problems/find-peak-element/Figures/162/Find_Peak_Case3.PNG
class Solution {
    public int findPeakElement(int[] nums) {
        int low = 0, high = nums.length - 1;
        
        while (low < high) {
            int mid = (low + high) / 2;
            
            if (nums[mid] > nums[mid + 1]) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        
        return low;
    }
}
