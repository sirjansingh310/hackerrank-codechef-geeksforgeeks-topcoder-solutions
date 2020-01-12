// https://www.youtube.com/watch?v=0CKUjDcUYYA watch from 16min
// https://leetcode.com/problems/longest-palindromic-substring
class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        int bestLen = 0;
        String res = "";
        // odd case, aba , b is mid and is considered in substring
        for(int mid = 0; mid < n; mid++){
            for(int x = 0; mid - x >= 0 && mid + x < n; x++){
                if(s.charAt(mid - x) != s.charAt(mid + x))
                    break;
                int len = 2 * x + 1;
                if(len > bestLen){
                    bestLen = len;
                    res = s.substring(mid -x, mid + x + 1);
                }
            }
        }
        // even case, aa, mid is not considered in substring
        
        for(int mid = 0; mid < n -1; mid++){
            // middle is between mid and mid + 1 here. ex abba, there are 2 middle chars here or no middle char if you think
            for(int x = 1; mid - x + 1 >= 0 && mid + x < n; x++){ // sub x = 1 in the boolean cond. we get mid and mid + 1
                if(s.charAt(mid - x + 1) != s.charAt(mid + x))
                    break;
                int len = 2 * x;
                if(len > bestLen){
                    bestLen = len;
                    res = s.substring(mid -x + 1,  mid + x + 1);
                
            }
            
        }
    }
        return res;
 }
}
