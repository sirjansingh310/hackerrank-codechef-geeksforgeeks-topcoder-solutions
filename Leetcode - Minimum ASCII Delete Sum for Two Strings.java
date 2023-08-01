// https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/description/
class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        // similar to LCS, but with weights
        int[][] cost = new int[s1.length() + 1][s2.length() + 1];// cost[i][j] represents considering string s1 till index i and s2 till index j

        // when s1 is deleted completely 
        for (int i = 1; i <= s1.length(); i++) {
            cost[i][0] = cost[i - 1][0] + s1.charAt(i - 1);
        }

        // when s2 is deleted completely 
        for (int i = 1; i <= s2.length(); i++) {
            cost[0][i] = cost[0][i - 1] + s2.charAt(i - 1);
        }

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    cost[i][j] = cost[i - 1][j - 1];
                } else {
                    int deleteS1 = s1.charAt(i - 1) + cost[i - 1][j];
                    int deleteS2 = s2.charAt(j - 1) + cost[i][j - 1];
                    cost[i][j] = Math.min(deleteS1, deleteS2);
                }
            }
        }

        return cost[s1.length()][s2.length()];
    }
}
