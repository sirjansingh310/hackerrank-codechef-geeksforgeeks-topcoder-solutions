// https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/
class Solution {
    public int removeStones(int[][] stones) {
        int[] rank = new int[stones.length];
        int[] parent = new int[stones.length];
        
        Arrays.fill(rank, 1);
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        
        for (int i = 0; i < stones.length; i++) {
            for (int j = i + 1; j < stones.length; j++) {
                if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
                    union(i, j, parent, rank);
                }
            }
        }
        
        Set<Integer> uniqueParents = new HashSet<>();
        for (int i = 0; i < stones.length; i++) {
            uniqueParents.add(find(i, parent));
        }
        
        return stones.length - uniqueParents.size();
    }
    
    
    private int find(int x, int[] parent) {
        if (x != parent[x]) {
            int result = find(parent[x], parent);
            parent[x] = result;
            return result;
        }
        return x;
    }
    
    private void union(int x, int y, int[] parent, int[] rank) {
        int xParent = find(x, parent);
        int yParent = find(y, parent);
        
        if (xParent != yParent) {
            int xRank = rank[xParent];
            int yRank = rank[yParent];
            
            if (xRank < yRank) {
                parent[xParent] = yParent;
                rank[yParent] += rank[xParent];
            } else {
                parent[yParent] = xParent;
                rank[xParent] += rank[yParent];
            }
        }
    }
}
