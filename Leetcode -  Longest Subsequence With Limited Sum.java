class Solution {
    public int[] answerQueries(int[] nums, int[] queries) {
        Arrays.sort(nums);
        int[] prefix = new int[nums.length];
        prefix[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            prefix[i] += prefix[i - 1] + nums[i];
        }

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = binarySearchClosest(prefix, queries[i]);
        }

        return ans;
    }


    private int binarySearchClosest(int[] prefix, int target) {
        int low = 0, high = prefix.length - 1;
        int result = 0;
        boolean match = false;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (prefix[mid] > target) {
                high = mid - 1;
            } else {
                match = true;
                result = mid;
                low = mid + 1;
            }
        }

        return match ? result + 1 : 0;
    }
}
