// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
class Solution {
    private Map<String, Integer> map;
    
    private int recur(int[] prices, int index, boolean stockInHand) {
        String key = stockInHand + "_"+ index;
        if (map.containsKey(key)) {
            return map.get(key);
        }
        if (index == prices.length - 1) {
            if (stockInHand) {
                return prices[prices.length - 1];
            } else {
                return 0;
            }
        } else if (index >= prices.length) {
            return 0;
        }
        
        int best = Integer.MIN_VALUE;
        if (stockInHand) {
            // sell with cooldown
            best = Math.max(best, recur(prices, index + 2, false) + prices[index]);
        } else {
            // buy 
            best = Math.max(best, recur(prices, index + 1, true) - prices[index]);
        }
        // skip current day
        best = Math.max(best, recur(prices, index + 1, stockInHand));
        map.put(key, best);
        return best;
    }
    public int maxProfit(int[] prices) {
        map = new HashMap<>();
        return recur(prices, 0, false);
    }
}
