// https://leetcode.com/problems/set-matrix-zeroes/
//Two solutions, one with O(M * N) space and one with constant space, O(1)
// O(M * N) solution is much cleaner, easier to understand.
// TC: O(M * N), SC: O(M + N)
class Solution {
    public void setZeroes(int[][] matrix) {
        int rowCount = matrix.length, colCount = matrix[0].length;
        boolean[] rowHasZero = new boolean[rowCount];
        boolean[] colHasZero = new boolean[colCount];
        
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                if (matrix[i][j] == 0) {
                    rowHasZero[i] = true;
                    colHasZero[j] = true;
                }
            }
        }
        
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                if (rowHasZero[i] || colHasZero[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
        return;
    }
}


// TC: O(M*N)
// SC: O(1) => mark 1st element of row or col if that row is bad or col is bad or both. (By bad we mean, contains zero)
class Solution {
    public void setZeroes(int[][] matrix) {
        int rowCount = matrix.length, colCount = matrix[0].length;
        final int BAD_ROW = 99999;
        final int BAD_COL = 99998;
        final int BAD_ROW_COL = 99997;
        
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                if (matrix[i][j] == 0) {
                    if (matrix[i][0] == BAD_COL || matrix[i][0] == BAD_ROW_COL) {
                        matrix[i][0] = BAD_ROW_COL;
                    } else {
                        matrix[i][0] = BAD_ROW;
                    }
                    
                    if (matrix[0][j] == BAD_ROW || matrix[0][j] == BAD_ROW_COL) {
                        matrix[0][j] = BAD_ROW_COL;
                    } else {
                        matrix[0][j] = BAD_COL;
                    }
                }
            }
        }
        
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                if (matrix[i][j] == BAD_ROW_COL || matrix[i][j] == BAD_ROW || matrix[i][j] == BAD_COL)
                {
                    continue;
                }
                
                if (matrix[i][0] == BAD_ROW || matrix[i][0] == BAD_ROW_COL) {
                    matrix[i][j] = 0;
                }
                
                if (matrix[0][j] == BAD_COL || matrix[0][j] == BAD_ROW_COL) {
                    matrix[i][j] = 0;
                }
            }
        }

        
        for (int i = 0; i < rowCount; i++) {
            if (matrix[i][0] == BAD_ROW_COL || matrix[i][0] == BAD_ROW) {
                matrix[i][0] = 0;
            }
        }
        
        for (int j = 0; j < colCount; j++) {
            if (matrix[0][j] == BAD_ROW_COL || matrix[0][j] == BAD_COL) {
                matrix[0][j] = 0;
            }
        }
        
        return;
    }
}
