// https://leetcode.com/problems/course-schedule-ii/ 
// Topological sort only instead of naive detect a cycle(which i did with both topo sort and dfs cycle detection)
// Since we want to return order of courses taken if possible, and topological sort gives exactly that.

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
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
        
        int[] sortedTakenCourses = new int[numCourses];
        int idx = 0;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            numCourses--;// take that course
            sortedTakenCourses[idx++] = current;
            
            List<Integer> children = graph[current];
            for (int child : children) {
                indegree[child]--;// delete dependency
                if (indegree[child] == 0) {
                    queue.add(child);
                }
            }
        }
        return numCourses == 0 ? sortedTakenCourses :  new int[0];
    }
}
