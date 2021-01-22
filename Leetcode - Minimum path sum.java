//https://leetcode.com/problems/minimum-path-sum/
// Asked in microsoft as per leetcode discussions.

// DP Approach 
class Solution {
    public int minPathSum(int[][] grid) {
        int rowSize = grid.length, colSize = grid[0].length;
        
        int[][] dp = new int[rowSize][colSize]; // min cost to reach i, j
        dp[0][0] = grid[0][0];
        
        // row 0 can be explored by only going right
        for (int j = 1; j < colSize; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        // col 0 can be explored by only going down
        for (int i = 1; i < rowSize; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        // for rest, do DP
        for (int i = 1; i < rowSize; i++) {
            for (int j = 1; j < colSize; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i -1][j], dp[i][j - 1]);
            }
        }
        
        return dp[rowSize - 1][colSize - 1];
    }
}

// Brute force, try all with DFS
/*
class Solution {
    private int minPathSum = Integer.MAX_VALUE;
    private int rowSize, colSize;
    
    private boolean isValid(int row, int col) {
        return row >= 0 && row < rowSize && col >= 0 && col < colSize;
    }
    
    public void dfs(int[][] grid, int startRow, int startCol, int endRow, int endCol, int sum) {
        if (startRow == endRow && startCol == endCol) {
            minPathSum = Math.min(sum, minPathSum);
            return;
        }
        
        if (isValid(startRow, startCol + 1)) {
            dfs(grid, startRow, startCol + 1, endRow, endCol, sum + grid[startRow][startCol + 1]);
        }
        
        if (isValid(startRow + 1, startCol)) {
            dfs(grid, startRow + 1, startCol, endRow, endCol, sum + grid[startRow + 1][startCol]);
        }
        return;
    }
    public int minPathSum(int[][] grid) {
        rowSize = grid.length;
        colSize = grid[0].length;
        dfs(grid, 0, 0, rowSize - 1, colSize - 1, grid[0][0]);
        return minPathSum;
    }
}

*/
