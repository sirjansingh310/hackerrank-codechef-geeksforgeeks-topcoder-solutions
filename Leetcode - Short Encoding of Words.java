// Trie Solution. Time complexity - O(sum of length of words) 
class Solution {
    private class TrieNode {
        int childrenCount;
        TrieNode[] children;
        
        TrieNode() {
            childrenCount = 0;
            children = new TrieNode[26];
        }
        
        TrieNode getChildForChar(char c) {
            if (children[c - 'a'] == null) {
                children[c - 'a'] = new TrieNode();
                childrenCount++;
            }
            return children[c - 'a'];
        }
    }
    public int minimumLengthEncoding(String[] words) {
        // No need to sort in any fashion, Trie DS used for Pattern matching
        Map<TrieNode, Integer> trieToWordIndex = new HashMap<>();
        TrieNode root = new TrieNode();
        
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            TrieNode temp = root;
            for (int j = word.length() - 1; j >= 0; j--) {
                temp = temp.getChildForChar(word.charAt(j));
            }
            trieToWordIndex.put(temp, i);
        }
        int resultLength = 0;
        
        for (Map.Entry<TrieNode, Integer> entry : trieToWordIndex.entrySet()) {
            if (entry.getKey().childrenCount == 0) { // trie entered last for current index has no children, means it is full word entered in trie, it's length + # will be considered for encoding. 
                // example "time" was entered in trie as e -> m -> i -> t, then "me" while entering, was already contained in the trie. For TrieNode "t" of time, children are 0, so it is deepest in trie, extract, the word from the hashmap, get its length.
                int index = trieToWordIndex.get(entry.getKey());
                resultLength +=( words[index].length() + 1);// for # as per question
            }
        }
        
        return resultLength;
     }
}


// Brute force - Time complexity - O(sum of length of words)^2
class Solution {
    private boolean alreadyProcessed(Set<String> processed, String word) {
        String wordWithHash = word + "#";
        for (String s : processed) {
            if (s.contains(wordWithHash)) {
                return true;
            }
        }
        return false;
    }
    public int minimumLengthEncoding(String[] words) {
        // sort so bigger words are first
        Arrays.sort(words, (s1, s2) -> s2.length() - s1.length());
        Set<String> processed = new HashSet<>();
        for (String word : words) {
            if (alreadyProcessed(processed, word)) {
                continue; // bigger word will contain current word + #
            } else {
                processed.add(word + "#");
            }
        }
        int length = 0;
        for (String word : processed) {
            length += word.length();
        }
        return length;
    }
}
