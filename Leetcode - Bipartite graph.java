// https://www.geeksforgeeks.org/bipartite-graph/ Read
//solution for https://leetcode.com/explore/challenge/card/february-leetcoding-challenge-2021/585/week-2-february-8th-february-14th/3639/
class Solution {
    public boolean isBipartite(int[][] graph) {
        // we assign each node a color, lets say blue and red.
        // all nodes of red should be in one set and all nodes of blue in another
        // a graph is bipartate, for each edge in the whole graph, one end of edge is blue/red 
        // and other is red/blue(i.e opposite color)
        
        Map<Integer, Integer> color = new HashMap<>();
        
        for (int i = 0; i < graph.length; i++) {
            // if a node seen is not colored, and dfs return true, then we continue
            // while calling dfs, we assign node i as color 1
            if (!color.containsKey(i) && !dfs(i, 1, color, graph)) {
                return false;
            }
        }
        return true;
    }
    
    
    private boolean dfs(int parent, int parentColor, Map<Integer, Integer> colorMap, int[][] graph) {
        // if our current recursion node is already colored, return false if it mis match with current passed color param
        if (colorMap.containsKey(parent)) {
            return parentColor == colorMap.get(parent);
        }
        colorMap.put(parent, parentColor);
        for (int i : graph[parent]) {
            // call dfs to child and send inverted color. if it return false, immediately terminate
            //as false condition, otherwise continue to next child check.
           if (!dfs(i, parentColor * -1, colorMap, graph)) {
               return false;
           }
        }
        
        return true;
    }
}
