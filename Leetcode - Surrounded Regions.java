// https://leetcode.com/problems/surrounded-regions/
class Solution {
    public void solve(char[][] board) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (!visited[i][j] && board[i][j] == 'O') {
                    capture(board, visited, i, j);
                }
            }
        }
    }
    
    
    private void capture(char[][] board, boolean[][] visited, int row, int col) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(row);
        queue.add(col);
        visited[row][col] = true;
        
        Set<List<Integer>> currentVisitedSet = new HashSet<>();
        currentVisitedSet.add(Arrays.asList(row, col));
        
        int[] rowDir = {1, -1, 0, 0};
        int[] colDir = {0, 0, 1, -1};
        
        while (!queue.isEmpty()) {
            int currentX = queue.poll();
            int currentY = queue.poll();
            
            // visit children
            for (int i = 0; i < 4; i++) {
                int nextRow = currentX + rowDir[i];
                int nextCol = currentY + colDir[i];
                if (nextRow >= 0 && nextRow < board.length && nextCol >= 0 && nextCol < board[0].length && !visited[nextRow][nextCol] && board[nextRow][nextCol] == 'O') {
                    queue.add(nextRow);
                    queue.add(nextCol);
                    visited[nextRow][nextCol] = true;
                    currentVisitedSet.add(Arrays.asList(nextRow, nextCol));
                }
            }
            
            // return if not bordered with 'X' or self is at border itself as it is again no border with 'X'
            if (currentX == 0 || currentX == board.length - 1 || currentY == 0 || currentY == board[0].length - 1) {
                return;
            }
            
            for (int i = 0; i < 4; i++) {
                int nextRow = currentX + rowDir[i];
                int nextCol = currentY + colDir[i];
                
                if (nextRow >= 0 && nextRow < board.length && nextCol >= 0 && nextCol < board[0].length && !currentVisitedSet.contains(Arrays.asList(nextRow, nextCol)) && board[nextRow][nextCol] == 'O') {
                    return;
                }
            }
        }
        
        // replace as fully surrounded by 'X'
        for (List<Integer> point : currentVisitedSet) {
            board[point.get(0)][point.get(1)] = 'X';
        }
    }
}
