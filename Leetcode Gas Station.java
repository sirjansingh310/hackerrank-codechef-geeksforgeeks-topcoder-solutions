// https://leetcode.com/problems/gas-station/submissions/
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalCost = 0;
        int start = 0;
        int currentFuel = 0;
        for (int i = 0; i < gas.length; i++) {
            currentFuel += (gas[i] - cost[i]);
            totalCost += (gas[i] - cost[i]);
            if (currentFuel < 0 && cost[i] > gas[i]) { // deadlock, we cant go ahead
            
                currentFuel = 0;// Greedy expl:  we have to reset, if the i + 1 start works to take 
                // us till end of array, that means it is the answer since we were able
                // to travel from 0 to here anyways. So we can travel in a full circle
                // without us having to check for it. 
                start = (i + 1) % (gas.length);
            }
        }
        
        return totalCost < 0 ? -1 : start;
    }
}
