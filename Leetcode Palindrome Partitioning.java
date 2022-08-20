// https://leetcode.com/problems/palindrome-partitioning/
class Solution {
    public List<List<String>> partition(String s) {
        List<List<Integer>> palindromSubStringIndexes = new ArrayList<>();
        
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                if (isPalindrome(s.substring(i, j))) {
                    palindromSubStringIndexes.add(Arrays.asList(i, j - 1)); // j is excluded
                }
            }
        }
        
        List<List<String>> partitions = new ArrayList<>();
        recur(palindromSubStringIndexes, new LinkedList<>(), 0, partitions, s);
        return partitions;
    }
    
    private void recur(List<List<Integer>> palindromeIndexes, LinkedList<List<Integer>> list, int index, List<List<String>> resultList, String s) {
        
        if (index >= palindromeIndexes.size()) {
            String buildString = buildString(list, s);
            if (buildString.equals(s)) {
                List<String> toAdd = list.stream().map(p -> s.substring(p.get(0), p.get(1) + 1)).collect(Collectors.toList());
                resultList.add(toAdd);
            }
            return;
        }
        
        // skip current slice
        recur(palindromeIndexes, list, index + 1, resultList, s);
        
        if (list.size() == 0 || (list.get(list.size() - 1).get(1) + 1 == palindromeIndexes.get(index).get(0))) { // see if it can connect to chain
            list.add(palindromeIndexes.get(index));
            recur(palindromeIndexes, list, index + 1, resultList, s);
            list.removeLast();
        }
        return;
    }
    
    private boolean isPalindrome(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }
    
    private String buildString(LinkedList<List<Integer>> list, String s) {
        String r = "";
        for (List<Integer> l : list) {
            r += (s.substring(l.get(0), l.get(1) + 1));
        }
        return r;
    }
}
