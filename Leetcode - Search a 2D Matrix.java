// coded in google doc
// https://leetcode.com/problems/binary-search/
// The whole matrix is properly sorted row and col wise, meaning if we were to join all rows to flatten the structure, the flatten array would already be sorted

// Naive solution would be to do binary search on each row -> O(MlogN) where m rows, n cols
// But since we know it is completely soreted, i.e row above last element will be less than row below first element
// we can do binary search first rows, check which row will have the element. (by checking first and last elements in that col, check the bounds)
// once we know the exact row, we simply do binary search of columns on that row to find the element
// time complexity : O(logM) + O(logN) or O(logN) when m == n
// below solution is much consise version of that where we move rows or cols in one loop

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
