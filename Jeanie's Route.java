import java.util.*;

/*
Passes all test cases and does not use recursion:

The basic idea is that we compute a minimum spanning subtree, e.g. the smallest subtree that spans all the cities that we need to deliver letters to. This can be done in O(N) time by putting all non-letter degree-1 cities in a queue, then repeatedly "pruning" them (e.g. ticking them off in a bit mask), then whenever we detect a parent that is also non-letter and has degree 1, we add it to the queue.
The final resulting subtree has no leaves which are non-letter cities, and also contains all letter cities, so by definition is the "minimum spanning subtree". Note this is not related to the standard minimum spanning tree algorithm.

Once we have a bitmask telling us which nodes are in the subtree, we find the diameter of the subtree using two Breadth first searches (https://cs.stackexchange.com/questions/22855/algorithm-to-find-diameter-of-a-tree-using-bfs-dfs-why-does-it-work though that thread is for weight-1 edges, it is straightforward to generalize to arbitrarily weighted edges: simply keep track of all distances from the source while you are BFSing). Finally, the answer, e.g. the minimum distance needed to travel to cover the entire subtree, is just twice the total weight of that subtree minus the diameter.
*/
class Graph{
    private int nodeCount;
    private List<Integer> adjList[];
    private HashMap<Integer, Integer> cost[];
    private boolean isTargetNode[];
    private boolean isNodePruned[];
    private int treeDiameter;
    private int distFromBfsSource[];
    private int prunedTreeTotalWeight;
    Graph(int nodeCount, boolean isTargetNode[]){
        this.isTargetNode = isTargetNode;
        this.nodeCount = nodeCount;
      //  cost = new int[nodeCount][nodeCount]; runtime error. heap space issue for large nodeCount
        cost = new HashMap[nodeCount];// array index = souce, hashmap key = destination, hashmap value = dist
        isNodePruned = new boolean[nodeCount];
        adjList = new ArrayList[nodeCount];
        prunedTreeTotalWeight = 0;

        for(int i = 0; i < nodeCount; i++){
            adjList[i] = new ArrayList<Integer>();
            cost[i] = new HashMap<Integer, Integer>();
        }
    
    }

    public void addEdge(int from, int to, int dist){
        adjList[from].add(to);
        adjList[to].add(from);
        cost[from].put(to, dist);
        cost[to].put(from, dist);
    }

    public void prune(){
        LinkedList<Integer> queue = new LinkedList<>();
        int degree[] = new int[nodeCount];
        for(int node = 0; node < nodeCount; node++){
            degree[node] = adjList[node].size();
        }
        for(int node = 0; node < nodeCount; node++){
            if(degree[node] == 1 && !isTargetNode[node])
                queue.add(node);
        }

        while(queue.size() > 0){
            int currentNode = queue.remove();
            isNodePruned[currentNode] = true;

            for(Integer childNode : adjList[currentNode]){
                if(degree[childNode] == 2 && !isTargetNode[childNode]){ // if deg == 2, it means that it is connected to parent and some other node. As parent is marked as removed, the child's actual deg =1 and it  will become leaf node. Hence it should also be marked as removed.
                    queue.add(childNode);
                }
                degree[childNode]--; // if we don't do this, we get tle. parent will refer child, child will refer parent. pruning never stops
                
            }
        }
    }



    public void calculateDiameter(){
        int startNode = getBfsStartNode(); // can be any node but we want the node left in subgraph

        int farthestNode = bfs(startNode);
        // farthestNode stores the node with max dist from startNode. Usually it's last 
        // visited node in bfs in an un-weighted graph.
        prunedTreeTotalWeight = 0; // reset after first bfs call, otherwise it will calculate again
        farthestNode = bfs(farthestNode);
        treeDiameter = distFromBfsSource[farthestNode]; 

    }

    public int getPrunedTreeTotalWeight(){
        return prunedTreeTotalWeight;
    }

    public int getPrunedTreeDiameter(){
        return treeDiameter;
    }

    public int getBfsStartNode(){
        int startNode = 0;
        for(int node = 0; node < nodeCount; node++){
            if(!isNodePruned[node]){
                startNode = node;
                break;
            }
        }
        return startNode;
    
    }

    public int bfs(int start){
        distFromBfsSource = new int[nodeCount]; // we only care about this in our 2nd bfs call to calculate diameter
        Arrays.fill(distFromBfsSource, -1);
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(start);
        distFromBfsSource[start] = 0;
        int farthestNode = start;
        int maxDist = -1;
        while(queue.size() > 0){
            int currentNode = queue.remove();
            for(Integer childNode : adjList[currentNode]){
                if(!isNodePruned[childNode] && distFromBfsSource[childNode] == -1){
                    queue.add(childNode);
                    int currentEdgeWeight = cost[currentNode].get(childNode);
                    distFromBfsSource[childNode] = distFromBfsSource[currentNode] + currentEdgeWeight;
                    prunedTreeTotalWeight += currentEdgeWeight;
                    if(distFromBfsSource[childNode] > maxDist){
                        maxDist = distFromBfsSource[childNode];
                        farthestNode = childNode;
                        
                    }
                }
            }
        }
         return farthestNode;
    }

}

public class MainClass{
    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        int nodeCount, targetCount;
        nodeCount = scanner.nextInt();
        targetCount = scanner.nextInt();
        boolean isTargetNode[] = new boolean[nodeCount];
        for(int i = 0; i < targetCount; i++){
            int node = scanner.nextInt() - 1;
            isTargetNode[node] = true;
        }
        Graph graph = new Graph(nodeCount, isTargetNode);
        int from, to, dist;

        for(int i = 0; i < nodeCount - 1; i++){
            from = scanner.nextInt() - 1;
            to = scanner.nextInt() - 1;
            dist = scanner.nextInt();
            graph.addEdge(from, to, dist);
        }
        graph.prune(); // remove leaf nodes until we have a subgraph of targetnodes + non-target nodes(deg > 1)
        graph.calculateDiameter();
        
        int prunedTreeTotalWeight = graph.getPrunedTreeTotalWeight();
        int prunedTreeDiameter = graph.getPrunedTreeDiameter();

        System.out.println(2 * prunedTreeTotalWeight - prunedTreeDiameter);

    }
}
