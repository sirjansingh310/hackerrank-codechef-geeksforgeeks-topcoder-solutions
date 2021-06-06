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


// my second solution
class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        
        for (int i : nums) {
            numSet.add(i);
        }
        int bestLength = 0;
        for (int num : numSet) {
            if (!numSet.contains(num - 1)) { // if it contains, it is already processed
                int currentLength = 1;
                int current = num;
                while (numSet.contains(current + 1)) {
                    current++;
                    currentLength++;
                }
                bestLength = Math.max(bestLength, currentLength);

            }
        }
        
        return bestLength;
    }
}
