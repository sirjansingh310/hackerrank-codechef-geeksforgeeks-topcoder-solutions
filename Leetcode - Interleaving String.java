// https://leetcode.com/explore/challenge/card/june-leetcoding-challenge-2021/603/week-1-june-1st-june-7th/3765/

class Solution {
    private Map<String, Boolean> memo = new HashMap<>();
    private boolean recur(String s1, int idx1, String s2, int idx2, String s3, int idx3) {
        // all three index pointers are important for the state of recursion
        // they might eventually meet each other again, so cache it
        String key = idx1 + "_" + idx2 + "_" + idx3;
        
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        
        // full match
        if (idx3 == s3.length() && idx1 == s1.length() && idx2 == s2.length()) {
            return true;
        }
        
        boolean isInterleave = false;
        
        // recur char by char 
        if (idx1 < s1.length() && idx3 < s3.length() && s1.charAt(idx1) == s3.charAt(idx3)) {
            isInterleave = recur(s1, idx1 + 1, s2, idx2, s3, idx3 + 1);
        }
        
        if (!isInterleave && idx2 < s2.length() && idx3 < s3.length() && s2.charAt(idx2) == s3.charAt(idx3)) {
            isInterleave = recur(s1, idx1, s2, idx2 + 1, s3, idx3 + 1);
        }
        
        memo.put(key, isInterleave);
        return isInterleave;
        
    }
    
    
    public boolean isInterleave(String s1, String s2, String s3) {
        // handle some base cases
        if (s1.equals("")) {
            return s2.equals(s3);
        } else if (s2.equals("")) {
            return s1.equals(s3);
        } else if (s3.equals("")) {
            return false;
        }
        
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        // idea is to go character by character in s1, s2, s3.
        // comparing by chunk might be bad, example s1 has "ccc" at some place and s3 also has "ccc"
        // considering "ccc" of both match, we might think we are traversing faster
        // but eventually it might give wrong result(ex: so it was "c" from s1 only and "cc" from s2 which leads to correct result)
        // therefore go char by char, i.e brute force recursion and cache results for speed
        return recur(s1, 0, s2, 0, s3, 0);
    }
}
