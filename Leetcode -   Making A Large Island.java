// https://leetcode.com/explore/featured/card/august-leetcoding-challenge-2021/613/week-1-august-1st-august-7th/3835/

class ConnectedComponentDetails {
    private final int gridOriginX;
    private final int gridOriginY;
    private final int count;
    
    public ConnectedComponentDetails(int gridOriginX, int gridOriginY, int count) {
        this.gridOriginX = gridOriginX;
        this.gridOriginY = gridOriginY;
        this.count = count;
    }
    
    public int getCount() {
        return this.count;
    }
    
    public String toString() {
        return "X = " + this.gridOriginX + " y = " + this.gridOriginY + " count = " + this.count + "\n";
    }
}

class Solution {
    private static final int[] xDir = {-1, 1, 0, 0};
    private static final int[] yDir = {0, 0, 1, -1};
    
    private void bfs(int[][] grid, boolean[][] visited, int startX, int startY, int bound, Map<String, ConnectedComponentDetails> map) {
        Queue<Integer> queue = new LinkedList<>();
        visited[startX][startY] = true;
        queue.add(startX);
        queue.add(startY);
        Set<String> visitedNow = new HashSet<>();
        visitedNow.add(createKey(startX, startY));
        int count = 1;
        
        while (!queue.isEmpty()) {
            int currentX = queue.poll(), currentY = queue.poll();
            for (int i = 0; i < 4; i++) {
                int x = currentX + xDir[i], y = currentY + yDir[i];
                if (isValid(x, y, grid, visited)) {
                    visited[x][y] = true;
                    queue.add(x);
                    queue.add(y);
                    visitedNow.add(createKey(x, y));
                    count++;
                }
            }
        }
        
        ConnectedComponentDetails details = new ConnectedComponentDetails(startX, startY, count);
        for (String pair : visitedNow) {
            map.put(pair, details);
        }
    }
    
    private String createKey(int x, int y) {
        return x + "_" + y;
    }
    
    private boolean isValid(int x, int y, int[][] grid, boolean[][] visited) {
        return isValid(x, y, grid) && !visited[x][y];
    }
    
    private boolean isValid(int x, int y, int[][] grid) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid.length && grid[x][y] == 1;
    }
    
    private int getIfConnected(int x, int y, int[][] grid, Map<String, ConnectedComponentDetails> map) {
        int count = 1;
        Set<ConnectedComponentDetails> uniqueComponents = new HashSet<>();
        for (int i = 0; i < 4; i++) {
            int childX = x + xDir[i], childY = y + yDir[i];
            if (isValid(childX, childY, grid)) {
                uniqueComponents.add(map.get(createKey(childX, childY)));
            }
        }
        
        for (ConnectedComponentDetails component : uniqueComponents) {
            count += component.getCount();
        }
        
        return count;
    }
    
    public int largestIsland(int[][] grid) {
        int n = grid.length;
        boolean[][] visited = new boolean[n][n];
        Map<String, ConnectedComponentDetails> connectedComponentMap = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isValid(i, j, grid, visited)) {
                    bfs(grid, visited, i, j, n, connectedComponentMap);
                }
            }
        }
        
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    max = Math.max(max, connectedComponentMap.get(createKey(i, j)).getCount());
                } else {
                    max = Math.max(max, getIfConnected(i, j, grid, connectedComponentMap));
                }
            }
        }
        return max;
    }
}
