// https://leetcode.com/explore/challenge/card/march-leetcoding-challenge-2021/590/week-3-march-15th-march-21st/3676/\
// My first O(N^2) Approach

class Solution {
    private int getCurrentWiggleLength(int index, int[] nums, boolean greaterMode) {
        int currentLength = 1;
         for (int j = index; j < nums.length; j++) {
                if (nums[j] > nums[j - 1] && greaterMode) {
                    currentLength++;
                    greaterMode = !greaterMode;
                } else if (nums[j] < nums[j - 1] && !greaterMode) {
                    currentLength++;
                    greaterMode = !greaterMode;
                }
            }
        return currentLength;
    }
    public int wiggleMaxLength(int[] nums) {
        int bestLength = 1;
        
        for (int i = 0; i < nums.length; i++) {
            bestLength = Math.max(bestLength, getCurrentWiggleLength(i + 1, nums, true));
            bestLength = Math.max(bestLength, getCurrentWiggleLength(i + 1, nums, false));
        }
        return bestLength;
    }
}


// Seeing we are doing same work again and again for inner loop till end, storing results from backwards to this point using DP
//O(N)
class Solution {
    public int wiggleMaxLength(int[] nums) {
        int dp[][] = new int[nums.length][2];
        
        dp[nums.length - 1][0] = 1;
        dp[nums.length - 1][1] = 1;
        
        for (int i = dp.length - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1]) {
                dp[i][0] = dp[i + 1][1] + 1;
            } 
            if (nums[i] < nums[i + 1]) {
                dp[i][1] = dp[i + 1][0] + 1;
            }
            dp[i][0] = Math.max(dp[i][0], dp[i + 1][0]);
            dp[i][1] = Math.max(dp[i][1], dp[i + 1][1]);
        }

        return Math.max(dp[0][0], dp[0][1]);
    }
}
