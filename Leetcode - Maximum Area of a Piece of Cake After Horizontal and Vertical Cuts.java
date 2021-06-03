// https://leetcode.com/explore/challenge/card/june-leetcoding-challenge-2021/603/week-1-june-1st-june-7th/3766/
class Solution {
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        final int MOD = 1000000007;
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        
        long maxLength = 0L, maxWidth = 0L;
        
        for (int i = 0; i <= horizontalCuts.length; i++) {
            long length;
            if (i == 0) {
                length = horizontalCuts[0];
            } else if (i == horizontalCuts.length) {
                length = h - horizontalCuts[i - 1];
            } else {
                length = horizontalCuts[i] - horizontalCuts[i - 1];
            }
            maxLength = Math.max(length, maxLength);
        }
        
        
        for (int i = 0; i <= verticalCuts.length; i++) {
            long width;
            if (i == 0) {
                width = verticalCuts[0];
            } else if (i == verticalCuts.length) {
                width = w - verticalCuts[i - 1];
            } else {
                width = verticalCuts[i] - verticalCuts[i - 1];
            }
            maxWidth = Math.max(width, maxWidth);
        }
        
        return (int)(((maxLength % MOD) * (maxWidth % MOD)) % MOD);
    }
}
