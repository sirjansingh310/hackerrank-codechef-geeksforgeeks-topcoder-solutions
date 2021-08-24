// https://leetcode.com/problems/minimum-window-substring/

class Solution {
    public String minWindow(String s, String t) {
        if (t.length() > s.length()) {
            return "";
        }
        int left = 0, right = 0;
        Map<Character, Integer> sMap = new HashMap<>();
        Map<Character, Integer> tMap = new HashMap<>();
        
        for (int i = 0; i < t.length(); i++) {
            tMap.put(t.charAt(i), tMap.getOrDefault(t.charAt(i), 0) + 1);
        }
        
        int requiredChars = tMap.size();
        int currentGoodChars = 0;
        
        String result = "";
        int bestLen = s.length() + 1;
        int bestLeft = -1 , bestRight = -1;
        while (right < s.length()) {
            sMap.put(s.charAt(right), sMap.getOrDefault(s.charAt(right), 0) + 1);
            /// Important use .intValue() to compare two Integers's int value or use .equals
            // https://stackoverflow.com/questions/3637936/java-integer-equals-vs
            if (tMap.containsKey(s.charAt(right)) && tMap.get(s.charAt(right)).intValue() == sMap.get(s.charAt(right)).intValue()) {
                currentGoodChars++;
            }
            
            boolean valid = false;
            while (left <= right && currentGoodChars == requiredChars) {
                valid = true;
                sMap.put(s.charAt(left), sMap.get(s.charAt(left)) - 1);
                
                if (tMap.containsKey(s.charAt(left)) && tMap.get(s.charAt(left)).intValue() > sMap.get(s.charAt(left)).intValue()) {
                    currentGoodChars--;
                }
                left++;
            }
            
            if (valid) {
                int length = right - (left - 1);
                if (length < bestLen) {
                    bestLen = length;
                    bestLeft = left - 1;
                    bestRight = right;
                }
            }
            
            right++;
        }
        
        
        if (bestLen != s.length() + 1) {
            result = s.substring(bestLeft, bestRight + 1);
        }
        return result;
    }

}
