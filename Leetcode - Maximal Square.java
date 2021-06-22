// Brute force
// for every matrix[i][j] which is 1, we check next row and col until any one is zero. 
// O(MN)^2 (when all elements in 2d Matrix are 1)
class Solution {
    public int maximalSquare(char[][] matrix) {
        int max = 0;
        int n = matrix.length, m = matrix[0].length;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == '1') {
                    int count = 1;
                    
                    int row = i + 1, col = j + 1;
                
                    while (row < n && col < m) {
                        boolean allOne = true;
                        for (int k = i; k <= row; k++) {
                            if (matrix[k][col] != '1') {
                                allOne = false;
                                break;
                            }
                        }
                        
                        if (!allOne) {
                            break;
                        }
                        
                        for (int k = j; k <= col; k++) {
                            if (matrix[row][k] != '1') {
                                allOne = false;
                                break;
                            }
                        }
                        
                        if (!allOne) {
                            break;
                        }
                        
                        count = (row - i + 1) * (row - i + 1);
                        row++;
                        col++;
                    }
                    
                    max = Math.max(max, count);
                }
            }
        }
        
        return max;
    }
}


// Dynamic Programming
// Think you are at bottom right of a square with all ones. the row before you has this property, the col before you, and the row and col before you. Represented
// By dp 2d array
// We calcuate maxside length, and the result will be maxsidelen * maxsidelen
// your contibution will be 1 + min of all the 3 dp's
// This image explains it very well
// https://leetcode.com/media/original_images/221_Maximal_Square.PNG?raw=true

// O(MN)
class Solution {
    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
        int[][] dp = new int[rows + 1][cols + 1];
        int maxsqlen = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (matrix[i-1][j-1] == '1'){
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                    maxsqlen = Math.max(maxsqlen, dp[i][j]);
                }
            }
        }
        return maxsqlen * maxsqlen;
    }
}
