//https://leetcode.com/problems/maximum-sum-of-two-non-overlapping-subarrays/
class Solution {
    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        int n = nums.length;
        int[] prefixSum = new int[n];
        
        prefixSum[0] = nums[0];
        
        for (int i = 1; i < n; i++) {
            prefixSum[i] = nums[i] + prefixSum[i - 1];
        }
        
        int maxSum = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i + firstLen - 1 < n && j + secondLen - 1 < n && !isOverlap(i, i + firstLen - 1, j, j + secondLen - 1)) {
                    int firstSum = prefixSum[i + firstLen - 1] - prefixSum[i] + nums[i];
                    int secondSum = prefixSum[j + secondLen - 1] -prefixSum[j] + nums[j];
                    
                    maxSum = Math.max(maxSum, firstSum + secondSum);
                    
                }
            }
        }
        
        return maxSum;
    }
    
    
    private boolean isOverlap(int s1, int e1, int s2, int e2) {
        if (s1 < s2) {
            return e1 >= s2;
        } else if (s1 > s2) {
            return e2 >= s1;
        }
        return true;
    }
}
