//https://leetcode.com/problems/ones-and-zeroes/
class Solution {
    // having one static map to avoid Memory overflow error in leetcode
    private static final int[][][] map = new int[601][101][101];    
    public int findMaxForm(String[] strs, int m, int n) {
        init();// init in fn call since map is static and will be shared accross all Leetcode tests
        int result = solve(strs, 0, m, n);
        return result;
    }
    
    private void init() {
        for (int i = 0; i < 601; i++) {
            for (int j = 0; j < 101; j++) {
                for (int k = 0; k < 101; k++) {
                    map[i][j][k] = -1;
                }
            }
        }
    }
    
    private int solve(String[] strs, int index, int m, int n) {
        
        if (map[index][m][n] != -1) {
            return map[index][m][n];
        }
        
        if (index >= strs.length) {
            map[index][m][n] = 0;
            return 0;
        }
        int[] arr = getCount(strs[index]);
        int zeroCount = arr[0];
        int oneCount = arr[1];
        
        int countWithAdd = 0, countWithSkip = 0;
        if (n - oneCount >= 0 && m - zeroCount >= 0) {
            countWithAdd = 1 + solve(strs, index + 1, m - zeroCount, n - oneCount);
        }
        countWithSkip = solve(strs, index + 1, m, n);
        
        map[index][m][n] = Math.max(countWithAdd, countWithSkip);
        return map[index][m][n];
    }
    
    private int[] getCount(String s) {
        int one = 0, zero = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                one++;
            } else {
                zero++;
            }
        }
        return new int[]{zero, one};
    }
}
