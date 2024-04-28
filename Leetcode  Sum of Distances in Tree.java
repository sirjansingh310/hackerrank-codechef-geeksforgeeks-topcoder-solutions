// https://leetcode.com/problems/sum-of-distances-in-tree/description/
class Solution {
    private List<List<Integer>> adj = new ArrayList<>();
    private int[] subtreeCount;
    private int[] distanceSum;
    private int distanceSumRoot;

    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        subtreeCount = new int[n];
        distanceSum = new int[n];

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int [] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        dfs(0, -1, 0);
        distanceSum[0] = distanceSumRoot;
        dfs2(0, -1);
        return distanceSum;
    }

    private int dfs(int node, int nodeParent, int depth) {
        int subtreeNodes = 1;
        for (int child : adj.get(node)) {
            if (child != nodeParent) {
                distanceSumRoot += (depth + 1);
                subtreeNodes += dfs(child, node, depth + 1);
            }
        }
        subtreeCount[node] = subtreeNodes;
        return subtreeNodes;
    }

    private void dfs2(int node, int nodeParent) {
        // now we are making node as root of our tree
        // the subtree nodes in original tree become one step closer up
        // the other nodes are pushed away. these both statements are 
        // true for the new root w.r.t original tree present to us
        // the ones getting closer, that distance needs to be subtracted
        // the ones getting far, that distance needs to be added, by one unit for each node

        for (int child : adj.get(node)) {
            if (child != nodeParent) {
                int closerAdjustment = subtreeCount[child];
                int farAdjustment = distanceSum.length - closerAdjustment;
                distanceSum[child] = distanceSum[node] - closerAdjustment + farAdjustment;

                dfs2(child, node);
            }
        }
    }
}
