// https://leetcode.com/problems/validate-binary-tree-nodes/
class Solution {
    public boolean validateBinaryTreeNodes(int n, int[] left, int[] right) {
        // topo sort. We need to check if we form a single DAG
        
        int[] indegree = new int[n];
        
        for (int i = 0; i < n; i++) {
            if (left[i] != -1) 
                indegree[left[i]]++;
            if (right[i] != -1)
                indegree[right[i]]++;
        }
        
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }
        
        // not a single DAG
        if (queue.size() != 1) {
            return false;
        }
        
        // check if a node has indegree >= 2, which is not a binary tree property
        
        for (int i = 0; i < n; i++) {
            if (indegree[i] >= 2) {
                return false;
            }
        }
      
        int visitCount = 0;
        while (!queue.isEmpty()) {
            int parent = queue.poll();
            visitCount++;
            
            if (left[parent] != -1) {
                indegree[left[parent]]--;
                // checking indegree == 0 after decrement is redundant, since we already checked
                // the case where indegree >= 2 above
                queue.add(left[parent]);
            }
            
            if (right[parent] != -1) {
                indegree[right[parent]]--;
                queue.add(right[parent]);
            }
        }
        
        return visitCount == n;
    }
}
