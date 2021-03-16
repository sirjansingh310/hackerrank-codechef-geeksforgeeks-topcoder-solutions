//https://leetcode.com/explore/challenge/card/march-leetcoding-challenge-2021/590/week-3-march-15th-march-21st/3674/
// Here we can buy and sell as many times, with a transaction fee. 
// return the max profit possible

class Solution {
    private Map<String, Integer> profitFromDayToLastDay = new HashMap<>(); // memoization. Store from a particular day, if we were to buy it or sell it, or skip it, what is the best profit from this day to last day
    private int recur(int[] prices, int index, boolean stockBought, int sellingFee) {
        String key = index + "_" + stockBought;
        if (profitFromDayToLastDay.containsKey(key)) {
            return profitFromDayToLastDay.get(key);
        }
        if (index == prices.length) {
            if (stockBought && prices[prices.length - 1] < sellingFee) {
                return 0; // don't sell at last day
            } else if (stockBought){
                return prices[prices.length - 1] - sellingFee;// profit from selling
            } else {
                return 0;
            }
        }
        int buyProfit = -1, sellProfit = -1, skipProfit = -1;
        if (stockBought) { // sell, don't check for prices[i] < sellingFee like base case(last day)
            // because selling now can yeild to a much bigger overall profit
            sellProfit = prices[index] - sellingFee + recur(prices, index + 1, false, sellingFee);
        } else { // buy 
            buyProfit = recur(prices, index + 1, true, sellingFee) - prices[index];
        }
        skipProfit = recur(prices, index + 1, stockBought, sellingFee); // skip that day
        int bestProfit =  Math.max(buyProfit, Math.max(sellProfit, skipProfit));
        profitFromDayToLastDay.put(key, bestProfit);
        return bestProfit;
    }
    public int maxProfit(int[] prices, int fee) {
        return recur(prices, 0, false, fee);
    }
}
