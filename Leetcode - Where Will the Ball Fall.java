// https://leetcode.com/problems/where-will-the-ball-fall/
class Solution {
    public int[] findBall(int[][] grid) {
        int n = grid[0].length;
        int[] ans = new int[n];
        
        for (int i = 0; i < n; i++) {
            int currentRow = 0, currentCol = i;
            
            while (currentRow < grid.length) {
                if (isStuck(grid, currentRow, currentCol, n)) {
                    ans[i] = -1;
                    break;
                } else {
                    if (grid[currentRow][currentCol] == 1) {
                        currentCol++;
                    } else {
                        currentCol--;
                    }
                    
                    currentRow++;
                    ans[i] = currentCol;
                }
            }
        }
        
        return ans;
    }
    
    private boolean isStuck(int[][] grid, int currentRow, int currentCol, int n) {
        return (grid[currentRow][currentCol] == 1 && currentCol == n - 1) ||  (grid[currentRow][currentCol] == 1 && grid[currentRow][currentCol + 1] == -1) || (grid[currentRow][currentCol] == -1 && currentCol == 0) ||  
            (grid[currentRow][currentCol] == -1 && grid[currentRow][currentCol - 1] == 1);
            
    }
}
