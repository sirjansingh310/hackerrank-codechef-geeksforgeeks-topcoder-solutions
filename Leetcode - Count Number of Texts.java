// https://leetcode.com/problems/count-number-of-texts/description/
class Solution {
    private static final long MOD = 1000000007;
    private static Map<Integer, Integer> keyMap = new HashMap<>();

    static {
        for (int i = 2; i < 10; i++) {
            keyMap.put(i, 3);
        }
        keyMap.put(7, 4);
        keyMap.put(9, 4);
    }

    public int countTexts(String pressedKeys) {
        long[] dp = new long[pressedKeys.length()];
        return (int) recur(pressedKeys, 0, dp);
    }

    private long recur(String pressedKeys, int index, long[] dp) {
        if (index == pressedKeys.length()) {
            return 1;
        } else if (dp[index] != 0) {
            return dp[index];
        }

        char ch = pressedKeys.charAt(index);
        long count = 0;
        int maxClicks = keyMap.get(ch - '0'), i = index;
        
        while (i < pressedKeys.length() && maxClicks-- > 0 && pressedKeys.charAt(i) == pressedKeys.charAt(index)) {
            // register a char input and move on
            count = (count + recur(pressedKeys, i + 1, dp) + MOD) % MOD;
            i++;
        }
        dp[index] = count;
        return count;
    }
}
