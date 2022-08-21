// https://leetcode.com/problems/course-schedule/
// Topological sort, see if current graph is a DAG

// Solution 2 below DFS + Cycle detection

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List[] graph = new ArrayList[numCourses];
        
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }
        
        int[] indegree = new int[numCourses];
        for (int[] pair : prerequisites) {
            indegree[pair[0]]++;
            graph[pair[1]].add(pair[0]);
        }
        
        Queue<Integer> queue = new LinkedList<>();
        
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }
        
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            numCourses--;// take that course
            
            List<Integer> children = graph[current];
            for (int child : children) {
                indegree[child]--;// delete dependency
                if (indegree[child] == 0) {
                    queue.add(child);
                }
            }
        }
        return numCourses == 0;
    }
}


// DFS Cycle detection on directed graph
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List[] graph = new ArrayList[numCourses];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] pair : prerequisites) {
            graph[pair[1]].add(pair[0]);
        }
        
        boolean[] visited = new boolean[numCourses];
        boolean[] locks = new boolean[numCourses]; // can be shared since reset via backtrack
        
        for (int i = 0; i < numCourses; i++) {
            if (!visited[i] && isCyclic(i, visited, locks, graph)) {
                return false;
            }
        }
        
        return true;
    }
    
    private boolean isCyclic(int current, boolean[] visited, boolean[] locks, List[] graph) {
        if (locks[current]) {
            return true; // already on lock, but visited again
        }
        
        if (visited[current]) {
            return false;
        }
        
        visited[current] = true;
        locks[current] = true;
        List<Integer> children = graph[current];
        
        for (int child : children) {
            if (isCyclic(child, visited, locks, graph)) {
                return true;
            }
        }
        
        locks[current] = false;// after deep dfs, unlock node
        return false;
    }
}
