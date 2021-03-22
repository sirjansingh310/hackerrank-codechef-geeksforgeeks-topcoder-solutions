//https://leetcode.com/explore/challenge/card/march-leetcoding-challenge-2021/591/week-4-march-22nd-march-28th/3681/
class Solution {
    public String[] spellchecker(String[] wordlist, String[] queries) {
        Set<String> exactMatchSet = new HashSet<>();
        Map<String, String> capitalizationMatchMap = new HashMap<>();
        Map<String, String> vowelErrorMatchMap = new HashMap<>();
        // pre process
        for (String word : wordlist) {
            exactMatchSet.add(word);
            String lowerCase = word.toLowerCase();
            // as we need first match, we use putIfAbsent()
            capitalizationMatchMap.putIfAbsent(lowerCase, word);
            String vowelRemoved = removeVowel(lowerCase);
            vowelErrorMatchMap.putIfAbsent(vowelRemoved, word);
        }
        
        String[] result = new String[queries.length];
        
        for (int i = 0; i < queries.length; i++) {
            String lowerCase = queries[i].toLowerCase();
            String vowelRemoved = removeVowel(lowerCase);
            if (exactMatchSet.contains(queries[i])) {
                result[i] = queries[i];
            } else if (capitalizationMatchMap.containsKey(lowerCase)) {
                result[i] = capitalizationMatchMap.get(lowerCase);
            } else if (vowelErrorMatchMap.containsKey(vowelRemoved)) {
                result[i] = vowelErrorMatchMap.get(vowelRemoved);
            } else {
                result[i] = "";
            }
        }
        
        return result;
    }
    
    private String removeVowel(String word) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            sb.append(isVowel(word.charAt(i)) ? '*' : word.charAt(i));
        }
        return sb.toString();
    }
    
    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
