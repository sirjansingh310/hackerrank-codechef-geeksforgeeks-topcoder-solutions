// https://leetcode.com/problems/find-duplicate-file-in-system/
class Solution {
    public List<List<String>> findDuplicate(String[] paths) {
        Map<String, Set<String>> map = new HashMap<>();
        
        for (String path : paths) {
            String[] tokens = path.split(" ");
            String directory = tokens[0];
            
            for (int i = 1; i < tokens.length; i++) {
                String[] file = tokens[i].split("\\(");
                map.computeIfAbsent(file[1], x -> new HashSet<>()).add(directory + "/" + file[0]);
            }
        }
        
        return map.values()
            .stream()
            .filter(s -> s.size() > 1)
            .map(set -> new ArrayList<>(set))
            .collect(Collectors.toList());
    }
}
