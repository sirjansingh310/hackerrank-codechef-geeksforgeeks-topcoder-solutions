// https://leetcode.com/explore/challenge/card/january-leetcoding-challenge-2021/580/week-2-january-8th-january-14th/3598/
class Solution {
    private boolean isEdge(String parent, String child) {
       // check char by char left to right if one or no diff found 
        if (parent.length() != child.length()) {
            return false;
        }
        int diffCount = 0;
        for (int i = 0; i < parent.length(); i++) {
            if (parent.charAt(i) != child.charAt(i)) {
                diffCount++;
            }
        }
        
        return diffCount < 2;
    }
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        
        // do bfs 
        Map<String, Integer> distanceFromSource = new HashMap<>();
        distanceFromSource.put(beginWord, 1);
        
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
    
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        
        while (queue.size() > 0) {
            String current = queue.poll();
            int distance = distanceFromSource.get(current);
            
            for (String word : wordList) {
                if (!visited.contains(word) && isEdge(current, word)) {
                    distanceFromSource.put(word, distance + 1);
                    queue.add(word);
                    visited.add(word);
                }
            }
        }
        // System.out.println(distanceFromSource);
        return distanceFromSource.getOrDefault(endWord, 0);
    }
}
