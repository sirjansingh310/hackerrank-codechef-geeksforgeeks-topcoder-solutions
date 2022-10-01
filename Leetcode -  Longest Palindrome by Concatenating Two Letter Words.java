//  Longest Palindrome by Concatenating Two Letter Words
// https://leetcode.com/problems/longest-palindrome-by-concatenating-two-letter-words
class Solution {
    public int longestPalindrome(String[] words) {
        int count = 0;
        Map<String, Integer> map = new HashMap<>();
        
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        
        boolean midPartTaken = false;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1 && !midPartTaken && entry.getKey().charAt(0) == entry.getKey().charAt(1)) {
                midPartTaken = true;
                count += 2;
            } else if (entry.getValue() == 1 && entry.getKey().charAt(0) == entry.getKey().charAt(1)) {
                continue;
            } else {
                String opp = "" + entry.getKey().charAt(1) + entry.getKey().charAt(0);
                if (map.containsKey(opp)) {
                    if (opp.equals(entry.getKey())) {
                        count += entry.getValue() * 2; // since opp and key are same
                    } else {
                        count += Math.min(entry.getValue(), map.get(opp)) * 4; // value if freq, meaning 2 chars, we have opp of it so total 4
                    }
                    
                    
                    if (opp.equals(entry.getKey())) {
                        int val = entry.getValue();
                        if (!midPartTaken && (val % 2) != 0 && val > 1) {
                            midPartTaken = true;
                        } else if (midPartTaken && (val % 2) != 0 && val > 1) {
                            count -= 2;
                        }
                    }
                    map.put(entry.getKey(), 0);
                    map.put(opp, 0);
                }
            }
        }
        
        return count;
    }
}
