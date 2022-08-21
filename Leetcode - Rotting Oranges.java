// Regular BFS Solution followed by Multi-source bfs

class Solution {
    public int orangesRotting(int[][] grid) {
        int[][] minimumRotTime = new int[grid.length][grid[0].length];
        
        for (int i = 0; i < grid.length; i++) {
            Arrays.fill(minimumRotTime[i], Integer.MAX_VALUE);
        }
        
        Set<String> rottenOverall = new HashSet<>(); // making grid[i][j] = 2 while doing bfs to mark as rotten can create problems when someother rotten orange can make it rotten, at cheaper cost. So not touching original grid array
        
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    bfs(grid, i, j, minimumRotTime, rottenOverall);
                }
            }
        }
    
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1 && !rottenOverall.contains(i + "_" + j)) { // was not rotten
                    return -1;
                } else if (grid[i][j] == 0) {
                    continue;
                }
                result = Math.max(result, minimumRotTime[i][j]);
            }
        }
        
        return result;
    }
    
    
    private void bfs(int[][] grid, int row, int col, int[][] minimumRotTime, Set<String> rottenSet) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        
        minimumRotTime[row][col] = 0;
        queue.add(row);
        queue.add(col);
        queue.add(0);
        visited[row][col] = true;
        
        int[] rDir = {1, -1, 0, 0};
        int[] cDir = {0, 0, 1, -1};
        
        while (!queue.isEmpty()) {
            int x = queue.poll();
            int y = queue.poll();
            int distance = queue.poll();
            minimumRotTime[x][y] = Math.min(minimumRotTime[x][y], distance);
            for (int i = 0; i < 4; i++) {
                int nextRow = x + rDir[i];
                int nextCol = y + cDir[i];
                if (nextRow >= 0 && nextRow < grid.length && nextCol >= 0 && nextCol < grid[0].length && grid[nextRow][nextCol] == 1 && !visited[nextRow][nextCol]) {
                    queue.add(nextRow);
                    queue.add(nextCol);
                    queue.add(distance + 1);
                    rottenSet.add(nextRow + "_" + nextCol);
                    visited[nextRow][nextCol] = true;
                }
            }
        }
    }
}


// Multi source bfs. Here we put all start points in queue. Now for the currnt queue size, we visit all nodes. It is exactly like level order traversal
// Notice the immediate advantage of this, we can actually make grid[i][j] = 2 as we are not worried that someother source can make it rotten in less time.
// all sources fired from once. And for the current level(queue size), we update its children in like realtime, parallely. And we know all this is still one unit of time.
// because of this, we need not store mininmumRotTime like we did in previous solution. It is like all real-time updates made on orange.
// This is simply amazing solution
class Solution {
    public int orangesRotting(int[][] grid) {
        int fresh = 0;
        Queue<int[]> queue = new LinkedList<>();
        
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) { // for multi source bfs
                    queue.add(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }
        
        int timeTaken = 0;
        int[] rowDir = {1, -1, 0, 0};
        int[] colDir = {0, 0, 1, -1};
        
        
        while (!queue.isEmpty() && fresh != 0) {
            int currentSize = queue.size();
            for (int i = 0; i < currentSize; i++) {
                int[] arr = queue.poll();
                int x = arr[0], y = arr[1];
                
                for (int j = 0; j < 4; j++) {
                    int nextRow = x + rowDir[j];
                    int nextCol = y + colDir[j];
                    
                    if (nextRow >= 0 && nextRow < grid.length && nextCol >= 0 && nextCol < grid[0].length && grid[nextRow][nextCol] == 1) {
                        grid[nextRow][nextCol] = 2;
                        queue.add(new int[]{nextRow, nextCol});
                        fresh--;
                    }
                }
            }
            timeTaken++; // for all oranges in current level, they get visited / rotten parallely. this is the beauty of the multi source bfs as opposed to regular bfs
            
        }
        
        return fresh == 0 ? timeTaken : -1;
    }
}
