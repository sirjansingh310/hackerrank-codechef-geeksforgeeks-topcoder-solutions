//https://leetcode.com/explore/featured/card/may-leetcoding-challenge-2021/598/week-1-may-1st-may-7th/3734/
class Solution {
    public int minDistance(String word1, String word2) {
        // this problem directly translates to longest common subsequence
        
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        
        for (int i = word1.length(); i >= 0; i--) {
            for (int j = word2.length(); j >= 0; j--) {
                if (i == word1.length() || j == word2.length()) {
                    continue;
                }
                
                if (word1.charAt(i) == word2.charAt(j)) {
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                } else {
                    dp[i][j] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
            }
        }
        
       int toDelete = word1.length() - dp[0][0] + word2.length() - dp[0][0];
       return toDelete; 
    }
}
