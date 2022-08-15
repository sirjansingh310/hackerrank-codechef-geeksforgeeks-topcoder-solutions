// https://leetcode.com/problems/implement-trie-prefix-tree/

class TrieNode {
    char ch;
    TrieNode[] nodes;
    boolean isEndForWord;
    
    public TrieNode(char ch) {
        this.ch = ch;
        nodes = new TrieNode[26];
    }
}

class Trie {
    private final TrieNode root;
    
    public Trie() {
        root = new TrieNode('X');// dummy node, just acts as root
    }
    
    public void insert(String word) {
        char[] arr = word.toCharArray();
        TrieNode lastInsertionNode = root;
        
        for (int i = 0; i < arr.length; i++) {
            if (lastInsertionNode.nodes[arr[i] - 'a'] == null) {
                lastInsertionNode.nodes[arr[i] - 'a'] = new TrieNode(arr[i]);
            }
            lastInsertionNode = lastInsertionNode.nodes[arr[i] - 'a'];
        }
        lastInsertionNode.isEndForWord = true;
    }
    
    public boolean search(String word) {
        TrieNode node = getDeepNode(word);
        return node != null && node != root && node.isEndForWord;
    }
    
    public boolean startsWith(String prefix) {
        TrieNode node = getDeepNode(prefix);
        return node != null && node != root;
    }
    
    private TrieNode getDeepNode(String word) {
        TrieNode lastNode = root;
        char[] arr = word.toCharArray();
        
        for (int i = 0; i < arr.length; i++) {
            lastNode = lastNode.nodes[arr[i] - 'a'];
            if (lastNode == null) {
                return null;
            }
        }
        
        return lastNode;
    }
    
}


/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
