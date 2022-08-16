// https://leetcode.com/problems/design-add-and-search-words-data-structure/

class WordDictionary {
    private class TrieNode {
        char ch;
        TrieNode[] nodes;
        boolean isEnd;
        
        private TrieNode(char ch) {
            this.ch = ch;
            this.nodes = new TrieNode[26];
        }
    }
    
    private final TrieNode root;
    
    public WordDictionary() {
        root = new TrieNode('\0');
    }
    
    public void addWord(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            if (current.nodes[word.charAt(i) - 'a'] == null) {
                current.nodes[word.charAt(i) - 'a'] = new TrieNode(word.charAt(i));
            }
            current = current.nodes[word.charAt(i) - 'a'];
        }
        current.isEnd = true;
    }
    
    public boolean search(String word) {
        if (word == null) {
            return false;
        }
        return search(word.toCharArray(), 0, root);
    }
    
    private boolean search(char[] ch, int start, TrieNode current) {
        boolean isWildCardMatch = false;
        boolean didWildCardMatch = false;
        
        if (start >= ch.length && current != null) {
           return current.isEnd;
        }
        for (int i = start; i < ch.length; i++) {
            if (current == null) {
                break;
            } else if (ch[i] != '.') {
                current = current.nodes[ch[i] - 'a'];
            } else { // wildcard, return deep recursion result and break from this for loop
                isWildCardMatch = true;
                for (int j = 0; j < 26; j++) {
                    if (current.nodes[j] != null) {
                        didWildCardMatch = search(ch, i + 1, current.nodes[j]);
                        if (didWildCardMatch) {
                            break;
                        }
                    }
                }
                break;
            }
        }
        return isWildCardMatch ? didWildCardMatch : (current != null && current.isEnd);
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
