// https://leetcode.com/problems/freedom-trail/description/?envType=daily-question&envId=2024-04-27
// this was fun solving

class Solution {
    public int findRotateSteps(String ring, String key) {
        int n = ring.length();
        int[][] dist = new int[n][n];
        int[][] dp = new int[ring.length() + 1][key.length() + 1];
        Map<Character, Set<Integer>> indexMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            indexMap.computeIfAbsent(ring.charAt(i), x -> new HashSet<>()).add(i);
            for (int j = i + 1; j < n; j++) {
                dist[i][j] = Math.min(Math.abs(i - j), Math.abs(n - j + i));
                dist[j][i] = dist[i][j];
            }
        }
        return dfs(ring, key, 0, 0, dist, indexMap, dp);
    }

    private int dfs(String ring, String key, int ringIdx, int keyIdx, int[][] dist, Map<Character, Set<Integer>> ringIndexMap, int[][] dp) {
        if (keyIdx == key.length()) {
            return 0;
        }

        if (dp[ringIdx][keyIdx] != 0) {
            return dp[ringIdx][keyIdx];
        }
        int minMoves = Integer.MAX_VALUE;
        for (int i : ringIndexMap.get(key.charAt(keyIdx))) {
            int moves = dist[ringIdx][i] + 1; 
            moves += dfs(ring, key, i, keyIdx + 1, dist, ringIndexMap, dp);
            minMoves = Math.min(moves, minMoves);
        }
        dp[ringIdx][keyIdx] = minMoves;
        return minMoves;
    }
}
