
// O(N^2logN)
//https://leetcode.com/explore/challenge/card/july-leetcoding-challenge-2021/611/week-4-july-22nd-july-28th/3828/
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int minDiff = Integer.MAX_VALUE, result = -1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (i != j) {
                    int sum = nums[i] + nums[j];
                    int closestIdx = binarySearch(nums, target - sum);
                    int diff = Math.abs(target - (nums[i] + nums[j] + nums[closestIdx]));
                    if (diff < minDiff && closestIdx != i && closestIdx != j) {
                        minDiff = diff;
                        result = nums[i] + nums[j] + nums[closestIdx];
                    }
                }
            }
        }
        
        return result;
    }
    
    
    private int binarySearch(int[] nums, int target) {
        if (target < nums[0]) {
            return 0;
        } else if (target > nums[nums.length - 1]) {
            return nums.length - 1;
        }
        int low = 0, high = nums.length - 1;
        
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return nums[low] - target < target - nums[high] ? low : high;
    }
}

// O(N^2)
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int result = nums[0] + nums[1] + nums[nums.length - 1];
        
        for (int i = 0; i < nums.length; i++) {
            // two pointer now
            int low = i + 1, high = nums.length - 1;
            while (low < high) {
                int sum = nums[low] + nums[high] + nums[i];
                if (sum > target) { // get as close to target as possible
                    high--;
                } else {
                    low++;
                }
                if (Math.abs(target - result) > Math.abs(target - sum)) {
                    result = sum;
                }
            }
        }
        
        return result;
    }
}
