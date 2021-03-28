//https://leetcode.com/problems/word-search/
//very good problem
class Solution {
    private int m, n;
    public boolean exist(char[][] board, String word) {
        this.m = board.length;
        this.n = board[0].length;
        char[] ch = word.toCharArray();
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(ch, i, j, board, new boolean[m][n], 0)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean dfs(char[] word, int x, int y, char[][] board, boolean[][] visited, int index) {
       
        if (word[index] != board[x][y]) {
            return false;
        }
        if (index == word.length - 1) {
            return true;
        }
         visited[x][y] = true;
        int[] xDir = {-1, 1, 0, 0};
        int[] yDir = {0, 0, 1, -1};
        
        for (int i = 0; i < 4; i++) {
            int childX = x + xDir[i];
            int childY = y + yDir[i];
            if (isValid(childX, childY) && !visited[childX][childY] && dfs(word, childX, childY, board, visited, index + 1)) {
                return true;
            }
        }
        visited[x][y] = false; // backtrack if we couldnot find the word in our current dfs, other dfs search should not be blocked because a node in matrix was marked as true, just because current dfs matched the word halfway
        return false;
    }
    private boolean isValid(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }
}
