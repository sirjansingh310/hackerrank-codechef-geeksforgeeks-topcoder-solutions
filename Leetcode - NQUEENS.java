//https://leetcode.com/explore/challenge/card/may-leetcoding-challenge-2021/601/week-4-may-22nd-may-28th/3752/
class Solution {
    List<List<String>> solutions = new ArrayList<>();
    
    // go row by row, if possible, place queen, and move to next row
    private void nQueens(int row, List<int[]> placedQueens, int n) {
        if (row == n) {
            solutions.add(prettyString(placedQueens));
            return;
        }
    
        for (int i = 0; i < n; i++) {
            int[] cordinate = new int[]{row, i};
            if (isPossibleToPlace(placedQueens, cordinate)) {
                // we need a copy for next recursion call, a shallow copy is enough because
                // we are not going to touch int[] inside lists ever again. They are read only
                List<int[]> shallowCopy = new ArrayList<>(placedQueens);
                shallowCopy.add(cordinate);
                nQueens(row + 1, shallowCopy, n);
            }
        }
        return;
    }
    
    private boolean isPossibleToPlace(List<int[]> placedQueens, int[] cordinate) {
        boolean conflict = false;
        
        for (int[] queen : placedQueens) {
            // check row & col
            conflict |= (queen[0] == cordinate[0]);
            conflict |= (queen[1] == cordinate[1]);
            conflict |= (Math.abs(queen[0] - cordinate[0]) == Math.abs(queen[1] - cordinate[1]));
            
            if (conflict) {
                return false;
            }
        }
        
        return true;
    }
    
    private List<String> prettyString(List<int[]> queens) {
        int n = queens.size();
        List<String> result = new ArrayList<>();
        
        for (int[] queen : queens) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                if (i == queen[1]) {
                    sb.append("Q");
                } else {
                    sb.append(".");
                }
            }
            result.add(sb.toString());
        }
        return result;
    }
    public List<List<String>> solveNQueens(int n) {
        nQueens(0, new ArrayList<int[]>(), n);
        return solutions;
    }
}
