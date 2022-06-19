// https://leetcode.com/problems/search-suggestions-system/
// Very nice problem! Solution using Trie DS. 
// Problem -> given list of products, and a search term, return list of search results on each key stroke of search term.
// On a single key stroke, we can have at most 3 results. 
// Solution -> Sort products. Insert them into trie. 
// For each key stroke, we have a search term. Go to node which matches this search term in the trie. We might be at the product or not in the trie(indicated
by isEndForAWord flag). Both scenarios will be handled by dfs itself. 
// From this trie node, do dfs in the trie. Wheneven we encounter a word match, insert in list. Limit list to 3 as base case of dfs. 
class Node {
    Node[] next;
    char current;
    boolean isEndForAWord;
    
    public Node(char current) {
        this.current = current;
        this.next = new Node[26];
        this.isEndForAWord = false;
    }
    
    public Node insert(char c) {
        int index = c - 'a';
        if (this.next[index] == null) {
            this.next[index] = new Node(c);
        }
        return this.next[index];
    }
    
    public Node getNext(int index) {
        return this.next[index];
    }
    
    public Node getFirst() {
        for (int i = 0; i < 26; i++) {
            if (this.next[i] != null) {
                return this.next[i];
            }
        }
        return null;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Currently at ").append(this.current).append(' ');
        
        for (Node node : this.next) {
            if (node != null) {
                sb.append(node);
            }
        }
        
        sb.append('\n');
        return sb.toString();
    }
}
class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        final Node trie = new Node(' ');
        Arrays.sort(products);
        
        for (String product : products) {
            insertInTrie(trie, product);
        }
        
        List<List<String>> results = new ArrayList<>();
        String currentSearch = "";
        for (char c : searchWord.toCharArray()) {
            List<String> result = new ArrayList<>();
            Node currentSearchNode = getNodeForSearchTerm(trie, currentSearch + c); // go to the node of trie with current search term
            dfs(result, currentSearchNode, currentSearch + c);// dfs the 3 products from this node.
            currentSearch = currentSearch + c;
            results.add(result);
        }
        
        return results;
    }
    
    private void insertInTrie(final Node trie, String product) {
        Node current = trie;
        
        for (int i = 0; i < product.length(); i++) {
            current = current.insert(product.charAt(i));
        }
        current.isEndForAWord = true;
    }
    
    private void dfs(List<String> list, Node node, String currentTerm) {
        if (node == null || list.size() == 3) {
            return;
        }
        
        if (node.isEndForAWord) {
            list.add(currentTerm);
        }
        
        for (int i = 0; i < 26; i++) {
            Node next = node.getNext(i);
            if (next != null) {
                dfs(list, next, currentTerm + next.current);
            }
        }
    }
    
    private Node getNodeForSearchTerm(final Node trie, String term) {
        Node current = trie;
        for (int i = 0; i < term.length(); i++) {
            current = current.getNext(term.charAt(i) - 'a');
            if (current == null) {
                return null;
            }
        }
        return current;
    }
}
