// https://leetcode.com/problems/stone-game-iii/
class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int[] dp = new int[stoneValue.length + 1];
        // result will be from dp[0]. It contains diffs from "Alice" scores
        // and "Bob" scores. If it is positive, it means best alice score > bob score.
        
        for (int i = stoneValue.length - 1; i >= 0; i--) {
            dp[i] = stoneValue[i] - dp[i + 1];
            if (i + 2 < dp.length) { 
                dp[i] = Math.max(dp[i], stoneValue[i] + stoneValue[i + 1] - dp[i + 2]);
            }
            if (i + 3 < dp.length) {
                dp[i] = Math.max(dp[i], stoneValue[i] + stoneValue[i + 1] + stoneValue[i + 2] - dp[i + 3]);
            }
        }
        
        if (dp[0] == 0) {
            return "Tie";
        } else if (dp[0] > 0) {
            return "Alice";
        } else {
            return "Bob";
        }
    }
}
