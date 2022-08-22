// https://leetcode.com/problems/redundant-connection/
class Solution {
    private int[] parent;
    private int[] rank; // size of current subset 
    public int[] findRedundantConnection(int[][] edges) {
        // Naive BFS/DFS where we remove each node and see if graph is a tree is O(N^2) solution
        
        // Union-find, we can do in O(N)
        // https://www.youtube.com/watch?reload=9&v=FXWRE67PLL0
        
        parent = new int[edges.length];
        rank = new int[edges.length];    
        for (int i = 0; i < edges.length; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
        
        for (int i = 0; i < edges.length; i++) {
            if (!union(edges[i][0] - 1, edges[i][1] - 1)) { // we could not merge them, meaning they belonged to same parent already, a cycle is formed
                return edges[i];
            }
        }
        
        return null;
    }
    
    private boolean union(int n1, int n2) {
        int parent1 = find(n1);
        int parent2 = find(n2);

        if (parent1 == parent2) {
            return false; // same parent. cannot merge
        }
        
        if (rank[parent1] > rank[parent2]) {
            parent[parent2] = parent1;
            rank[parent1] += rank[parent2];
        } else {
            parent[parent1] = parent2;
            rank[parent2] += rank[parent1];
        }
        return true;
    }
    
    // naive find would be
    // private int find(int n) {
    //     if (n == parent[n]) {
    //         return n;
    //     }
    //     return find(parent[n]);
    // }
    
    // find using path compression
    private int find(int n) {
        if (n == parent[n]) {
            return n;
        }
        int rootParent = find(parent[n]);
        parent[n] = rootParent; // path - compress 
        return rootParent;
    }
}
