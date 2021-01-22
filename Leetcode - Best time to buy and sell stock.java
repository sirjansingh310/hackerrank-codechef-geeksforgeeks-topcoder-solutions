//best time to buy and sell stock Leetcode. 
//https://leetcode.com/explore/featured/card/top-interview-questions-easy/97/dynamic-programming/572/
class Solution {
    public int maxProfit(int[] prices) {
        int cheapestTillNow = prices[0];
        int maxProfit = 0;
        
        for (int i = 1; i < prices.length; i++) {
            maxProfit = Math.max(maxProfit, prices[i] - cheapestTillNow);
            cheapestTillNow = Math.min(cheapestTillNow, prices[i]);
        }
        
        return maxProfit;
    }
}
