// https://leetcode.com/explore/challenge/card/january-leetcoding-challenge-2021/582/week-4-january-22nd-january-28th/3619/

class Solution {
    public String getSmallestString(int n, int k) {
        StringBuilder sb = new StringBuilder("");
        // greedy
        // as we want lexiographically smallest, try putting big characters 
        // like z,y,x... as last characters, so the starting characters are left
        // with smaller numbers like 1, 2 (to contribute to sum k).
        // in this way, our string starts with smaller characters(a, b, c..)
        
        char current = 'z';
        int count = 1;
        while (count <= n) {
            int intValue = current - 'a' + 1;
            if (k - intValue >= 0 && (k - intValue) >= (n - count)) {// possible to pick a char so sum remaining k is positive and still at the same time should be able to pick a character in the future
                // example case: n = 52, k = 52. we want "aaa... 52 times" instead of "zz"
                k -= intValue;
                sb.append(current);
                count++;
            } else {
                current--;
            }
        }
        return sb.reverse().toString();
    }
}
