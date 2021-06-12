// https://leetcode.com/explore/challenge/card/june-leetcoding-challenge-2021/604/week-2-june-8th-june-14th/3776/
/*
When driving past a gas station, let's remember the amount of fuel it contained. We don't need to decide yet whether to fuel up here or not - for example, there could be a bigger gas station up ahead that we would rather refuel at.

When we run out of fuel before reaching the next station, we'll retroactively fuel up: greedily choosing the largest gas stations first.

This is guaranteed to succeed because we drive the largest distance possible before each refueling stop, and therefore have the largest choice of gas stations to (retroactively) stop at.
*/
class Solution {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        if (stations.length == 0) {
            return startFuel >= target ? 0 : -1 ;
        }
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        int currentFuel = startFuel;
        int prev = 0;
        int visited = 0;
        
        for (int i = 0; i < stations.length; i++) {
            currentFuel -= (stations[i][0] - prev);
            if (currentFuel >= 0) {
                pq.add(stations[i][1]);// we have unlimited tank, lets add gas station 
                // to pq. If we ever require it, we get it by pq.poll. This will simulate the idea
                // that we visited the gas station.
            } else {
                while (!pq.isEmpty()) {
                    currentFuel += pq.poll(); // consuming best possible fuel. So in this way, we will
                    // visit less number of stations(greedy)
                    visited++;
                    if (currentFuel >= 0) {
                        pq.add(stations[i][1]); // add to pq
                        break;
                    }
                }
                if (currentFuel < 0) {
                    return -1; // cannot go ahead after consuming all fuel from pq
                }
            }
            
            prev = stations[i][0];
        }
        
        // repeat logic from last station to target
        currentFuel -= (target - prev);
        
        if (currentFuel < 0) {
            while (!pq.isEmpty()) {
                currentFuel += pq.poll();
                visited++;
                if (currentFuel >= 0) {
                    break;
                }
            }
        }
        
        if (currentFuel < 0) { // still not enough to reach target after consuming all fuel from pq
            return -1;
            
        }
        
        
        return visited;
        
    }
}
