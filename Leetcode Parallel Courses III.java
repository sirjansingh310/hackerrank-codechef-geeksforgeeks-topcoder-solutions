// https://leetcode.com/problems/parallel-courses-iii/description
// LC Hard, but not so hard. 
class Solution {
    public int minimumTime(int n, int[][] relations, int[] time) {
        List<Integer>[] adj = new List[n];
        int[] indegree = new int[n];
        int[] jobEndTimeStamp = new int[n];
        int[] potentialInsertTimestamp = new int[n];
        Arrays.fill(potentialInsertTimestamp, Integer.MIN_VALUE);

        Queue<int[]> queue = new LinkedList<>(); // node id + time inserted at

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }

        for (int[] arr : relations) {
            int from = arr[0] - 1, to = arr[1] - 1;
            adj[from].add(to);
            indegree[to]++;
        }
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                queue.add(new int[]{i, 0});
            }
        }

        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int parent = arr[0], insertTime = arr[1];
            jobEndTimeStamp[parent] = insertTime + time[parent];

            for (int child : adj[parent]) {
                indegree[child]--; 
                potentialInsertTimestamp[child] = Math.max(potentialInsertTimestamp[child], jobEndTimeStamp[parent]); // maybe, maybe not, actual insert time in queue determined when indegree[child] == 0
                if (indegree[child] == 0) {
                    queue.add(new int[]{child, potentialInsertTimestamp[child]});
                }
            }
        }
        
        int result = -1;
        System.out.println(Arrays.toString(jobEndTimeStamp));
        for (int end : jobEndTimeStamp) {
            result = Math.max(result, end);
        }

        return result;
    }
}
