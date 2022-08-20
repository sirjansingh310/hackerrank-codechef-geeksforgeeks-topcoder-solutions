class Solution {
    private List<List<Integer>> result;
    public List<List<Integer>> permute(int[] nums) {
        result = new ArrayList<>();
        recur(nums, new ArrayList<>(), 0);
        return result;
    }
    
    private void recur(int[] nums, List<Integer> list, int start) {
        if (start == nums.length) {
            List<Integer> permutation = new ArrayList<>();
            for (int i : nums) {
                permutation.add(i);
            }
            result.add(permutation);
            return;
        }
        
        for (int i = start; i < nums.length; i++) {
            swap(nums, start, i); // get first num in index i to get that num in all positions and recur
            
            recur(nums, list, start + 1);
            swap(nums, i, start); // backtrack
        }
    }
    
    private void swap(int[] nums, int x, int y) {
        int t = nums[x];
        nums[x] = nums[y];
        nums[y] = t;
    }
}


// sol 2
class Solution {
    private Set<List<Integer>> result;
    public List<List<Integer>> permute(int[] nums) {
        result = new HashSet<>();
        recur(nums, new LinkedHashSet<>());
        return new ArrayList<>(result);
    }
    
    private void recur(int[] nums, Set<Integer> linkedHashSet) {
        if (linkedHashSet.size() == nums.length) {
            result.add(new ArrayList<>(linkedHashSet));
            return;
        }
        for (int i : nums) {
            if (!linkedHashSet.contains(i)) {
                linkedHashSet.add(i);
                recur(nums, linkedHashSet);
                linkedHashSet.remove(i);
            }
        }
    }
}
