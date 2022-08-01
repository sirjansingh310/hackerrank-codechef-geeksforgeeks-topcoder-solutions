// https://leetcode.com/problems/permutation-in-string/
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }
        
        Map<Character, Integer> s1Map = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            s1Map.put(s1.charAt(i), s1Map.getOrDefault(s1.charAt(i), 0) + 1);
        }
        Map<Character, Integer> s2Map = new HashMap<>();
        
        int left = 0, right = 0;
        
        for (right = 0; right < s1.length(); right++) {
            s2Map.put(s2.charAt(right), s2Map.getOrDefault(s2.charAt(right), 0) + 1);
        }
        
        // now right - left + 1 must be s1.length()
        while (left <= right && right < s2.length()) {
            if (isPermutation(s1Map, s2Map)) {
                return true;
            } else {// remove and add one char to make size of s1
                s2Map.put(s2.charAt(right), s2Map.getOrDefault(s2.charAt(right), 0) + 1);
                s2Map.put(s2.charAt(left), s2Map.get(s2.charAt(left)) - 1);
                left++;
                right++;
            }
        }
        
        return isPermutation(s1Map, s2Map);
    }
    
    private boolean isPermutation(Map<Character, Integer> map1, Map<Character, Integer> map2) {
        for (char ch = 'a'; ch <= 'z'; ch++) {
            int c1 = map1.getOrDefault(ch, 0);// auto unboxing
            int c2 = map2.getOrDefault(ch, 0);
            if (c1 != c2) {
                return false;
            }
        }
        return true;
    }
}
