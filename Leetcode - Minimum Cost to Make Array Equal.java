// https://leetcode.com/problems/minimum-cost-to-make-array-equal/description/
// https://www.youtube.com/watch?v=8ERS_4tSx2U&ab_channel=AryanMittal video solution
class Solution {
    public long minCost(int[] nums, int[] cost) {
        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            low = Math.min(low, nums[i]);
            high = Math.max(high, nums[i]);
        }

        // ans is median if all costs are 1, 
        // we can do weighted median also, imagine every element, nums[i]
        // in nums is actually cost[i] * nums[i] times in nums, with cost 1 for
        // each element in this new expanded array. instead of physically 
        // storing such a big array, we can logically imagine with freq(sort pairs of {nums[i], cost[i]}) and loop to find the median. Once we have median, we calculate cost by looping on nums and return ans.
        // O(NLOGN) for sorting, O(N) for pairs


        // We can further optimize the above using binary search and use just
        // O(1) space, using binary search we can find the best result. The concept of median still applies, we need to converge to lowest cost. 
        
        // Intuition: Our cost plotted will be a curve, with best cost at lowest point
        // for straight line, simple binary search will do
        // but to find lowest point, we need to see where to convege, should we
        // go down from left to right or right to left

        // TC: O(nlogK) where K = max number of nums - min number of nums
        // SC: O(1)

        long result = 0L;
        while (low <= high) {
            int possibleElement = (low + high) / 2; // we can possibly make all in nums[i] = possibleElement


            long cost1 = getCost(nums, cost, possibleElement);
            long cost2 = getCost(nums, cost, possibleElement + 1);
            result = Math.min(cost1, cost2);

            if (cost2 < cost1) { // possibleElement + 1 is better, we can go more downhill in the graph
                low = possibleElement + 1;
            } else {
                high = possibleElement - 1; 
            }
        }

        return result;
    }

    private long getCost(int[] nums, int[] cost, int x) {
        long total = 0L;
        for (int i = 0; i < nums.length; i++) {
            total += (Math.abs(x - nums[i]) * (long)cost[i]);
        }
        return total;
    }
}
