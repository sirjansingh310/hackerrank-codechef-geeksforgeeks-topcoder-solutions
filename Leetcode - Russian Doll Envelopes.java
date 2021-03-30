//https://leetcode.com/explore/challenge/card/march-leetcoding-challenge-2021/592/week-5-march-29th-march-31st/3690/
class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (e1, e2) -> e1[0] != e2[0] ? e1[0] - e2[0] : e1[1] - e2[1]); // sort asc by width
        
        int[] dp = new int[envelopes.length];// stores how many env from pos o to i can fit in env i
        Arrays.fill(dp, 1);
        int best = 1;
        for (int i = 0; i < envelopes.length; i++) {
            for (int j = 0; j < i; j++) {
                // i will be greater in width by j as sorted asc
                if (envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            best = Math.max(best, dp[i]);
        }
        
        return best;
    }
}
