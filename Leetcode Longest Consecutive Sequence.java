class Solution {
    public int longestConsecutive(int[] nums) {
        // brilliant solution
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            set.add(i);
        }
        
        int result = 0;
        for (int i : set) {
            int head = i - 1;
            if (!set.contains(head)) { // for numbers which have head in set, they should not be considered for processing as they will be processed in the below loop by some other number.
                // This ensures it is O(N)
                
                int count = 0;
                while (set.contains(head + 1)) {
                    count++;
                    head++;
                }
                result = Math.max(count, result);
            }
        }
        
        return result;
    }
}
