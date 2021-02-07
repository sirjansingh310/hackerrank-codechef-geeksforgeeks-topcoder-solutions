// https://leetcode.com/explore/featured/card/february-leetcoding-challenge-2021/584/week-1-february-1st-february-7th/3631/
class Solution {
    public int[] shortestToChar(String s, char c) {
        int[] result = new int[s.length()];
        
        List<Integer> charIndexes = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                charIndexes.add(i);
            }
        }
        
        int first = charIndexes.get(0);// constraint that minimum one char is string s will be char c, so safe.
        int lastRetrieved = 0;
        int second = -1;
        if (charIndexes.size() > 1) {
            second = charIndexes.get(1);
            lastRetrieved = 1;;// index from charIndexes collection where last index of char c was extracted
        }
        
        
        for (int i = 0; i < s.length(); i++) {
            if (i == second) {// update first & second, for characters to be compared from now. they 
                // will be more closer to first & second just calculated and old first will obviously
                // be more far, so update it.
                first = second;
                lastRetrieved++;
                second = lastRetrieved < charIndexes.size() ? charIndexes.get(lastRetrieved) : -1;
            }
            int distFromFirst = Math.abs(i - first);
            int distFromSecond = second != -1 ? Math.abs(i - second) : Integer.MAX_VALUE;
            
            result[i] = Math.min(distFromFirst, distFromSecond);
        }
        
        
        return result;
    }
}
