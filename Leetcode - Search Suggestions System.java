// https://leetcode.com/explore/challenge/card/may-leetcoding-challenge-2021/602/week-5-may-29th-may-31st/3762/
class TrieNode {
    char ch;
    TrieNode[] children;
    boolean isEndOfWord;
    
    List<String> matchedWordsFromHere; // maxiumum size of 3, more memory but faster results. Acts as fast cache
    
    TrieNode() {
        children = new TrieNode[26];
        matchedWordsFromHere = new ArrayList<>();
    }
    
    TrieNode(char ch) {
        this();
        this.ch = ch;
    }
    
    public String toString() {
        return "char = " + ch + '\n'
            + "matched words = " + matchedWordsFromHere + '\n';
    }
    
}

class Solution {
    
    private void insert(TrieNode root, String word) {
        TrieNode newNodeTraverse = root;
        
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int charToIndex = ch - 'a';
            if (newNodeTraverse.children[charToIndex] == null) {
                newNodeTraverse.children[charToIndex] = new TrieNode(ch);
            }
            
            newNodeTraverse = newNodeTraverse.children[charToIndex];
            
            if (newNodeTraverse.matchedWordsFromHere.size() < 3) {
                newNodeTraverse.matchedWordsFromHere.add(word);
            }
        }
        
        newNodeTraverse.isEndOfWord = true;
    }
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products);
        
        TrieNode root = new TrieNode();
        
        for (String word: products) {
            insert(root, word);
        }
        
        List<List<String>> result = new ArrayList<>();
        
        TrieNode searchTraverse = root;
        for (int i = 0; i < searchWord.length(); i++) {
            int charToIndex = searchWord.charAt(i) - 'a';
            if (searchTraverse != null && searchTraverse.children[charToIndex] != null) {
                result.add(searchTraverse.children[charToIndex].matchedWordsFromHere);
                searchTraverse = searchTraverse.children[charToIndex];
            } else {
                searchTraverse = null;
                result.add(new ArrayList<>());
            }
        }
        
        return result;
    }
}
