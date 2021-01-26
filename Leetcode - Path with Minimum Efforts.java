// https://leetcode.com/explore/item/3617

// Accepted Solution: Dijkstra With Min Heap(Priority queue)
// How it works: exact like bfs, but using PriorityQueue instead of normal queue.
// don't process in FIFO way, process with node which has minimum distance seen so far first, stored in PQ.
// Another subtle difference with BFS is we don't mark the child node as visited(in this case isPicked array) as soon as a parent visits it(in the for loop).
// This is done so it can get updated later on from other nodes if that node's path to this child is lesser than previously computed.
// We mark it as isPicked / visited only after it becomes a parent(or in other words is the node with min distance from source value and is on top of heap)

// Some accepted ideas: 
/*
KRUSHKAL MST

BFS/DFS USING Binarysearch: 

binary_search() {
int low = 0, high = 10^6. (constraints of heights array, so we can tell these are the extreme values of our minimum effort to jump a node to another node)
int minEffort;
while (low <= high) {
      int mid = (low + high) / 2
      if (dfs(mid, 0, 0)) { // find if a path exists by starting dfs and has the total traveling cost = mid)
            high = mid; // reduce the search space for min in next bfs, we can find an even lower value, then good
            minEffort = mid;
      } else {
            low = mid + 1; // increase search space, no path with cost = mid
      }
}

return minEffort;
}

*/

// My solution : Dijkstra with PriorityQueue
class Solution {
    private class DijkstraNode {
        int rowValue, colValue, distanceCalculatedFromSource;
        DijkstraNode(int rowValue, int colValue, int distanceCalculatedFromSource) {
            this.rowValue = rowValue;
            this.colValue = colValue;
            this.distanceCalculatedFromSource = distanceCalculatedFromSource;
        }
        
    }
    private int rowCount, colCount;
    
    private int minRowIndex = 0, minColIndex = 0;
  
    private boolean isValid(int row, int col) {
        return row >= 0 && row < rowCount && col >= 0 && col < colCount;
    }
    
    public int minimumEffortPath(int[][] heights) {
        int rowCount = heights.length;
        int colCount = heights[0].length;
        this.rowCount = rowCount;
        this.colCount = colCount;
        
        int[][] dist = new int[rowCount][colCount];
        for (int[] arr : dist) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }
        dist[0][0] = 0;
        boolean[][] isPicked = new boolean[rowCount][colCount];
        PriorityQueue<DijkstraNode> pq = new PriorityQueue<>((DijkstraNode node1, DijkstraNode node2) -> node1.distanceCalculatedFromSource - node2.distanceCalculatedFromSource);
        
        pq.add(new DijkstraNode(0, 0, 0));
        
        while (pq.size() > 0) {
            DijkstraNode minNode = pq.poll();
            int minRowIndex = minNode.rowValue;
            int minColIndex = minNode.colValue;
            isPicked[minRowIndex][minColIndex] = true;
           
            
            int[] rowMoves = {-1, 1, 0, 0};
            int[] colMoves = {0, 0, 1, -1};
            
            for (int i = 0; i < 4; i++) {
                int childRow = minRowIndex + rowMoves[i];
                int childCol = minColIndex + colMoves[i];
                if (isValid(childRow, childCol) && !isPicked[childRow][childCol]) {
                    int absValue = Math.abs(heights[minRowIndex][minColIndex] - heights[childRow][childCol]);
                    dist[childRow][childCol] = Math.min(dist[childRow][childCol], 
                                                       Math.max(dist[minRowIndex][minColIndex], absValue));// not traditional shortest path, logic specfic to question
                    
                    pq.add(new DijkstraNode(childRow, childCol, dist[childRow][childCol]));
                }
            }
        }
       
        return dist[rowCount - 1][colCount - 1];
    }
}


// My previous solution using simple brute force Dijkstra, TLE

class Solution {
    private int rowCount, colCount;
    
    private int minRowIndex = 0, minColIndex = 0;
  
    private boolean isValid(int row, int col) {
        return row >= 0 && row < rowCount && col >= 0 && col < colCount;
    }
    
    private void pickMinimumIndex(int[][] dist, boolean[][] isPicked) {
        int minSeenSoFar = Integer.MAX_VALUE;
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                if (dist[i][j] != Integer.MAX_VALUE && dist[i][j] < minSeenSoFar && !isPicked[i][j]) {
                    minSeenSoFar = dist[i][j];
                    minRowIndex = i;
                    minColIndex = j;
                }
            }
        }
    }
    
    public int minimumEffortPath(int[][] heights) {
        int rowCount = heights.length;
        int colCount = heights[0].length;
        this.rowCount = rowCount;
        this.colCount = colCount;
        
        int[][] dist = new int[rowCount][colCount];
        for (int[] arr : dist) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }
        dist[0][0] = 0;
        boolean[][] isPicked = new boolean[rowCount][colCount];
        int visitCount = 0;
        
        while (visitCount < rowCount * colCount) {
            pickMinimumIndex(dist, isPicked);
            isPicked[minRowIndex][minColIndex] = true;
            visitCount++;
            
            int[] rowMoves = {-1, 1, 0, 0};
            int[] colMoves = {0, 0, 1, -1};
            
            for (int i = 0; i < 4; i++) {
                int childRow = minRowIndex + rowMoves[i];
                int childCol = minColIndex + colMoves[i];
                if (isValid(childRow, childCol)) {
                    int absValue = Math.abs(heights[minRowIndex][minColIndex] - heights[childRow][childCol]);
                    dist[childRow][childCol] = Math.min(dist[childRow][childCol], 
                                                       Math.max(dist[minRowIndex][minColIndex], absValue));
                }
            }
        }
        
        return dist[rowCount - 1][colCount - 1];
    }
}
