// https://www.youtube.com/watch?v=gqXU1UyA8pk
class Solution {
    public int characterReplacement(String s, int k) {
        int max = 0;
        int left = 0, right = 0;
        Map<Character, Integer> charFreqInWindow = new HashMap<>();
        
        
        while (left <= right && right < s.length()) {
            int windowLength = right - left + 1;
            charFreqInWindow.put(s.charAt(right), charFreqInWindow.getOrDefault(s.charAt(right), 0) + 1);
            int maxCharInWindowCount = getMaxCharInWindowCount(charFreqInWindow);
            
            // while invalid, fix to make valid
            while (windowLength - maxCharInWindowCount > k) { // invalid, shift left pointer
                    charFreqInWindow.put(s.charAt(left), charFreqInWindow.get(s.charAt(left)) - 1);
                    left++;
                    windowLength = right - left + 1;
                    maxCharInWindowCount = getMaxCharInWindowCount(charFreqInWindow);
                }
            // now windowLength - maxCharInWindow <= k which is valid
            max = Math.max(max, windowLength);
            right++;
        }
        
        return max;
    }
    
    private int getMaxCharInWindowCount(Map<Character, Integer> map) {
        // O(1) as 26 entries in map at max
        int max = -1;
        for (Map.Entry<Character, Integer> entry:  map.entrySet()) {
            max = Math.max(max, entry.getValue());
        }
        return max;
    }
}
