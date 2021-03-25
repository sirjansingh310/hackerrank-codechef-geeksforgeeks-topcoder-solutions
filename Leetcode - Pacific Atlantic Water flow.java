class Solution {
    private boolean isValidCell(int x, int y, int totalX, int totalY) {
        return x >= 0 && x < totalX && y >= 0 && y < totalY;
    }
    
    private boolean canWaterFlow(int childHeight, int parentHeight) {
        return parentHeight <= childHeight;
    }
    
    private boolean onCoast(String oceanName, int x, int y, int totalX, int totalY) {   
        if (oceanName.equals("atlantic")) {
            return x == totalX - 1 || y == totalY - 1;
        }
        return x == 0 || y == 0;
    }
    private Set<List<Integer>> bfs(int[][] matrix, String oceanName) {
        Set<List<Integer>> result = new HashSet<>();
        int n = matrix.length, m = matrix[0].length;
        boolean[][] visited = new boolean[n][m];
        Queue<Integer> queue = new LinkedList<>();
        if (oceanName.equals("atlantic")) {
             queue.add(n - 1);
             queue.add(m - 1);
        } else {
            queue.add(0);
            queue.add(0);
        }
        int[] yDir = {-1, 1, 0, 0};
        int[] xDir = {0, 0, 1, -1};
        
        while (!queue.isEmpty()) {
            int x = queue.poll();
            int y = queue.poll();
            result.add(Arrays.asList(x, y));
            
            for (int i = 0; i < 4; i++) {
                int childX = x + xDir[i];
                int childY = y + yDir[i];
                if (isValidCell(childX, childY, n, m) && !visited[childX][childY]) {
                    // on the coast of occean or inside & water can flow
                    if ((onCoast(oceanName, childX, childY, n, m)) || (canWaterFlow(matrix[childX][childY], matrix[x][y]))) {
                        visited[childX][childY] = true;
                        queue.add(childX);
                        queue.add(childY);
                    }
                }
            }
        }
        
        return result;
    }
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        if (matrix.length == 0) {
            return Collections.emptyList();
        }
        Set<List<Integer>> atlanticCells = bfs(matrix, "atlantic");
        Set<List<Integer>> pacificCells = bfs(matrix, "pacific");
        
        
        atlanticCells.retainAll(pacificCells);
        return new ArrayList(atlanticCells);
    }
}
