https://leetcode.com/explore/challenge/card/march-leetcoding-challenge-2021/591/week-4-march-22nd-march-28th/3685/
class Solution {
    public List<String> wordSubsets(String[] A, String[] B) {
        Map<String, Map<Character, Integer>> charFreqMapA = new HashMap<>();
        
        for (String a : A) {
            charFreqMapA.put(a, new HashMap<>());
            char[] ch = a.toCharArray();
            Map<Character, Integer> map = charFreqMapA.get(a);
            for (char c : ch) {
                map.put(c, map.getOrDefault(c, 0) + 1);
            }
        }
        
        Map<Character, Integer> allCharsInBMap = new HashMap<>();// for each char a-z, this map stores freq of a letter = max freq of that letter among all strings in B. if array B has one string with 5 "o", and that is max number of "o"s in B, then all strings b in B with "o"s in it will satisfy a string a in A if it has more than or equal to 5 "o"s
        
        for (char c = 'a'; c <= 'z'; c++) {
            allCharsInBMap.put(c, 0);
        }
        for (String b : B) {
            char[] ch = b.toCharArray();
            Map<Character, Integer> currentFreqMap = new HashMap<>();
            for (char c : ch) {
                currentFreqMap.put(c, currentFreqMap.getOrDefault(c, 0) + 1);
            }
            
            for (Map.Entry<Character, Integer> entry : currentFreqMap.entrySet()) {
                allCharsInBMap.put(entry.getKey(), Math.max(entry.getValue(), allCharsInBMap.get(entry.getKey())));
            }
        }
        
        int expectedMatchCount = 0;
        for (Map.Entry<Character, Integer> entry : allCharsInBMap.entrySet()) {
            if (entry.getValue() > 0) {
                expectedMatchCount++;
            }
        }
        
        List<String> result = new ArrayList<>();
        for (String a : A) {
            Map<Character, Integer> map = charFreqMapA.get(a);
            int count = 0;
            for (Map.Entry<Character, Integer> entry : allCharsInBMap.entrySet()) {
                if (entry.getValue() > 0) { // present in some string b of B and is max freq amoung all B
                    if (entry.getValue() <= map.getOrDefault(entry.getKey(), -1)) {
                        count++;
                    } else {
                        break;
                    }
                    
                }
            }
            if (count == expectedMatchCount) {
                result.add(a);
            }
        }
        
        return result;
    }
}


// a simpler version of same logic, but reduced all b in B to a single word containing max freq of all 'a' to 'z' in all b of B


class Solution {
   public List<String> wordSubsets(String[] A, String[] B) 
   {
        int[] t = new int[26];
        for(String b : B) 
        {
            int[] tmp = new int[26];
            for(char c : b.toCharArray()) tmp[c - 'a']++;
            for(int i = 0; i < 26; i++) t[i] = Math.max(t[i], tmp[i]);
        }
        
        List<String> res = new ArrayList<>();  
        for(String a : A) if(isUniversal(a, t)) res.add(a);
        return res;
    }
    
    boolean isUniversal(String s, int[] t) 
    {
        int[] tmp = new int[26];
        for(char c : s.toCharArray()) tmp[c - 'a']++;
        for(int i = 0; i < 26; i++) if(tmp[i] < t[i]) return false;
        return true;
    }
}
