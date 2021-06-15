//https://leetcode.com/explore/challenge/card/june-leetcoding-challenge-2021/605/week-3-june-15th-june-21st/3780/
class Solution {
    private int[] matchsticks;
    private boolean recur(int index, int s1, int s2, int s3, int s4) {
        if (s1 == 0 && s2 == 0 && s3 == 0 && s4 == 0) {
            return true;
        }
        
        if (index >= matchsticks.length) {
            return false;
        }
        
        boolean result = false;
        if (s1 - matchsticks[index] >= 0) {
            result = recur(index + 1, s1 - matchsticks[index], s2, s3, s4);
        }
        
        if (!result && s2 - matchsticks[index] >= 0) {
            result = recur(index + 1, s1, s2 - matchsticks[index], s3, s4);
        }
        
        if (!result && s3 - matchsticks[index] >= 0) {
            result = recur(index + 1, s1, s2, s3 - matchsticks[index], s4);
        }
        
        if (!result && s4 - matchsticks[index] >= 0) {
            result = recur(index + 1, s1, s2, s3, s4 - matchsticks[index]);
        }
        return result;
    }
    public boolean makesquare(int[] matchsticks) {
        int sum = 0;
        this.matchsticks = matchsticks;
        for (int i : matchsticks) {
            sum += i;
        }
        if (sum % 4 != 0) {
            return false;
        }
        int requiredSumPerSide = sum / 4;
        return recur(0, requiredSumPerSide, requiredSumPerSide, requiredSumPerSide, requiredSumPerSide);
    }
}
