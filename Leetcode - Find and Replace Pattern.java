//https://leetcode.com/explore/challenge/card/may-leetcoding-challenge-2021/600/week-3-may-15th-may-21st/3750/
class Solution {
    private boolean match(String word1, String word2) {
        Map<Character, Character> oneToOneMapping = new HashMap<>();
        
        if (word1.length() == word2.length()) {
            for (int i = 0; i < word1.length(); i++) {
                if (oneToOneMapping.containsKey(word1.charAt(i)) && oneToOneMapping.get(word1.charAt(i)) != word2.charAt(i)) {
                    return false;
                }
                oneToOneMapping.putIfAbsent(word1.charAt(i), word2.charAt(i));
            }
            return true;
        }
        return false;
    }
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> qualifiedWords = new ArrayList<>();
        for (String word : words) {
            if (match(word, pattern) && match(pattern, word)) {
                qualifiedWords.add(word);
            }
        }
        return qualifiedWords;
    }
}
