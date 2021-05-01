//https://leetcode.com/explore/featured/card/april-leetcoding-challenge-2021/595/week-3-april-15th-april-21st/3715/
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        List<Integer> lastRow = triangle.get(triangle.size() - 1);
        int[] dp = new int[lastRow.size()];
        
        for (int i = 0; i < lastRow.size(); i++) {
            dp[i] = lastRow.get(i);
        }
        
        for (int i = triangle.size() - 2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j + 1]);
            }
        }
        
        return dp[0];
    }
}
