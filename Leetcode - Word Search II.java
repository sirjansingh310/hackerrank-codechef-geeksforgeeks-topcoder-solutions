// https://leetcode.com/problems/word-search-ii/
class Trie {
    private class TrieNode {
        char ch;
        TrieNode nodes[];
        boolean isEnd;
        
        private TrieNode(char ch) {
            this.ch = ch;
            this.nodes = new TrieNode[26];
        }
    }
    
    private final TrieNode root;
    
    public Trie() {
        root = new TrieNode('\0');
    }
    
    public void insert(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            int charIndex = word.charAt(i) - 'a';
            if (current.nodes[charIndex] == null) {
                current.nodes[charIndex] = new TrieNode(word.charAt(i));
            }
            current = current.nodes[charIndex];
        }
        current.isEnd = true;
    }
    
    public boolean isPrefix(String word) {
        TrieNode deepNode = search(word, root);
        return deepNode != null;
    }
    
    public boolean fullMatch(String word) {
        TrieNode deepNode = search(word, root);
        return deepNode != null && deepNode.isEnd;
    }
    
    private TrieNode search(String word, TrieNode root) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            int charIndex = word.charAt(i) - 'a';
            if (current.nodes[charIndex] == null) {
                return null;
            }
            current = current.nodes[charIndex];
        }
        return current;
    }
}


class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        // reverse thinking! instead of trying to create trie on board where we
        // can search a word, create trie on words itself. DFS on board and see if current result is leading to a word, else backtrack!
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }
        Set<String> result = new HashSet<>();
        
        boolean[][] visited = new boolean[board.length][board[0].length];// reusable as we backtrack
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // give all characters chance to start string from
                dfs(board, i, j, visited, trie, "", result);
            }
        }
        return new ArrayList<>(result);
    }
    
    private void dfs(char[][] board, int row, int col, boolean[][] visited, Trie trie, String currentString, Set<String> result) {
        if (row >= 0 && row < board.length && col >= 0 && col < board[0].length) {
            if (visited[row][col]) {
                return;
            }
            currentString += board[row][col];
            if (!trie.isPrefix(currentString)) { // way faster back tracking than visited locks
                return;
            }
            visited[row][col] = true;// lock so in current string processing, we don't visit this row-col again
            if (trie.fullMatch(currentString)) {
                result.add(currentString);// dont return, we might have another word with currentString as its prefix
            }
            int[] rowDir = {-1, 1, 0, 0};
            int[] colDir = {0, 0, 1, -1};
            
            for (int i = 0; i < 4; i++) {
                dfs(board, row + rowDir[i], col + colDir[i], visited, trie, currentString, result);
            }
            
            visited[row][col] = false; // backtrack, unlock after all deep recursion is done. 
        }
    }
}
