// https://leetcode.com/problems/array-nesting/
// TC: O(N). Since we count / add in set a element at most once
// Recursive
class Solution {
    private boolean[] visited;
    private int getSetSize(int[] nums, int index, Set<Integer> set) {
        if (set.contains(nums[index])) {
            return set.size();
        }
        
        set.add(nums[index]);
        visited[index] = true;
        return getSetSize(nums, nums[index], set);
    }
    
    public int arrayNesting(int[] nums) {
        int longestChain = 0;
        visited = new boolean[nums.length];
         // since we are interested
        // in maximum length, visiting again with nums[i] as source meaning we will always get shorter length
        // than previous computed set where nums[i] was in between
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                longestChain = Math.max(longestChain, getSetSize(nums, i, new HashSet<>()));
            }
        }
        return longestChain;
    }
}
// Iterative 
class Solution {
    public int arrayNesting(int[] nums) {
        int longestChain = 0;
        boolean[] visited = new boolean[nums.length]; // since we are interested
        // in maximum length, visiting again with nums[i] as source meaning we will always get shorter length
        // than previous computed set where nums[i] was in between
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                Set<Integer> set = new HashSet<>();
                int k = i;
                while (true) {
                    if (set.contains(nums[k])) {
                        break;
                    }
                    set.add(nums[k]);
                    visited[k] = true;
                    k = nums[k];
                }
                longestChain = Math.max(longestChain, set.size());
            }
        }
        return longestChain;
    }
}
