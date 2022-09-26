// https://leetcode.com/problems/satisfiability-of-equality-equations/
// Union-find
// Equations having == are unioned. Then we iterate over all != equations, to see if they are really different by seeing which disjoint set they belong to.
class Solution {
    private int[] parent = new int[26];
    private int[] rank = new int[26];
    
    public boolean equationsPossible(String[] equations) {
        Arrays.fill(parent, -1);
        Arrays.fill(rank, -1);
        
        for (int i = 0; i < equations.length; i++) {
            String s = equations[i];
            int v1 = s.charAt(0) - 'a';
            int v2 = s.charAt(3) - 'a';
            parent[v1] = v1;
            rank[v1] = 1;
            parent[v2] = v2;
            rank[v2] = 1;
        }
        
        for (int i = 0; i < equations.length; i++) {
            String s = equations[i];
            int v1 = s.charAt(0) - 'a';
            int v2 = s.charAt(3) - 'a';
            if (s.charAt(1) == '=') { // ex s is a==b
                union(v1, v2);
            }
        }        
        // Now the ones which are equal to each other are in their own disjoint set, 
        //verify the != equations
        for (int i = 0; i < equations.length; i++) {
            String s = equations[i];
            int v1 = s.charAt(0) - 'a';
            int v2 = s.charAt(3) - 'a';
            if (s.charAt(1) == '!') {
                int parent1 = find(v1); // ALWAYS use find function for finding parent and after union-find is complete, and not parent array itself since path compression might not be completed given on input.
                int parent2 = find(v2);
                if (parent1 == parent2) { // it is invalid equation since they are in same disjoint set but equation says they are not equal
                    return false;
                }
            }
        }
        
        return true;
    }
    
    private int find(int num) {
        if (num != parent[num]) {
            int root = find(parent[num]);
            parent[num] = root; // path compression
            return root;
        }
        return num;
    }
    
    private boolean union(int num1, int num2) {
        int parent1 = find(num1);
        int parent2 = find(num2);
        
        if (parent1 != parent2) {
            int rank1 = rank[parent1];
            int rank2 = rank[parent2];
            
            if (rank1 > rank2) {
                rank[parent1] += rank[parent2];
                parent[parent2] = parent1;
            } else {
                rank[parent2] += rank[parent1];
                parent[parent1] = parent2;
            }
            return true;
        }
        return false;
    }
}
