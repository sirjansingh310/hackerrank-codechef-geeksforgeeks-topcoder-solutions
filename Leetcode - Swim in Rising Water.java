// https://leetcode.com/explore/challenge/card/june-leetcoding-challenge-2021/605/week-3-june-15th-june-21st/3785/
class Node implements Comparable<Node> {
    private int x; 
    private int y;
    private int time; 
    
    
    public Node(int x, int y, int time) {
        this.x = x;
        this.y = y;
        this.time = time;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public int getTime() {
        return this.time;
    }
    
    @Override
    public int compareTo(Node node) {
        return this.time - node.time;
    }
    
    @Override
    public String toString() {
        return "Node x = " + this.x + " y = " + this.y + " time = " + this.time + "\n";
    }
}

class Solution {
    private int calculateTime(int parent, int child) {
        return (child > parent) ? child : parent;
    }
    
    private boolean isValid(int childX, int childY, int n, int m) {
        return childX >= 0 && childX < n && childY >= 0 && childY < m;
    }
    
    public int swimInWater(int[][] grid) {
        //Dijkstra's shortest path
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] visited = new boolean[n][m];
        
        int[] xMoves = {-1, 1, 0, 0};
        int[] yMoves = {0, 0, 1, -1};
        
        int[][] time = new int[n][m]; // distance from source. In this question time taken from source
        for (int i = 0; i < n; i++) {
            Arrays.fill(time[i], Integer.MAX_VALUE);
        }
        
        PriorityQueue<Node> pq = new PriorityQueue<>();// min heap
        time[0][0] = grid[0][0]; // wait till water rises to grid[0][0] level
        pq.add(new Node(0, 0, grid[0][0]));
        
        while (!pq.isEmpty()) {
            Node best = pq.poll();
            int parentX = best.getX();
            int parentY = best.getY();
            int parentTime = best.getTime();
            visited[parentX][parentY] = true; // updated here instead of while visiting child
            // like bfs so we give the child to get updated with a better distance from some other parent.
            
            for (int i = 0; i < 4; i++) {
                int childX = parentX + xMoves[i];
                int childY = parentY + yMoves[i];
                if (isValid(childX, childY, n, m) && !visited[childX][childY] && time[childX][childY] > calculateTime(parentTime, grid[childX][childY])) {
                    int newTime = calculateTime(parentTime, grid[childX][childY]);
                    pq.add(new Node(childX, childY, newTime));
                    time[childX][childY] = newTime;
                }
            }
        }
        
        return time[n - 1][m - 1];
    }
}
