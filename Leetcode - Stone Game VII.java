/// https://leetcode.com/explore/challenge/card/june-leetcoding-challenge-2021/604/week-2-june-8th-june-14th/3775/
class Solution {
    private int memo[][];
    
    private int getDiff(int sum, int left, int right, int[] stones) {
        
        if (left >= right || sum <= 0) {
            memo[left][right] = 0;
            return 0;
        }
        
        if (memo[left][right] != -1) {
            return memo[left][right];
        }
        
        // alice wants to maximize the score diff, bob wants to minimize the score diff
        // both can achieve this by getting maximum possible score for themselves
        
        // Think game is already optimally played in next recursion call, the score in (this move - optimal diff in next moves) should be returned
        int withLeft = sum - stones[left] - getDiff(sum - stones[left], left + 1, right, stones);
        int withRight = sum - stones[right] - getDiff(sum - stones[right], left, right - 1, stones);
        memo[left][right] = Math.max(withLeft, withRight);
        
        return memo[left][right];
    }
    
    public int stoneGameVII(int[] stones) {
        int sum = 0;
        for (int stone : stones) {
            sum += stone;
        }
        memo = new int[stones.length][stones.length];
        
        for (int i = 0; i < stones.length; i++) {
            Arrays.fill(memo[i], -1);
        }
        return getDiff(sum, 0, stones.length - 1, stones);
    }
}
