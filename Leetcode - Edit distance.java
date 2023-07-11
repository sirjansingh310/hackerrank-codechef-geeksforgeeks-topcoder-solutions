class Solution {
    public int minDistance(String word1, String word2) {
        // dp[i][j] meaning min cost to match str1[0...i] to str2[0...j]
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
       
        // base case if str 2 is empty, remove all chars from str1
        for (int i = 0; i <= word1.length(); i++) {
            dp[i][0] = i;
        }

        // base case if str 1 is empty, insert all chars from str2
        for (int i = 0; i <= word2.length(); i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int insert = 1 + dp[i][j - 1];
                    int delete = 1 + dp[i - 1][j];
                    int replace = 1 + dp[i - 1][j - 1];
                    dp[i][j] = Math.min(insert, Math.min(delete, replace));
                }
            }
        }

        return dp[word1.length()][word2.length()];
    }
}
