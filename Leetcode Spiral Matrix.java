// https://leetcode.com/problems/spiral-matrix/
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int top = 0, bottom = matrix.length - 1, right = matrix[0].length - 1, left = 0;
        List<Integer> spirals = new ArrayList<>();
        
        while (true) {
            for (int i = left; i <= right; i++) {
                spirals.add(matrix[top][i]);
            }
            top++;
            if (top > bottom) {
                break;
            }
            for (int i = top; i <= bottom; i++) {
                spirals.add(matrix[i][right]);
            }
            right--;
            if (right < left) {
                break;
            }

            for (int i = right; i >= left; i--) {
                spirals.add(matrix[bottom][i]);
            }
            bottom--;
            if (top > bottom) {
                break;
            }

            for (int i = bottom; i >= top; i--) {
                spirals.add(matrix[i][left]);
            }
            left++;
            if (right < left) {
                break;
            }
        }
        
        return spirals;
    }
}
