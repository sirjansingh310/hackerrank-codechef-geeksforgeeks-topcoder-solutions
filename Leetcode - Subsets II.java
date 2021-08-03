// https://leetcode.com/explore/challenge/card/august-leetcoding-challenge-2021/613/week-1-august-1st-august-7th/3837/
class Solution {
    private final Set<List<Integer>> subsets = new HashSet<>();
    
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums); // so [4,1] and [1,4] added to set are not different. now we will always get [1,4] so set will mark it duplicate when seen again
        createSubsets(new ArrayList<>(), nums, 0);
        return new ArrayList<>(subsets);
    }
    
    
    private void createSubsets(List<Integer> currentList, int[] nums, int index) {
        if (index == nums.length) {
            subsets.add(new ArrayList<>(currentList));
            return;
        }
        currentList.add(nums[index]);
        createSubsets(currentList, nums, index + 1);
        currentList.remove(currentList.size() - 1);
        createSubsets(currentList, nums, index + 1);
    }
}
