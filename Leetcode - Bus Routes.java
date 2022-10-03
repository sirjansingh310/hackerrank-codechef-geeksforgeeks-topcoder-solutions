// https://leetcode.com/problems/bus-routes/
// We don't want minimum number of stops, but minimum number of bus hops. Our graph out of the box would help us say the former but not the latter.
// Since a bus runs in circles around particular stops, we can call them as a cluster. The cost between two stops in this cluster is zero, since same bus
// For stops shared by multiple buses, i.e multiple clusters, we connect them in a graph, where each node is the cluster id. 
// now it boils down to simple bfs where each edge between clusterIds nodes is a hop.
// But we will use multi source bfs since we don't know which bus to board from source stop as multiple buses can be going through the source stop. 

class Solution {
    private class Cluster {
        int clusterId;
        Set<Integer> stopsSet;
        
        Cluster(int clusterId, int[] stops) {
            this.clusterId = clusterId;
            this.stopsSet = new HashSet<>();
            for (int s : stops) {
                stopsSet.add(s);
            }
        }
        
        @Override
        public String toString() {
            return Integer.toString(this.clusterId);
        }
    }
    
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0; // no need to board bus
        }
        Map<Integer, Cluster> clusterIdMap = new HashMap<>();
        Map<Integer, List<Cluster>> stopIdToClusters = new HashMap<>();
        
        int clusterId = 0;
        for (int[] stops : routes) {
            Cluster newCluster = new Cluster(clusterId, stops);
            clusterIdMap.put(clusterId, newCluster);
            
            for (int stop : stops) {
                stopIdToClusters.computeIfAbsent(stop, x -> new ArrayList<>()).add(newCluster);
            }
            clusterId++;
        }
        
        Map<Integer, Set<Integer>> clustersAdj = new HashMap<>();
        
        for (Map.Entry<Integer, List<Cluster>> entry : stopIdToClusters.entrySet()) {
            if (entry.getValue().size() > 1) {
                // stops with common cluster ids should be connected.
                for (Cluster c : entry.getValue()) {
                    for (Cluster d : entry.getValue()) {
                        if (c.clusterId != d.clusterId) {
                            clustersAdj.computeIfAbsent(c.clusterId, x -> new HashSet<>()).add(d.clusterId);
                            clustersAdj.computeIfAbsent(d.clusterId, x -> new HashSet<>()).add(c.clusterId);
                        }
                    }
                }
            }
        }
        
       
        // Now our graph is consiting of clusters, each cluster has stops internally
        // with hop count of 0, to move to stop in another cluster, we will have to travel an edge.
        // With this setup, we have converted the problem to a simple shortest path using BFS, but we will use multi source bfs since a stop can be part of multiple clusters
        
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        
        for (Cluster sourceCluster : stopIdToClusters.get(source)) {
            queue.add(sourceCluster.clusterId);
            visited.add(sourceCluster.clusterId);
        }
        
        int busHopCount = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            while (size-- > 0) {
                int currentClusterId = queue.poll();
                Cluster currentCluster = clusterIdMap.get(currentClusterId);
                
                if (currentCluster.stopsSet.contains(target)) {
                    return busHopCount;
                }
                
                if (!clustersAdj.containsKey(currentClusterId)) {
                    continue;
                }
                for (int nextClusterId : clustersAdj.get(currentClusterId)) {
                    if (!visited.contains(nextClusterId)) {
                        visited.add(nextClusterId);
                        queue.add(nextClusterId);
                    }
                }    
            }
            
            busHopCount++;
            
        }
        
        return -1;
    }
}
