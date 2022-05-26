// https://leetcode.com/problems/network-delay-time/

class Solution {
    private class Pair implements Comparable<Pair> {
        int node;
        int time;
        
        public Pair(int node, int time) {
            this.node = node;
            this.time = time;
        }
        
        public int compareTo(Pair other) {
            return this.time - other.time;
        }
    }
    
    public int networkDelayTime(int[][] times, int n, int k) {
        boolean[] visited = new boolean[n];
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        Map<Integer, List<Pair>> adjMap = new HashMap<>();
        
        for (int[] arr : times) {
            int source = arr[0] - 1;
            int dest = arr[1] - 1;
            int time = arr[2];
            adjMap.computeIfAbsent(source, x -> new ArrayList<>()).add(new Pair(dest, time));
        }

        int[] timesFromSource = new int[n];
        Arrays.fill(timesFromSource, Integer.MAX_VALUE);
        timesFromSource[k - 1] = 0;
        pq.add(new Pair(k - 1, 0));
        
        while (!pq.isEmpty()) {
            Pair min = pq.poll();
            int node = min.node;
            int parentTime = min.time;
            visited[node] = true;
            
            if (adjMap.containsKey(node)) {
                for (Pair p : adjMap.get(node)) {
                    if (!visited[p.node] && p.time + parentTime < timesFromSource[p.node]) {
                        timesFromSource[p.node] = p.time + parentTime;
                        p.time = timesFromSource[p.node];
                        pq.add(p);
                    }
                }
            }
        }

        boolean allVisited = true;
        for (boolean b : visited) {
            if (!b) {
                allVisited = false;
                break;
            }
        }
        int result = Arrays.stream(timesFromSource).boxed().max(Comparator.naturalOrder()).get();
        return allVisited  ? result : -1;
    }
}
