// https://leetcode.com/explore/challenge/card/july-leetcoding-challenge-2021/608/week-1-july-1st-july-7th/3801/
class Solution {
    public int maxSumSubmatrix(int[][] matrix, int limit) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] prefixSum = new int[n][m];
        
        prefixSum[0][0] = matrix[0][0];
        
        for (int i = 1; i < m; i++) {
            prefixSum[0][i] = matrix[0][i] + prefixSum[0][i - 1];
        }
        
        for (int i = 1; i < n; i++) {
            prefixSum[i][0] = matrix[i][0] + prefixSum[i - 1][0];
        }
        
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                prefixSum[i][j] = matrix[i][j] + prefixSum[i - 1][j] + prefixSum[i][j - 1] - prefixSum[i - 1][j - 1]; // need to subtract prefixSum[i - 1][j - 1] since it is considered in
                // both prefixSum[i - 1][j] and prefixSum[i][j - 1]
            }
        }
        
        // now that we have precomputed sums from [0, 0] to any i, j, lets
        // iterate over all possible rectagles and find the sum in O(1) time, instead of redoing 
        // the sum computation
        
        int bestSum = Integer.MIN_VALUE;
        // rect top left is i, j. bottom right k, l
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = i; k < n; k++) {
                    for (int l = j; l < m; l++) {
                        int sum = prefixSum[k][l], deductCount = 0;
                        if (i - 1 >= 0) {
                             sum -= prefixSum[i - 1][l]; // remove extra rectangle before i row
                             deductCount++;
                        }
                        if (j - 1 >= 0) {
                            sum -= prefixSum[k][j - 1]; // remove extra rect before j col
                            deductCount++;
                        }
                        
                        if (deductCount == 2) { // same logic as prefixSum, we might add twice there,
                            // and we might subtract twice here.
                            sum += prefixSum[i - 1][j - 1];
                        }
                        if (sum <= limit) {
                            bestSum = Math.max(sum, bestSum);
                        }
                    }
                }
            }
        }
        
        /*
        
        [5,-4,-3,4]
        [-3,-4,4,5]
        [5,1,5,-4]
        */
        
        return bestSum;
    }
}
