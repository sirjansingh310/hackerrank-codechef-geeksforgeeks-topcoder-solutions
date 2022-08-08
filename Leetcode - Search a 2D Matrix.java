// coded in google doc
// https://leetcode.com/problems/binary-search/
// The whole matrix is properly sorted row and col wise, meaning if we were to join all rows to flatten the structure, the flatten array would already be sorted
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int currentRow = matrix.length - 1;
        int currentCol = 0;

        while (currentRow >= 0 && currentCol < matrix[0].length) {
            int midElement = matrix[currentRow][currentCol];
            if (midElement == target) {
                return true;
            } else if (midElement > target) {
                currentRow--;
            } else {
                currentCol++;
            }
        }

        return false;
    }
}
