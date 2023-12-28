// https://leetcode.com/problems/string-compression-ii/description/
// Hard problem, not so clean solution

class Key {
    private final int i, j, k;
    private final char ch;

    public Key(int i, int j, int k, char ch) {
        this.i = i;
        this.j = j;
        this.k = k;
        this.ch = ch;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Key) {
            Key otherKey = (Key) other;
            return this.i == otherKey.i && this.j == otherKey.j && this.k == otherKey.k && this.ch == otherKey.ch;
        }
        return false;
    }

    @Override 
    public int hashCode() {
        int code = 31 * this.i;
        code = 31 * code + this.j;
        code = 31 * code + this.k;
        code = 31 * code + this.ch;
        return code; 
    }
}


class Solution {
    private static final Set<Integer> incrementSet = new HashSet<>(Arrays.asList(1, 9, 99));
    private final Map<Key, Integer> memo = new HashMap<>();
  
    public int getLengthOfOptimalCompression(String s, int k) {
        return recur(s, 0, k, ' ', 0);
    }

    private int recur(String s, int index, int k, char prev, int prevCount) {
        Key key = new Key(index, k, prevCount, prev);

        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        if (index == s.length()) {
            return 0;
        }

        int result;
        if (s.charAt(index) == prev) {
            // for our constraints, we increment length only when prevCount is 1, 9 or 99. when 1, string was "a", now it becomes "a2" so length increases. Same for a9 -> a10 and a99 -> a100
            // otherwise we dont increase string length for other cases, ex a14 -> a15
            result = recur(s, index + 1, k, prev, prevCount + 1); // here we dont delete in middle of a streak. This char being deleted is being covered in the else case anyways, where we do both delete and non delete of a char. In the begining of recursion(i.e the else is executed first in the recur since our first prev == ' ')

            if (incrementSet.contains(prevCount)) {
                result++;
            }
        } else {
            int withDelete = Integer.MAX_VALUE;
            if (k > 0) {
                withDelete = recur(s, index + 1, k - 1, prev, prevCount);
            }

            // new streak
            int withoutDelete = 1 + recur(s, index + 1, k, s.charAt(index), 1);
            

            result = Math.min(withDelete, withoutDelete);
        }

        memo.put(key, result);
        return result;
    }
}
