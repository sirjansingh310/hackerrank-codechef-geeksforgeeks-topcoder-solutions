//https://leetcode.com/explore/challenge/card/july-leetcoding-challenge-2021/608/week-1-july-1st-july-7th/3802/
class Solution {
    private static Map<Character, Set<Character>> map;
    private Map<String, Long> memo;
    private final int MOD = 1000000007;
    static {
        map = new HashMap<>();
        map.put('a', new HashSet<>(Arrays.asList('e')));
        map.put('e', new HashSet<>(Arrays.asList('a', 'i')));
        map.put('i', new HashSet<>(Arrays.asList('a', 'e', 'o', 'u')));
        map.put('o', new HashSet<>(Arrays.asList('i', 'u')));
        map.put('u', new HashSet<>(Arrays.asList('a')));
        
    }
    
    private long recur(char lastChar, int n) {
        long count = 0;
        if (n == 0) {
            return 1;
        }
        if (lastChar == ' ') {
            for (char c : map.keySet()) {
                count = ((count % MOD) + (recur(c, n - 1) % MOD) % MOD);
            }
            return count;
        } else {
            String key = lastChar + "_" + n;
            if (memo.containsKey(key)) {
                return memo.get(key);
            }
            for (char c : map.get(lastChar)) {
                count = ((count % MOD) + (recur(c, n - 1) % MOD)) % MOD;
            }
            memo.put(key, count);
        }
        return count;
    }
    
    public int countVowelPermutation(int n) {
        memo = new HashMap<>();
        return (int)(recur(' ', n) % MOD);
    }
}
