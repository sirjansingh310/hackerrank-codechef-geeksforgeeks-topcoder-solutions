class Solution {
    private Set<Integer> alreadySeen = new HashSet<>();
    private int findLargestSubseq(int num, final Set<Integer> numsSet) {
        if (!numsSet.contains(num)) {
            return 0;
        }
        
        alreadySeen.add(num);
        
        int length = 1;
        
        if (!alreadySeen.contains(num + 1)) {
            length += findLargestSubseq(num + 1, numsSet);
        }
        
        if (!alreadySeen.contains(num - 1)) {
            length += findLargestSubseq(num - 1, numsSet);
        }
        
        return length;
    }
    
    public int longestConsecutive(int[] nums) {
        Set<Integer> numsSet = new HashSet<>();
        
        for (int i : nums) {
            numsSet.add(i);
        }
        
        int maxLength = 0;
        
        for (int i : nums) {
            if (!alreadySeen.contains(i)){
                maxLength = Math.max(maxLength, findLargestSubseq(i, numsSet));
            }
            
        }
        
        
        return maxLength;
    }
}
