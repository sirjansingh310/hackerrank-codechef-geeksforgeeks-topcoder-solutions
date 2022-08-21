// https://leetcode.com/problems/course-schedule/
// Topological sort, see if current graph is a DAG

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
