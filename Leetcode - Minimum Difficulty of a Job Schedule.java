// https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule/
class Solution {
    public int minDifficulty(int[] jobDifficulty, int d) {
        if (jobDifficulty.length < d) {
            return -1;
        }

        int[][] dp = new int[jobDifficulty.length][d + 1];
        for (int[] arr : dp) {
            Arrays.fill(arr, -1);
        }
        return recur(jobDifficulty, 0, d, dp);
    }

    private int recur(int[] jobDifficulty, int cutToProcess, int daysLeft, int[][] dp) {
        if (daysLeft == 0 && cutToProcess == jobDifficulty.length) {
            return 0;
        } else if (daysLeft == 0 || cutToProcess == jobDifficulty.length) {
            return Integer.MAX_VALUE;
        }

        if (dp[cutToProcess][daysLeft] != -1) {
            return dp[cutToProcess][daysLeft];
        }

        int maxJobTillNow = Integer.MIN_VALUE;
        int result = Integer.MAX_VALUE;

        // try all indexes to make a cut
        for (int cut = cutToProcess; cut < jobDifficulty.length; cut++) {
            maxJobTillNow = Math.max(maxJobTillNow, jobDifficulty[cut]);
            int nextResult = recur(jobDifficulty, cut + 1, daysLeft - 1, dp);
            if (nextResult != Integer.MAX_VALUE) {
                result = Math.min(result, nextResult + maxJobTillNow);
            }
        }

        dp[cutToProcess][daysLeft] = result;
        return result;
    }
}
