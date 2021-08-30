// https://leetcode.com/explore/challenge/card/august-leetcoding-challenge-2021/617/week-5-august-29th-august-31st/3957/
// For each operation, we are updating values from [0, 0] to [op[0], op[1]]. That means [0,0] will be updated for sure for every operation. 
// We need to find number of cells in matrix which have the maximum value, i.e same value as mat[0][0] after all updates.
// For the given operations, the minimum among the x and y coordinates will be updated in all opeations. The product of these 2 minimums will be the number of cells which
// were maximum updated.


class Solution {
    public int maxCount(int m, int n, int[][] ops) {
        int minX = m, minY = n;
        
        for (int[] op : ops) {
            minX = Math.min(minX, op[0]);
            minY = Math.min(minY, op[1]);
        }
        return minX * minY;
    }
}
