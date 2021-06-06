//https://leetcode.com/explore/featured/card/june-leetcoding-challenge-2021/603/week-1-june-1st-june-7th/3769/
class Solution {
    private Set<Integer> alreadySeen = new HashSet<>();
    private int findLargestSubseq(int num, final Map<Integer, Integer> freq) {
        if (!freq.containsKey(num)) {
            return 0;
        }
        
        alreadySeen.add(num);
        
        int length = 1;
        
        if (!alreadySeen.contains(num + 1)) {
            length += findLargestSubseq(num + 1, freq);
        }
        
        if (!alreadySeen.contains(num - 1)) {
            length += findLargestSubseq(num - 1, freq);
        }
        
        return length;
    }
    
    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        
        for (int i : nums) {
            freq.put(i, freq.getOrDefault(i, 0) + 1);
        }
        
        int maxLength = 0;
        
        for (int i : nums) {
            if (!alreadySeen.contains(i)){
                maxLength = Math.max(maxLength, findLargestSubseq(i, freq));
            }
            
        }
        
        
        return maxLength;
    }
}
