//https://leetcode.com/explore/challenge/card/march-leetcoding-challenge-2021/589/week-2-march-8th-march-14th/3665/
class Solution {
    private String removeProcessedPalindromeSubseq(char[] ch) {
        StringBuilder sb = new StringBuilder("");
        for (char c : ch) {
            if (c != 'X') {
                sb.append(c);
            }
        }
        return sb.toString();
    }
    public int removePalindromeSub(String s) {
        int count = 0;
        
        while (s.length() != 0) {
            char[] ch = s.toCharArray();
            int left = 0, right = s.length() - 1;

            while (left <= right) {
                if (ch[left] == ch[right]) {
                    ch[left] = 'X';
                    ch[right] = 'X';
                    left++;
                    right--;
                }
                if (left > right) {
                    break;
                }
                if (ch[left] != ch[right]) {
                    while (left < ch.length && ch[left] != ch[right]) {
                        left++; // 
                        ch[left] = 'X';
                    }                    
                    while (right >= 0 && ch[right] != ch[left]) {
                        right--;
                        ch[right] = 'X';
                    }
                }
                
            }
            count++;
            s = removeProcessedPalindromeSubseq(ch);
        }
        return count;
    }
}
