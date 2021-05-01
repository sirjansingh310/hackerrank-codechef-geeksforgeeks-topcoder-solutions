// https://leetcode.com/explore/challenge/card/april-leetcoding-challenge-2021/596/week-4-april-22nd-april-28th/3721/
class Solution {
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> diffInHeightsPQ = new PriorityQueue<>(); // min heap
        
        for (int i = 0; i < heights.length - 1; i++) {
            int diff = heights[i + 1] - heights[i];
            
            if (diff > 0) {
                diffInHeightsPQ.add(diff);
            }
            
            if (diffInHeightsPQ.size() > ladders) { // we have enough differences(jumps to make) to cover, give the cheap diff jump a job for bricks, since ladders are not height restricted and used for bigger jumps. imagine the larger jumps which are deep in PQ, were part of ladder jump, and thus we came so far          
                bricks -= diffInHeightsPQ.poll();
            }
            
            if (bricks < 0) { // we have negative number of bricks now after previous step, we can't go more ahead, as this jump is not possible and invalid(ladders were used, since PQ was filled up and exceeded ladder size, meaning we had some jumps to make(big ones, stored below in PQ) and we consumed the ladder)
                return i;
            }
        }
        
        return heights.length - 1;
    }
}
