// https://leetcode.com/problems/min-cost-to-connect-all-points/
// Problem is basically finding MST 
// This is solution without PQ, accepted. The one with PQ(which is below) passed all TC, but still TLE ( I am supposing it being that slow because graph
// becomes very dense, but a loop check to getBestEdge is not big because we have 1000 Nodes at max, so we have 1000 ^2 ~ edges). 
class Solution {
    private class Edge {
        int[] point;
        int edgeValue;
        
        public Edge(int[] point, int edgeValue) {
            this.point = point;
            this.edgeValue = edgeValue;
        }
    }
    
    
    public int minCostConnectPoints(int[][] points) {
        // Consider the graph to be fully connected
        // Return the MST
        final int INF = Integer.MAX_VALUE;
        Map<int[], Edge> pointToEdge = new HashMap<>();
        Set<int[]> visited = new HashSet<>();
        
        Edge firstEdge = new Edge(points[0], 0);
        pointToEdge.put(points[0], firstEdge);
        for (int i = 1; i < points.length; i++) {
            pointToEdge.put(points[i], new Edge(points[i], INF)); //INF 
        }
        
         List<Edge> edges = new ArrayList<>(pointToEdge.values());
        while (visited.size() < points.length) {
            Edge bestEdge = getBestEdge(edges, visited);
            int[] currentPoint = bestEdge.point;
            visited.add(currentPoint);
            
            for (int i = 0; i < points.length; i++) {
                if (!(currentPoint[0] == points[i][0] && currentPoint[1] == points[i][1])) {
                    int[] childPoint = points[i];
                    Edge childBestEdge = pointToEdge.get(childPoint);
                    int currentEdgeValue = Math.abs(currentPoint[0] - childPoint[0]) + Math.abs(currentPoint[1] - childPoint[1]);
                    
                    if (!visited.contains(childPoint) && currentEdgeValue < childBestEdge.edgeValue) {
                        childBestEdge.edgeValue = currentEdgeValue;
                    }
                }
            }
        }
        
        int result = 0;
        for (Map.Entry<int[], Edge> entry : pointToEdge.entrySet()) {
            result += entry.getValue().edgeValue;
        }
        
        return result;
    }
    
    private Edge getBestEdge(List<Edge> edges, Set<int[]> visited) {
        Edge bestEdge = null;
        
        for (int i = 0; i < edges.size(); i++) {
            if (!visited.contains(edges.get(i).point) && bestEdge == null) {
                bestEdge = edges.get(i);
            } else if (!visited.contains(edges.get(i).point) && edges.get(i).edgeValue < bestEdge.edgeValue) {
                bestEdge = edges.get(i);
            }
        }
        
        return bestEdge;
    }
}


// PQ Solution. Note, don't add new custom objects to PQ after modifying what was used in PQ comparator. Instead add new one to trigger a rebalance on 
// calling .add(). This i think is because it is same object if it was already add in PQ before

class Solution {
    private class Edge {
        int[] point;
        int edgeValue;
        
        public Edge(int[] point, int edgeValue) {
            this.point = point;
            this.edgeValue = edgeValue;
        }
    }
    
    
    public int minCostConnectPoints(int[][] points) {
        // Consider the graph to be fully connected
        // Return the MST
        final int INF = Integer.MAX_VALUE;
        PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.edgeValue - e2.edgeValue);
        
        Map<int[], Edge> pointToEdge = new HashMap<>();
        Set<int[]> visited = new HashSet<>();

        
        for (int i = 0; i < points.length; i++) {
            pointToEdge.put(points[i], new Edge(points[i], INF)); //INF 
        }
         Edge firstEdge = new Edge(points[0], 0); // imaginary edge so points[0] is picked first
        pointToEdge.put(points[0], firstEdge);
        pq.add(firstEdge);
        
        while (!pq.isEmpty()) {
            Edge bestEdge = pq.poll();
            int[] currentPoint = bestEdge.point;
            visited.add(currentPoint);
            
            for (int i = 0; i < points.length; i++) {
                if (!(currentPoint[0] == points[i][0] && currentPoint[1] == points[i][1])) {
                    int[] childPoint = points[i];
                    Edge childBestEdge = pointToEdge.get(childPoint);
                    int currentEdgeValue = Math.abs(currentPoint[0] - childPoint[0]) + Math.abs(currentPoint[1] - childPoint[1]);
                    
                    if (!visited.contains(childPoint) && currentEdgeValue < childBestEdge.edgeValue) {
                        childBestEdge.edgeValue = currentEdgeValue;
                        pq.add(new Edge(childBestEdge.point, childBestEdge.edgeValue)); // ADD NEW EDGE IN PQ, don't put the modified edge in PQ as it might already be present and doesn't trigger re-balance.
                    }
                }
            }
        }
        
        int result = 0;
        for (Map.Entry<int[], Edge> entry : pointToEdge.entrySet()) {
            result += entry.getValue().edgeValue;
        }
        
        return result;
    }
}
