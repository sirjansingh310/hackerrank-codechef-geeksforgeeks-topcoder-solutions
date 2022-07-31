class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        // since we want the list of numbers, we can use the optimized two sum where numbers are sorted
        
        Arrays.sort(nums);
        Set<List<Integer>> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            int left = i + 1, right = nums.length - 1;
            // two sum sorted list
            while (left < right) {
                int currentSum = nums[i] + nums[left] + nums[right];
                if (currentSum > 0) {
                    right--;
                } else if (currentSum < 0) {
                    left++;
                } else {
                   set.add(Arrays.asList(nums[i], nums[left], nums[right]));
                   left++;// keep going for other pairs
                   right--;
                }
            }
        }
        
        return new ArrayList<>(set);
    }
}

// without sorting, slower
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+ 1; j < nums.length; j++) {
                if (i != j) {
                    int sum = nums[i] + nums[j];
                    if (map.containsKey(- 1 * sum) && map.get(-1 * sum) != i && map.get(-1 * sum) != j) {
                        List<Integer> list = Arrays.asList(nums[i], nums[j], -1 * sum);
                        Collections.sort(list);
                        result.add(list);
                    }
                }
            }
        }
        
        return new ArrayList<>(result);
    }
}
