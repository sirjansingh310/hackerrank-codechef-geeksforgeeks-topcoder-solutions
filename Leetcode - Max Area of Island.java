//https://leetcode.com/explore/challenge/card/june-leetcoding-challenge-2021/603/week-1-june-1st-june-7th/3764/


// DFS
class Solution {
    private boolean isValid(int x, int y, int maxX, int maxY) {
        return x >= 0 && x < maxX && y >= 0 && y < maxY;
    }
    
    private int dfs(int x, int y, int[][] grid, boolean[][] visited) {
        if (visited[x][y]) {
            return 0;
        }
        
        int sum = 1;
        visited[x][y] = true;
        
        int[] xMoves = {-1 , 0, 0, 1};
        int[] yMoves = {0, 1, -1, 0};
        
        for (int i = 0; i < 4; i++) {
            int childX = x + xMoves[i];
            int childY = y + yMoves[i];
            
            if (isValid(childX, childY, grid.length, grid[0].length) && grid[childX][childY] == 1) {
                sum += dfs(childX, childY, grid, visited);
            }
        }
        return sum;
    }
    
    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0, n = grid.length, m = grid[0].length;
        boolean[][] visited = new boolean[n][m];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    maxArea = Math.max(maxArea, dfs(i, j, grid, visited));
                }
            }
        }
        return maxArea;
    }
}

// BFS

class Solution {
    private boolean isValid(int x, int y, int maxX, int maxY) {
        return x >= 0 && x < maxX && y >= 0 && y < maxY;
    }
    
    private int bfs(int x, int y, int[][] grid, boolean[][] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(x);
        queue.add(y);
        visited[x][y] = true;
        int sum = 0;
        
        while (!queue.isEmpty()) {
            int currentX = queue.poll();
            int currentY = queue.poll();
            sum++;
            
            int[] xMoves = {-1, 0, 0, 1};
            int[] yMoves = {0, 1, -1, 0};
            
            for (int i = 0; i < 4; i++) {
                int childX = currentX + xMoves[i];
                int childY = currentY + yMoves[i];
                if (isValid(childX, childY, grid.length, grid[0].length) && grid[childX][childY] == 1
                   && !visited[childX][childY]) {
                    visited[childX][childY] = true;
                    queue.add(childX);
                    queue.add(childY);
                }
            }
        }
        
        return sum;
    }
    
    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0, n = grid.length, m = grid[0].length;
        boolean[][] visited = new boolean[n][m];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    maxArea = Math.max(maxArea, bfs(i, j, grid, visited));
                }
            }
        }
        return maxArea;
    }
}
