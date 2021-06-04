// https://leetcode.com/explore/challenge/card/june-leetcoding-challenge-2021/603/week-1-june-1st-june-7th/3767/
class Solution {
    public int openLock(String[] deadends, String target) {
        // bfs from 0000 -> target, to get minimum number of moves
        
        String source = "0000";
        Set<String> deadEndsSet = Arrays.stream(deadends)
            .collect(Collectors.toSet());
        
        if (deadEndsSet.contains(source)) {
            return -1;
        }
        
        Queue<String> queue = new LinkedList<>();
        queue.add(source);
        Map<String, Integer> distanceFromSource = new HashMap<>();
        Set<String> visited = new HashSet<>();
        distanceFromSource.put(source, 0);
        visited.add(source);
        
        while (!queue.isEmpty()) {
            String current = queue.poll();
            int distance = distanceFromSource.get(current);
            
            List<String> children = getUnvisitedChildren(current, visited);
            
            for (String child : children) {
                if (!deadEndsSet.contains(child)) {
                    visited.add(child);
                    queue.add(child);
                    distanceFromSource.put(child, distance + 1);
                } 
            }
        }
        
        return distanceFromSource.getOrDefault(target, -1);
    }
    
    private List<String> getUnvisitedChildren(String node, Set<String> visited) {
        final List<String> result = new ArrayList<>();
        
        for (int i = 0; i < node.length(); i++) {
            char[] ch = node.toCharArray();
            int digit = ch[i] - '0';
            createChild(result, visited, ch, digit + 1, i);
            createChild(result, visited, ch, digit - 1, i);   
        }
    
        return result;
    }
    
    private void createChild(List<String> result, Set<String> visited, char[] ch, int newDigit, int index) {
        ch[index] = (char)(Math.floorMod(newDigit, 10) + '0');
        String child = new String(ch);
        if (!visited.contains(child)) {
            result.add(child);
        }
    }
}
