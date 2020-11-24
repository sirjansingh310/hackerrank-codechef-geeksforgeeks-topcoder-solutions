class Graph {
    public int n;
    public List<Integer> adj[];
    public int degree[];
    
    Graph(int n) {
        this.n = n;
        this.adj = new ArrayList[n];
        this.degree = new int[n];
        for(int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
    }
    
    public void addEdge(int s, int d) {
        adj[s].add(d);
        adj[d].add(s);
        degree[s]++;
        degree[d]++;
    }
    
    public List<Integer> getMHTNodes() {
//          https://leetcode.com/explore/challenge/card/november-leetcoding-challenge/564/week-1-november-1st-november-7th/3519/
        
//         Brute force O(n^2):
//         For each node, run BFS to find the height of the tree(max depth) and store in array. Find              the least among the array, and return a List<Integer> of all indexes matching this height,              that is roots which have this minimum height.
            
//         Efficient way O(n): 
//             A MHT will be formed from a node, which if considered as root, creates the most balanced
//             kind of tree.
//             A tree rooted from a leaf node, will not be balanced at all, so we can say leaf nodes 
//             will not contribute to our result
//             So, it is safe to say, we can remove the leaf nodes, and the subtree formed will still 
//             have the ans(i.e nodes when considered root, tree is most balanced).
                
//             Now, our new tree, also has some leaf nodes, and the idea can be applied again.
//             In this way, if we keep chopping the tree, until we have 1 or 2 nodes(depends on if the
//             initial tree had odd or even number of nodes).
                
//             These left over nodes (either 1 left or 2 left) is the center point of our inital tree,
//             where tree will have minimum height, and are the MHT roots.
                
//             This logic can be applied to any tree, and in the end we are left with 1 or 2 nodes                    contributing to MHT. Therefore, MHT roots can be atmost 1 or 2 in number.
                
//             The problem's expected answer should be an array of roots for MHT, not the minimum                      height itself, so we can safely keep chopping and return the result, not caring about                  the actual height, to get to center points of tree, i.e nodes when rooted, tree has                    minimum height.
        
        LinkedList<Integer> leafQueue = new LinkedList<>();
        for(int i = 0; i < n; i++) {
            if(degree[i] == 1) {
                leafQueue.add(i);
            }
        }
        
        while(n > 2) { /// MHT's can be at max 2
            int currentLeafSize = leafQueue.size();
            n -= currentLeafSize; // reduce the tree size
            
            for(int i = 0; i < currentLeafSize; i++){
                int leaf = leafQueue.poll();
                degree[leaf]--;
                for(int nonLeaf: adj[leaf]) {
                    degree[nonLeaf]--;
                    if(degree[nonLeaf] == 1) {
                        leafQueue.add(nonLeaf);
                    }
                }
            }
            
        }
        
        return new ArrayList(leafQueue);
        
    }
}


class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if(edges.length == 0) {
            return Collections.singletonList(0);
        }
        Graph g = new Graph(n);
        
        for(int i = 0; i < edges.length; i++) {
            g.addEdge(edges[i][0], edges[i][1]);
        }
        
        return g.getMHTNodes();
        
    }
}
