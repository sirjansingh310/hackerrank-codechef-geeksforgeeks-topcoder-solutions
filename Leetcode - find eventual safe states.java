// https://leetcode.com/problems/find-eventual-safe-states/
// Topological sorting
class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> result = new ArrayList<>();
        
        // we have node -> children, i.e outwards, 
        // create reverse graph which tells who all connect to me, size of each will be its indegree
        
        List<Set<Integer>> reverseGraph = new ArrayList<>();
        List<Set<Integer>> forwardGraph = new ArrayList<>();

        for (int i = 0; i < graph.length; i++) {
            reverseGraph.add(new HashSet<>());
            forwardGraph.add(new HashSet<>());
        }
        
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                reverseGraph.get(graph[i][j]).add(i);
                forwardGraph.get(i).add(graph[i][j]);
            }
        }
        
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < graph.length; i++) {
            if (graph[i].length == 0) {
                queue.add(i);
            }
        }
        
        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);
            
            for (int parent : reverseGraph.get(node)) {
                forwardGraph.get(parent).remove(node);
                if (forwardGraph.get(parent).isEmpty()) {
                    queue.add(parent);
                }
            }
        }
        
        Collections.sort(result);
        
        return result;
        
    }
}
