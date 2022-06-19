// https://leetcode.com/problems/search-suggestions-system/
// Very nice problem! Solution using Trie DS. 
// Problem -> given list of products, and a search term, return list of search results on each key stroke of search term.
// On a single key stroke, we can have at most 3 results. 
// Solution -> Sort products. Insert them into trie. 
// For each key stroke, we have a search term. Go to node which matches this search term in the trie. We might be at the product or not in the trie(indicated
// by isEndForAWord flag). Both scenarios will be handled by dfs itself. 
// From this trie node, do dfs in the trie. Wheneven we encounter a word match, insert in list. Limit list to 3 as base case of dfs. 

// GO below for a more faster trie solution. 
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
        Node currentSearchNode = trie;
        for (char c : searchWord.toCharArray()) {
            currentSearchNode = currentSearchNode != null ? currentSearchNode.getNext(c - 'a') : null;
            List<String> result = new ArrayList<>();
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

}

// Optimization!! 
// As we are inserting nodes into trie, we know this product can be found in the current inserted node. 
// We store a list whose size can be at max three, known as matchedWordsFromHere. 
// While we are at currentSearchNode in the trie, we can directly add this list!! No need to perform dfs on this node

class Node {
    Node[] next;
    char current;
    boolean isEndForAWord;
    
    List<String> matchedWordsFromHere = new ArrayList<>(); // as we insert in trie, we add words to this list
    /// Next time we are at a node, we already know what words are possible to search from here. 
    
    public Node(char current) {
        this.current = current;
        this.next = new Node[26];
        this.isEndForAWord = false;
    }
    
    public Node insert(char c, String wordBeingProcessed) {
        int index = c - 'a';
        if (this.next[index] == null) {
            this.next[index] = new Node(c);
        } 
        if (matchedWordsFromHere.size() < 3) {
            matchedWordsFromHere.add(wordBeingProcessed);
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
        Node currentSearchNode = trie;
        
        for (char c : searchWord.toCharArray()) {
            currentSearchNode = currentSearchNode != null ? currentSearchNode.getNext(c - 'a') : null;
            List<String> result = new ArrayList<>();
            if (currentSearchNode != null) {
                result.addAll(currentSearchNode.matchedWordsFromHere);
            }
            currentSearch = currentSearch + c;
            results.add(result);
        }
        
        return results;
    }
    
    private void insertInTrie(final Node trie, String product) {
        Node current = trie;
        
        for (int i = 0; i < product.length(); i++) {
            current = current.insert(product.charAt(i), product);
        }
        current.isEndForAWord = true;
        current.matchedWordsFromHere.add(product);// as not called for last inserted trie node.
    }
    
//     private void dfs(List<String> list, Node node, String currentTerm) {
//         if (node == null || list.size() == 3) {
//             return;
//         }
        
//         if (node.isEndForAWord) {
//             list.add(currentTerm);
//         }
        
//         for (int i = 0; i < 26; i++) {
//             Node next = node.getNext(i);
//             if (next != null) {
//                 dfs(list, next, currentTerm + next.current);
//             }
//         }
//     }
}



