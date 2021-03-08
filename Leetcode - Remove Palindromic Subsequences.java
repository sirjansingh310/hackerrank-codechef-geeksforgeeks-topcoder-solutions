//https://leetcode.com/explore/challenge/card/march-leetcoding-challenge-2021/589/week-2-march-8th-march-14th/3665/


// Better solution, leveraging the fact that the string will contain only 2 characters
class Solution {
    private boolean isPalindrome(String s) {
        for (int i = 0; i <= s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }
    public int removePalindromeSub(String s) {
        if (s.length() == 0) {
            return 0;
        }
        if (isPalindrome(s)) {
            return 1;
        }
        return 2; // the whole thing is not a palindrome, but string consists of 'a' & 'b'
        // remove all 'b's as first palindrome subseq and then a. Max step required = 2
    }
}

// original solution, will work for any string, didn't account the fact that the string will contain only 'a' and 'b'
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
