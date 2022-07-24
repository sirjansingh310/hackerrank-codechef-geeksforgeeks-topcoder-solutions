class Solution {
    private Map<String, Integer> map;
    private static final int NOT_POSSIBLE = Integer.MAX_VALUE;
    
    private int minCost(int[] houses, int[][] cost, int m, int n, int index, int colorsApplied, int target, int prevColor) {
        
        String key = index + "_" + colorsApplied + "_" + prevColor;
        if (map.containsKey(key)) {
            return map.get(key);
        }
        
        if (index >= m) {
            if (colorsApplied == target) {
                map.put(key, 0);
                return 0;
            }
            map.put(key, NOT_POSSIBLE);
            return NOT_POSSIBLE;
        }
        
        if (houses[index] != 0) {// already colored
            if (houses[index] != prevColor) {
                colorsApplied++;
            }
            
            int withSkip = minCost(houses, cost, m, n, index + 1, colorsApplied, target, houses[index]);
            map.put(key, withSkip);
            return withSkip;
        }
        
        int minCost = Integer.MAX_VALUE;
        // apply prevcolor to building
        if (prevColor != -1) {
            houses[index] = prevColor;
            int sameColorCost = minCost(houses, cost, m, n, index + 1, colorsApplied, target, houses[index]);
            if (sameColorCost != NOT_POSSIBLE) {
                sameColorCost += cost[index][prevColor - 1]; // adding only if possibe, to avoid overflow in addition with Integer.MAX_VALUE
            }
            minCost = sameColorCost;
            houses[index] = 0;// backtrack
        }
        
        // new color
        for (int i = 0; i < n; i++) {
            if ((i + 1) != prevColor) {
                houses[index] = i + 1;
                int diffColorCost = minCost(houses, cost, m, n, index + 1, colorsApplied + 1, target, houses[index]);
                if (diffColorCost != NOT_POSSIBLE) {
                    diffColorCost += cost[index][i];
                }
                minCost = Math.min(minCost, diffColorCost);
                houses[index] = 0;// backtrack
            }
        }
        map.put(key, minCost);
        return minCost;
    }
    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        map = new HashMap<>();
        int minOverall = minCost(houses, cost, m, n, 0, 0, target, -1);
        return minOverall == NOT_POSSIBLE ? -1 : minOverall;
    }
}
