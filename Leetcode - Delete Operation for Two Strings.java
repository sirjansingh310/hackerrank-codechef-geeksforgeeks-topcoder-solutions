// https://leetcode.com/problems/delete-operation-for-two-strings/
class Solution {
    public int minDistance(String word1, String word2) {
        // the two words will be same with least deletions, if we make them their longest common subsequence 
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        
        for (int i = word1.length() - 1; i >= 0; i--) {
            for (int j = word2.length() - 1; j >= 0; j--) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }
        
        return word1.length() + word2.length() - (2 * dp[0][0]);
    }
}
