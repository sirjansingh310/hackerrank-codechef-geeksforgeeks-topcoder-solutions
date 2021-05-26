/*
https://leetcode.com/explore/challenge/card/may-leetcoding-challenge-2021/601/week-4-may-22nd-may-28th/3756/

If each deci-binary number has no higher than a 1 in each position, then it will take at least x numbers to achieve an x in any given position of n.
This means that the largest character in any position in n will determine how many deci-binary numbers must be added together to obtain n.
*/

class Solution {
    public int minPartitions(String n) {
        int maxDigit = 1;
        
        for (int i = 0; i < n.length(); i++) {
            maxDigit = Math.max(maxDigit, n.charAt(i) - '0');
        }
        
        return maxDigit;
    }
}
