//https://leetcode.com/problems/longest-consecutive-sequence/
// brute force - Sorting O(NlogN)
// O(N) solution using HashSet
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
