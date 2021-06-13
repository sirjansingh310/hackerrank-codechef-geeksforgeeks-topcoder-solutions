// https://leetcode.com/problems/minimum-deletion-cost-to-avoid-repeating-letters/
// O(N) Time, O(1) Space

class Solution {
    public int minCost(String s, int[] cost) {
        int total = 0; // total cost of all characters present in String s
        for (int i = 0; i < cost.length; i++) {
            total += cost[i];
        }
        
        int finalCost = 0; // total cost of all characters in final String s, after performing delete operations
        char prev = s.charAt(0);
        int currentCharCost = cost[0];
        
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == prev) {
                currentCharCost = Math.max(currentCharCost, cost[i]);
            } else {
                finalCost += currentCharCost;
                prev = s.charAt(i);
                currentCharCost = cost[i];
            }
        }
        
        // for last character
        if (s.charAt(s.length() - 1) == s.charAt(s.length() - 2)) {
            finalCost += currentCharCost;
        } else {
            finalCost += cost[cost.length - 1];
        }
        
        return total - finalCost;
    }
}
