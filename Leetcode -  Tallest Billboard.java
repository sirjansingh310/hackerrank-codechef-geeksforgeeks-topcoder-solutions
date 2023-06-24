// https://leetcode.com/problems/tallest-billboard/description/
class Solution {
    private Map<String, Integer> memo = new HashMap<>();
    private static final int MIN_INF = -100000;

    public int tallestBillboard(int[] rods) {
        // b1 and b2 are two billboards, their diff should be zero, return max height of b1

        return getB1Height(rods, 0, 0);
    }

    private int getB1Height(int[] rods, int idx, int diff) { // diff is b1 - b2
        if (idx == rods.length) {
            if (diff == 0) {
                return 0;
            }
            return MIN_INF;
        }

        String key = idx + "_" + diff;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int best = getB1Height(rods, idx + 1, diff); // skip current rod
        int addInB1 = rods[idx] + getB1Height(rods, idx + 1, rods[idx] + diff);
        int addInB2 = getB1Height(rods, idx + 1, diff - rods[idx]);
        best = Math.max(best, addInB1);
        best = Math.max(best, addInB2);

        memo.put(key, best);
        return best;        
    }
}
