// https://leetcode.com/problems/house-robber-ii/
class Solution {
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        } else if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        
        
        int[] withLastHouseRob = new int[nums.length];
        withLastHouseRob[nums.length - 1] = nums[nums.length - 1];
        withLastHouseRob[nums.length - 2] = Math.max(withLastHouseRob[nums.length - 1], nums[nums.length - 2]);
        
        for (int i = nums.length - 3; i > 0; i--) {
            withLastHouseRob[i] = Math.max(withLastHouseRob[i + 1], nums[i] + withLastHouseRob[i + 2]);
        }
        
        withLastHouseRob[0] = withLastHouseRob[1]; // as connected in circle, if we robbed last house, we cannot rob first house.
        
        int[] withLastHouseNotRob = new int[nums.length];
        withLastHouseNotRob[nums.length - 1] = 0;
        withLastHouseNotRob[nums.length - 2] = nums[nums.length - 2];
        
        for (int i = nums.length - 3; i > 0; i--) {
            withLastHouseNotRob[i] = Math.max(withLastHouseNotRob[i + 1], nums[i] + withLastHouseNotRob[i + 2]);
        }
        
        withLastHouseNotRob[0] = withLastHouseNotRob[2] + nums[0]; // since we can rob it, as we didn't rob last house
        
        return Math.max(withLastHouseRob[0], withLastHouseNotRob[0]);
        
    }
}
