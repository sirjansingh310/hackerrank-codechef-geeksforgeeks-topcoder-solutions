// https://leetcode.com/problems/ipo/
class Solution {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        PriorityQueue<int[]> waitingQueue = new PriorityQueue<>((p1, p2) -> p1[0] - p2[0]); // projects sorted by capital in asc order
        PriorityQueue<int[]> jobQueue = new PriorityQueue<>((p1, p2) -> p2[1] - p1[1]); // ready to be picked, sorted by profit

        for (int i = 0; i < profits.length; i++) {
            waitingQueue.add(new int[]{capital[i], profits[i]});
        }

        for (int i = 0; i < k; i++) {
            // qualify projects if they can be done
            while (!waitingQueue.isEmpty() && waitingQueue.peek()[0] <= w) {
                jobQueue.add(waitingQueue.poll());
            }

            if (jobQueue.isEmpty()) {
                return w;
            }

            w += jobQueue.poll()[1]; // update current money
        }

        return w;
    }
}
