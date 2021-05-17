//https://leetcode.com/explore/challenge/card/may-leetcoding-challenge-2021/598/week-1-may-1st-may-7th/3732/
class Solution {
    public int jump(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, Integer.MAX_VALUE - 1);
        
        dp[nums.length - 1] = 0;
        
        for (int i = nums.length - 2; i >=0 ;i--) {
            for (int j = 1; j <= nums[i]; j++) {
                if (i + j >= 0 && i + j < nums.length) {
                    dp[i] = Math.min(dp[i], dp[i + j] + 1);
                }
            }
        }
        
        return dp[0];
    }
}
