/*
https://www.spoj.com/problems/ADAROADS/
https://www.geeksforgeeks.org/kruskals-minimum-spanning-tree-algorithm-greedy-algo-2/

*/
import java.util.*;
class Graph{
    private int nodeCount;
    private ArrayList<Edge> edgeList;
    public Graph(int nodeCount){
        this.nodeCount = nodeCount;
        edgeList = new ArrayList<>();
    }
    
    public void addEdge(int source, int destination, int weight){
        Edge edge = new Edge(source, destination, weight);
        edgeList.add(edge);
    }
    public int find(Subset subsets[], int node){
        if(subsets[node].parent != node)
            subsets[node].parent = find(subsets, subsets[node].parent); // flatten the tree. i.e add the current node as immediate child of root
        return subsets[node].parent;
    }
    public void union(Subset subsets[], int parentU, int parentV){
    //    int parentU = find(subsets, u);
      //  int parentV = find(subsets, v);
        // Attach smaller rank tree under root of high rank tree 
        // (Union by Rank) 
        if(subsets[parentU].rank < subsets[parentV].rank){
            subsets[parentU].parent = parentV;
        }
        else if(subsets[parentU].rank > subsets[parentV].rank){
            subsets[parentV].parent = parentU;
        }
        // if ranks are same, attach one to another and increment it's rank as the resulting tree has one extra layer 
        else{
            subsets[parentU].parent = parentV;
            subsets[parentV].rank++;
        }
    }
    public long getMstWeight(){
        long sum = 0L;
        Subset subsets[] = new Subset[nodeCount];
        // Initially, each node is isolated in its own subset
        for(int i = 0; i < nodeCount; i++){
            subsets[i] = new Subset();
            subsets[i].parent = i;
            subsets[i].rank = 0;
        }
        // sort the edges
        Collections.sort(edgeList);
        for(Edge currentEdge : edgeList){
          //  System.out.println(edgeList + " " + currentIndex);
            //Edge currentEdge = edgeList.get(currentIndex);
            int rootU = find(subsets, currentEdge.source);
            int rootV = find(subsets, currentEdge.destination);
            if(rootU != rootV){
                union(subsets, rootU, rootV);
                sum += (long)currentEdge.weight;
            }
        }
        return sum;
    }
}
class Subset{
    int parent;
    int rank;
}
class Edge implements Comparable<Edge>{
    public int source;
    public int destination;
    public int weight;
    public Edge(int source, int destination, int weight){
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
    public int compareTo(Edge edge){
        return this.weight - edge.weight;
    }
    public String toString(){
        return "[Source = " + source + " destination = " + destination + " weight = " + weight + " ] \n";
    }
}
 class AdaRoadsKrushkal{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        long prevResult = 0;
        Graph graph = new Graph(n);
        for(int i = 0; i < m; i++){
            long s = sc.nextLong();
            long d = sc.nextLong();
            long w = sc.nextLong();
            graph.addEdge((int)(s ^ prevResult), (int)(d ^ prevResult), (int)(w ^ prevResult));
            long result = graph.getMstWeight();
            System.out.println(result);
            prevResult = result;
        }
    }
}
