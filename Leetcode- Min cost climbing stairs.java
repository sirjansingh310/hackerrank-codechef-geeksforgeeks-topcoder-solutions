// https://leetcode.com/explore/challenge/card/june-leetcoding-challenge-2021/603/week-1-june-1st-june-7th/3770/
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length + 1];
        
        for (int i = dp.length -2; i >= 0; i--) {
            int oneStep = dp[i + 1];
            int twoStep = i + 2 < dp.length ? dp[i + 2] : Integer.MAX_VALUE;
            dp[i] = cost[i] + Math.min(oneStep, twoStep);
        }
        return Math.min(dp[0], dp[1]);
    }
}
