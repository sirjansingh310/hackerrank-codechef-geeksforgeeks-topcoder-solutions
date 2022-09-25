// https://leetcode.com/problems/decode-ways/
class Solution {
    public int numDecodings(String s) {
        int[] dp = new int[s.length() + 1];
        dp[s.length()] = 1; // base case, when string is empty
        
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                continue;
            }
            dp[i] = dp[i + 1]; // number of ways when single digit is considered
            if (i <= s.length() - 2) {
                String twoDigit = "" + s.charAt(i) + s.charAt(i + 1);
                if (Integer.parseInt(twoDigit) <= 26) {
                    dp[i] += dp[i + 2];//number of ways when two digits are considered
                }
            }
        }
        
        
        return dp[0];
    }
}
