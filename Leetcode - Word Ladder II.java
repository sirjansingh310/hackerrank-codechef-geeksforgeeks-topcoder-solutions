// https://leetcode.com/explore/challenge/card/july-leetcoding-challenge-2021/611/week-4-july-22nd-july-28th/3825/
// find all shortest paths and print them
// bfs with little trick of allowing even if visited(if and only if it is a better solution than previous recorded solution)

class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();
        
        Queue<String> queue = new LinkedList<>();
        Queue<List<String>> listQueue = new LinkedList<>();
        Map<String, Integer> prevVisitDistance = new HashMap<>();
        Set<String> visited = new HashSet<>();

        queue.add(beginWord);
        listQueue.add(Arrays.asList(beginWord));
        visited.add(beginWord);
        prevVisitDistance.put(beginWord, 0);
        
        while (!queue.isEmpty()) {
            String current = queue.poll();
            List<String> listSoFar = listQueue.poll();
            int distSoFar = listSoFar.size();
            List<String> neighbors = getNeighbors(current, visited, wordList, distSoFar + 1, prevVisitDistance);
            for (String neighbor : neighbors) {
                if (neighbor.equals(endWord)) {
                    // check for shortest
                    List<String> finalList = new ArrayList<>(listSoFar);
                    finalList.add(endWord);
                    
                    if (result.isEmpty() || result.get(0).size() == finalList.size()) {
                        result.add(finalList);
                    } else if (result.get(0).size() > finalList.size()) {
                        result.clear();
                        result.add(finalList);
                    }
                    break;
                }
                visited.add(neighbor);
                queue.add(neighbor);
                List<String> newList = new ArrayList<>(listSoFar);
                newList.add(neighbor);
                listQueue.add(newList);
                prevVisitDistance.put(neighbor, distSoFar + 1);
            }
        }
        return result;
    }
    
    private List<String> getNeighbors(String givenWord, Set<String> visited, List<String> wordList, int distSoFar, Map<String, Integer> prevVisitDistance) {
        List<String> result = new ArrayList<>();
        for (String word : wordList) {
            if (isDiffOneChar(word, givenWord)) {
                if (!visited.contains(word)) {
                    result.add(word); 
                } else if (distSoFar <= prevVisitDistance.get(word)) { // give a chance, even if visited, because it is better solution
                    prevVisitDistance.put(word, distSoFar);
                    result.add(word);
                }
            }
        }
        return result;
    }
    
    private boolean isDiffOneChar(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }
        
        int mismatch = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                mismatch++;
            }
            if (mismatch > 1) {
                return false;
            }
        }
        
        return mismatch == 1;
    }
}
