// https://leetcode.com/problems/permutations-ii/
class Solution {
    private final Set<List<Integer>> set;
    
    public Solution() {
        set = new HashSet<>();
    }
    
    private void permutations(List<Integer> current, int[] nums, Set<Integer> seenIndexes) {
        if (current.size() == nums.length) {
            set.add(new ArrayList<>(current));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (seenIndexes.contains(i)) {
                continue;
            }
            current.add(nums[i]);
            seenIndexes.add(i);
            permutations(current, nums, seenIndexes);
            current.remove(current.size() - 1);// backtrack
            seenIndexes.remove(i);
        }
        return;
    }
    public List<List<Integer>> permuteUnique(int[] nums) {
        permutations(new ArrayList<>(), nums, new HashSet<>());
        return new ArrayList<>(set);
    }
}
