// bottom up coin change solution like i did for Combination Sum 1 timed out, so tried naive brute force recursion with memoization to optimize and get AC.
class Solution {
    private Map<Integer, Integer> map = new HashMap<>();
    
    private int recur(int[] nums, int target) {
        if (target == 0) {
            return 1;
        }
        
        if (target < 0) {
            return 0;
        }
        
        if (map.containsKey(target)) {
            return map.get(target);
        }
        
        int result = 0;
        
        // add coins from begin, so we get solutions like 1,1,1,1 1,2,1, 2,1,1 ... in all permutations for a target like 4
        for (int i = 0; i < nums.length; i++) {
            if (target - nums[i] >= 0) {
                result += recur(nums, target - nums[i]);
            } else {
                break;
            }
        }
        
        map.put(target, result);
        
        return result;
    }
    
    public int combinationSum4(int[] nums, int target) {
        Arrays.sort(nums);
        return recur(nums, target);
    }
}
