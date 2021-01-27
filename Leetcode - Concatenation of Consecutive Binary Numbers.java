//https://leetcode.com/explore/challenge/card/january-leetcoding-challenge-2021/582/week-4-january-22nd-january-28th/3618/
// amazing solution

class Solution {
    private boolean isPowerOfTwo(int n) {
        return (n & (n - 1)) == 0;
    }
    public int concatenatedBinary(int n) {
        long current = 1L;
        long length = 1L;
        final long mod = 1000000007L;
        
        for (int i = 2; i <= n; i++) {
            if (isPowerOfTwo(i)) { // power of two detected, length of binary increases
                length++;// need to shift all bits to left with one extra than prev length, as power of 2.
            }
            current = (current << length) % mod;// prev binary = 11100, now make it 1110000000 
            current = (current + i) % mod;// add to fill in new zero Bits on right with new number, i.e concatinaton 
        }
        
        return (int)(current % mod);
    }
}
