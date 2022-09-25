// https://leetcode.com/problems/cheapest-flights-within-k-stops/
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // We will solve this using bellman ford
        // https://www.geeksforgeeks.org/bellman-ford-algorithm-dp-23/
        // https://www.youtube.com/watch?time_continue=1197&v=5eIK3zUdYmE&feature=emb_logo&ab_channel=NeetCode
        
        // We can use Dijikstra algo too, but it is much more complicated since we 
        // have the criteria of at most k stops when calculating shortest path
        // Bellman ford is O(N * E) but since we know we are allowed to take only
        // K stops, we can shorten it to O(K * E), where E = number of edges
        
        List<Integer> shortestFromSource = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            shortestFromSource.add(Integer.MAX_VALUE);
        }
        shortestFromSource.set(src, 0);
        
        for (int i = 0; i < k + 1; i++) { // if K =0, we still need to run to find shortest path, i.e why it is K + 1. K layers, like level order traversal in BFS
            
           List<Integer> copyList = new ArrayList<>(shortestFromSource);// This is the what we need to do since we are having the constraint to take at most K stops.
            // If we update all the shortest paths in the original shortestFromSource array, we might get a shortest path but with more than K stops. 
            // So we are going to make updates on copyList and READ from original array, and re-assign shortestFromSource to this copyList, only after we have looped over all edges.
            // This make sure and intermediate update in the loop doesn't effect another update later in the loop, as both updates are of same stopCount.
            
           for (int[] edge : flights) {
               int from = edge[0];
               int to = edge[1];
               int cost = edge[2];
               if (shortestFromSource.get(from).equals(Integer.MAX_VALUE)) {
                   continue; // cannot process as it is unreachable
               }
              // interesting, see what we are reading and which array we are writing. 
               if (cost + shortestFromSource.get(from) < copyList.get(to)) {
                   copyList.set(to, cost + shortestFromSource.get(from));
               }
           } 
            
           // now we processed a layer / stop, update the main shortestPaths
            shortestFromSource = copyList;
        }
        
        
        return shortestFromSource.get(dst).equals(Integer.MAX_VALUE) ? -1 : shortestFromSource.get(dst);
    }
}
