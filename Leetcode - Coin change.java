// https://leetcode.com/explore/challenge/card/march-leetcoding-challenge-2021/589/week-2-march-8th-march-14th/3668/
// Minimum number of coins required to make a particular amount, from an array of coins(supply of each coin is unlimited)
class Solution {
    private Map<Integer, Integer> memo = new HashMap<>();
    private int recur(int[] coins, int amount) {
        if (amount < 0) {
            return Integer.MAX_VALUE;
        }
        if (amount == 0) {
            return 0;
        }
        if (memo.containsKey(amount)) {
            return memo.get(amount);
        }
        int best = Integer.MAX_VALUE;
        for (int coin : coins) {
            if (amount - coin < 0) {
                break;
            }
            int newCase = recur(coins, amount - coin);
            if (newCase < Integer.MAX_VALUE) {
                best = Math.min(best, newCase + 1);
            }
        }
        memo.put(amount, best);
        return best;
    }
    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        int result = recur(coins, amount);
        return result == Integer.MAX_VALUE ? - 1 : result;
    }
}



class Solution {
    public int coinChange(int[] coins, int amount) {
        if (amount == 0 || coins.length == 0) {
            return 0;
        } 
        Arrays.sort(coins);
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        for (int i = 0; i < coins.length; i++) {
            if (coins[i] <= amount) {
                dp[coins[i]] = 1;
            }
        }
        
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i && dp[i - coins[j]] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - coins[j]]);
                }
            }
        }
        
        return dp[amount] != Integer.MAX_VALUE ? dp[amount] : -1;
    }
}
