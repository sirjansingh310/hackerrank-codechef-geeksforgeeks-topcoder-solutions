class Solution {
    // When we want to go from a -> b and the path is (a -> c) + (c - d) + (d -> b)
    // the actual result is simply (b - a), note the terms cancel out.
    // so here instead for looking for a good element to place a jump on either on the right side elements, or the bottom elements,
    // just move one place right or one place down because the path/number of steps don't matter, just the original and final element in matrix
    // such that final - original is maximum
    
    public int maxScore(List<List<Integer>> grid) {
        int m = grid.size(), n = grid.get(0).size();
        int[][] dp = new int[m][n];
        int result = Integer.MIN_VALUE;

        // going down
        for (int i = m - 2; i >= 0; i--) {
            dp[i][n - 1] = grid.get(i + 1).get(n - 1) - grid.get(i).get(n - 1); // just move down
            dp[i][n - 1] = Math.max(dp[i][n - 1], dp[i + 1][n - 1] + grid.get(i + 1).get(n - 1) - grid.get(i).get(n - 1)); // or move down + stop at whatever place the down element gets its maximum
            result = Math.max(result, dp[i][n - 1]);
        }

        // going right
        for (int i = n - 2; i >= 0; i--) {
            dp[m - 1][i] = grid.get(m - 1).get(i + 1) - grid.get(m - 1).get(i);
            dp[m - 1][i] = Math.max(dp[m - 1][i], dp[m - 1][i + 1] + grid.get(m - 1).get(i + 1) - grid.get(m - 1).get(i));
            result = Math.max(result, dp[m - 1][i]);
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                dp[i][j] = grid.get(i + 1).get(j) - grid.get(i).get(j);
                dp[i][j] = Math.max(dp[i][j], dp[i + 1][j] + grid.get(i + 1).get(j) - grid.get(i).get(j));
                dp[i][j] = Math.max(dp[i][j], dp[i][j + 1] + grid.get(i).get(j + 1) - grid.get(i).get(j));
                dp[i][j] = Math.max(dp[i][j], grid.get(i).get(j + 1) - grid.get(i).get(j));
                result = Math.max(result, dp[i][j]);
            }
        }

        return result;
    }
}
