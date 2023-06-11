// https://www.youtube.com/watch?v=tp8JIuCXBaU&t=1211s&ab_channel=takeUforward
class Solution {
    public void sortColors(int[] nums) {
        // 0, 0, 0...          1,1,1,...              2,1,2,0,1,...        2,2,2,2,
        // 0 -> low-1(sorted) low -> mid - 1(soreted) mid -> high(unsorted) high + 1 -> n - 1(sorted)

        int low = 0, mid = 0, high = nums.length - 1;
        while (mid <= high) { // unsorted range
            if (nums[mid] == 0) { // move to first sorted range
                swap(nums, low, mid);
                low++;
                mid++;
            } else if (nums[mid] == 1) { // move to second sorted range
                mid++;
            } else if (nums[mid] == 2) { // move to third sorted range
                swap(nums, mid, high);
                high--;
            }
        }
    }

    private void swap(int[] nums, int first, int second) {
        int temp = nums[first];
        nums[first] = nums[second];
        nums[second] = temp;
    }
}
