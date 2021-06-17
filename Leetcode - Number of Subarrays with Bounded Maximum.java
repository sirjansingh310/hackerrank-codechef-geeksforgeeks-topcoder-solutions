// https://leetcode.com/explore/challenge/card/june-leetcoding-challenge-2021/605/week-3-june-15th-june-21st/3782/
class Solution {
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        // create boundaries, and within each boundary, if there are n elements, n * (n + 1) / 2
        // subarrays can be formed
        
        /*
        [2, 1, 1, 1, 3, 4, 3]
        left = 2, right = 3
        
        4 is where we break the chain. It is impossible to add 4 since it is more than right
        1 is less than left, but it can be added in array like [2, 1] [2, 1, 1] [1, 3]..
        As the question says, each subarray should have the max element in range of left and right
        
        So we count 1 in our current size. But the final number of subarrays between 2 and 4(not including 4) will contain .. [1], [1,1], [1,1,1] etc which are invalid
        So to subtract those, we maintain continuousBadLower which tells how many continuous elements 
        were bad and to be subtracted. so we had 3 one's together, we remove (3 * (3 + 1) / 2) subarrays
        
        */
        int currentSize = 0, result = 0, continuousBadLower = 0;
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > right) {
                result += (currentSize * (currentSize + 1) / 2);
                result -= (continuousBadLower * (continuousBadLower + 1) / 2);
                continuousBadLower = 0;
                currentSize = 0;
            } else if (nums[i] < left) {
                currentSize++;
                continuousBadLower++;
            } else {
                currentSize++;
                result -= (continuousBadLower * (continuousBadLower + 1) / 2);
                continuousBadLower = 0;
            }
        }
        
        result += (currentSize * (currentSize + 1) / 2);
        result -= (continuousBadLower * (continuousBadLower + 1) / 2);
        
        return result;
    }
}
