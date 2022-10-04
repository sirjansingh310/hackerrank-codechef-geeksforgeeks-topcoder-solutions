// https://leetcode.com/problems/partition-equal-subset-sum/
class Solution {
    private int[][] memo = new int[201][20001];
    
    public boolean canPartition(int[] nums) {
        for (int i = 0; i < memo.length; i++) {
            Arrays.fill(memo[i], -1);
        }
        int totalSum = 0;
        for (int i = 0; i < nums.length; i++) {
            totalSum += nums[i];
        }
        if (totalSum % 2 != 0) {
            return false;
        }
        
        // now we know we can divide sum into two since it is even, check only for one partition if we get sum 0, then it is obv we have another partition with sum 0.
        // we send sum as total/2 because that is what we want
        
        return canPartition(nums, 0, totalSum / 2);
    }
    
    private boolean canPartition(int[] nums, int index, int sum) {
        if (index == nums.length) {
            return sum == 0;
        } else if (sum < 0 || index > nums.length) {
            return false;
        }
        
        if (memo[index][sum] != -1) {
            return memo[index][sum] == 1;
        }
        
        boolean withAddToPartition = canPartition(nums, index + 1, sum - nums[index]);
        boolean withoutAddToPartition = canPartition(nums, index + 1, sum);
        
        
        boolean result = withAddToPartition || withoutAddToPartition;
        if (result) {
            memo[index][sum] = 1; 
        } else {
            memo[index][sum] = 0;
        }
        return result;
    }
}
