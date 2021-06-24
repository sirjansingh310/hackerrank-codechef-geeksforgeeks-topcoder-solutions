// https://leetcode.com/explore/challenge/card/june-leetcoding-challenge-2021/606/week-4-june-22nd-june-28th/3790/
class Solution {
    // we can reach a point i, j in m ways where m <= maxMove. Since we can visit a point
    // anytime and there is no visited array / restriction of visit one time only. A BFS Would time out since we are making so many moves(no visited) without cache
  
    
    private final Map<String, Integer> memo = new HashMap<>();
    private final int[] rowMoves = {-1, 1, 0, 0};
    private final int[] colMoves = {0, 0, 1, -1};
    private final int MOD = 1000000007;
    
    public int findPaths(int m, int n, int maxMove, int startRow, int startCol) {
        String key = startRow + "_" + startCol + "_" + maxMove;
        if (maxMove < 0) {
            return 0; // no more moves left;
        }
        
        if (startRow < 0 || startCol < 0 || startRow >= m || startCol >= n) {
            return 1; // out of bounds
        }
        
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        
        int count = 0;
    
        for (int i = 0; i < 4; i++) {
            int nextRow = startRow + rowMoves[i];
            int nextCol = startCol + colMoves[i];
            count = (count % MOD + findPaths(m, n, maxMove - 1, nextRow, nextCol) % MOD) % MOD;
        }
        
        memo.put(key, count);
        return count;
    }
}
