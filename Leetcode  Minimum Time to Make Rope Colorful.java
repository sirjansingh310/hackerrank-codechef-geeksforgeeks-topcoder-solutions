// https://leetcode.com/problems/minimum-time-to-make-rope-colorful/
// Just pick the max from each cluster of colors, and subtract that with overall sum. This gives us cost to remove cheaper ones from each cluster
class Solution {
    public int minCost(String colors, int[] neededTime) {
        int totalSum = 0;
        for (int i = 0; i < neededTime.length; i++) {
            totalSum += neededTime[i];
        }
        
        int max = neededTime[0];
        int toRemove = neededTime[0];
        char currentColor = colors.charAt(0);
        
        for (int i = 1; i < colors.length(); i++) {
            if (colors.charAt(i) == currentColor && neededTime[i] > max) {
                toRemove -= max;
                max = neededTime[i];
                toRemove += max;
            } else if (colors.charAt(i) != currentColor) {
                currentColor = colors.charAt(i);
                toRemove += neededTime[i];
                max = neededTime[i];
            }
        }
        
        return totalSum - toRemove;
    }
}
