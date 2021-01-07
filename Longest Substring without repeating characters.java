//https://leetcode.com/explore/challenge/card/january-leetcoding-challenge-2021/579/week-1-january-1st-january-7th/3595/

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int prevDuplicateSeen = -1;
        int maxLength = 0;
        Map<Character, Integer> lastSeen = new HashMap<>();
        
        for (int i = 0; i < s.length(); i++) {
            if (lastSeen.containsKey(s.charAt(i))) {
                prevDuplicateSeen = Math.max(prevDuplicateSeen, lastSeen.get(s.charAt(i)));
            }
            lastSeen.put(s.charAt(i), i);
            maxLength = Math.max(maxLength, i - prevDuplicateSeen);
        }
   
        return maxLength;
    }
}
