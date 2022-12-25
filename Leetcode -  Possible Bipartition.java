// https://leetcode.com/problems/possible-bipartition/
// Problem basically boils down to see if dislikes graph is a bipartite graph
// https://en.wikipedia.org/wiki/Bipartite_graph
// Union find solution
class Solution {
    public boolean possibleBipartition(int n, int[][] dislikes) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            adj.put(i, new ArrayList<>());
        }
        for (int[] pair : dislikes) {
            adj.get(pair[0]).add(pair[1]);
            adj.get(pair[1]).add(pair[0]);
        }
        
        int[] parent = new int[n + 1];
        int[] rank = new int[n + 1];
        Arrays.fill(rank, 1);
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        
        for (int i = 1; i <= n; i++) {
            for (int j : adj.get(i)) {
                if (find(parent, i) == find(parent, j)) {
                    return false; // conflict, i and j were added as part of one disjoint set earlier, but now we have a pair which says they cannot exist together
                }
                union(parent, rank, adj.get(i).get(0), j); // all children in dislike graph should be part of one set, diff from set for current node, if we find conflict later between pair of people who dislike eachother, we return false, otherwise we return true
            }
        }
        
        return true;
    }
    
    private void union(int[] parent, int[] rank, int x, int y) {
        int parentX = find(parent, x);
        int parentY = find(parent, y);
        
        if (parentX != parentY) {
            if (rank[parentX] < rank[parentY]) {
                parent[parentX] = parentY;
                rank[parentY] += rank[parentX];
            } else {
                parent[parentY] = parentX;
                rank[parentX] += rank[parentY];
            }
        }
    }
    
    private int find(int[] parent, int x) {
        if (parent[x] != x) {
            int root = find(parent, parent[x]);
            parent[x] = root;
            return root;
        }
        return x;
    }
}

// More intuitive, bfs + graph coloring, color one set as red, other as blue, see if it holds true all the time, otherwise we have a conflict
class Solution {
    public boolean possibleBipartition(int n, int[][] dislikes) {
        if (dislikes.length == 0) {
            return true;
        }
        int[] color = new int[n + 1];// can we 0 or 1, -1 means not colored
        Arrays.fill(color, -1); // will use this to also know if node is visited or not
        
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        
        for (int[] pair : dislikes) {
            adj.get(pair[0]).add(pair[1]);
            adj.get(pair[1]).add(pair[0]);
        }
        
        for (int i = 1; i <= n; i++) { // since multiple connected components
            if (color[i] == -1) {
                if (!bfs(i, adj, color)) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    
    private boolean bfs(int start, List<List<Integer>> adj, int[] color) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        color[start] = 1;
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int i : adj.get(current)) {
                if (color[i] == -1) {
                    color[i] = 1 - color[current]; // other color than parent
                    queue.add(i);
                } else if (color[i] == color[current]) {// conflict
                    return false;
                }
            }
        }
        
        return true;
    }
}
