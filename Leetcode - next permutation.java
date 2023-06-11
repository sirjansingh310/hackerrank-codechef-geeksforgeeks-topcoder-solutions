// https://leetcode.com/problems/next-permutation/description/
class Solution {
    public void nextPermutation(int[] nums) {
        if (nums.length == 1) {
            return;
        } else if (nums.length == 2) {
            swap(nums, 0, 1);
            return;
        }

        int first = nums.length - 2;
        while (first >= 0 && nums[first] >= nums[first + 1]) {
            first--;
        }

        if (first == -1) {
            reverse(nums, 0);
        } else {
            int justBiggest = nums.length - 1;
            while (nums[justBiggest] <= nums[first]) {
                justBiggest--;
            }
            swap(nums, first, justBiggest);
            reverse(nums, first + 1);
        }
    }

    private void swap(int[] nums, int first, int second) {
        int t = nums[first];
        nums[first] = nums[second];
        nums[second] = t;
    }

    private void reverse(int[] nums, int index) {
        int low = index, high = nums.length - 1;
        while (low <= high) {
            swap(nums, low, high);
            low++;
            high--;
        }
    }
}
