// https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/
class Solution {
    public int numRollsToTarget(int n, int k, int target) {
        int[][] dp = new int[n + 1][target + 1];
        final int MOD = 1000000007;
        
        // for one dice roll base case
        for (int i = 1; i <= k; i++) {
            if (i <= target) {
                dp[1][i] = 1;
            }
        }
        
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= target; j++) {
                for (int m = 1; m <= k; m++) {
                    if (j - m >= 0) { // similar to coin change, if we have a way to get sum j - m with the previous roll, append that here. 
                        dp[i][j] = ((dp[i][j] % MOD) + (dp[i - 1][j - m] % MOD)) % MOD;
                    }
                }
            }
        }
        
        return dp[n][target];
    }
}
