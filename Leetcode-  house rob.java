// https://leetcode.com/explore/item/576

class Solution {
    public int rob(int[] nums) {
        if (nums.length == 0){
            return 0;
        }
        int[][] dp = new int[nums.length][2]; // money we made by robbing house i and not robbing house i and skipping it for adj one
        
        dp[0][0] = nums[0];
        dp[0][1] = 0;
        
        for (int i = 1; i < nums.length; i++) {
            dp[i][0] = nums[i] + dp[i - 1][1]; // i - 1 did not rob, rob i
            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1]);// did not rob i, so best till now is max of prev computation
        }
        
        return Math.max(dp[nums.length - 1][0], dp[nums.length - 1][1]);
    }
}
