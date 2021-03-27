// https://leetcode.com/problems/palindromic-substrings/
class Solution {
    public int countSubstrings(String s) {
        int count = 0;
        
        for (int i = 0; i < s.length(); i++) {
            int left = i - 1, right = i + 1;
            count++;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                count++;
                left--;
                right++;
            }
        }
        
        
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                count++;
                int left = i - 1, right = i + 2;
                while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                    count++;
                    left--;
                    right++;
                }
            }
        }
        
        
        return count;
    }
}
