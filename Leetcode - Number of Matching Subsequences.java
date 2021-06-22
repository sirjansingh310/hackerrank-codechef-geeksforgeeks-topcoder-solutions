// https://leetcode.com/explore/challenge/card/june-leetcoding-challenge-2021/606/week-4-june-22nd-june-28th/3788/
class Solution {    
    private boolean isSubsequence(Map<Character, List<Integer>> positions, String word) {
        int[] charOffset = new int[26];
        int prev = -1;
        // all positions of characters in word should be in order (and should exist) wrt the word to check against
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (positions.containsKey(c) && positions.get(c).size() > charOffset[c - 'a']
               && positions.get(c).get(charOffset[c - 'a']) > prev) {
                prev = positions.get(c).get(charOffset[c - 'a']);
                charOffset[c - 'a']++;
            } else if (positions.containsKey(c) && positions.get(c).size() > charOffset[c - 'a']) {
                // get offset just bigger than prev
                boolean found = false;
                List<Integer> list = positions.get(c);
                for (int j = 0; j < list.size(); j++) {
                    if (list.get(j) > prev) {
                        prev = list.get(j);
                        charOffset[c - 'a'] = j;
                        found = true;
                        break;
                    }
                }
                
                if (!found) {
                    return false;
                }
            } else {
                return false;
            }
        }
        
        return true;
    }
    
    public int numMatchingSubseq(String s, String[] words) {
        Map<Character, List<Integer>> positions = new HashMap<>();
        
        for (int i = 0; i < s.length(); i++) {
            if (positions.containsKey(s.charAt(i))) {
                positions.get(s.charAt(i)).add(i);
            } else {
                positions.put(s.charAt(i), new ArrayList<Integer>(Arrays.asList(i)));
            }
        }
        int count = 0;
        for (String word : words) {
            if (isSubsequence(positions, word)) {
                count++;
            }
        }
        
        return count;
    }
}
