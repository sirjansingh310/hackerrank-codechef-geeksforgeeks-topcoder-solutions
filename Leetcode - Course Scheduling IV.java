//https://leetcode.com/problems/course-schedule-iv/

class Solution {
    private void dfs(int source, int destination, boolean[] visited, List<List<Integer>> graph) {
        if (visited[source]) {
            return;
        }
        visited[source] = true;
        
        if (source == destination) {
            return;
        }
        for (int child : graph.get(source)) {
            dfs(child, destination, visited, graph);
        }
    }
    
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        List<List<Integer>> dependencyGraph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            dependencyGraph.add(new ArrayList<>());
        }
        
        for (int[] pair : prerequisites) {
            dependencyGraph.get(pair[1]).add(pair[0]);
        }
        
        List<Boolean> result = new ArrayList<>();
        for (int[] query: queries) {
            boolean[] visitedNodes = new boolean[numCourses];
            
            dfs(query[1], query[0], visitedNodes, dependencyGraph);
            if (visitedNodes[query[0]]){
                result.add(true);
            } else {
                result.add(false);
            }
        }
        return result;
    }
}
