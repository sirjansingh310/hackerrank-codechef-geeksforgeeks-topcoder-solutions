// O(N) Time, O(1) space!!
class Solution {
    public int firstMissingPositive(int[] nums) {
        // first missing will always be in range 1 to nums.length
        // we replace all negatives, numbers out of 1.. nums.length to zeros, as they are useless and not possible to be missing
        // Now array should have 0 or positive numbers. 
        // when we see a number, see the number as this being the index. if new number was positive just make it negative, if it was negative, let it be negative, if it is zero, make it Integer.MIN_VALUE, something which is out of range for sure and doesn't effect our processing.
        // this new number value being flipped meaning the orignal number is seen. as missing are in 1...n, we are using indexes smartly.
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 1 || nums[i] > nums.length) {
                nums[i] = 0;
            }
        }
                
        /// process only positives, negatives are just indicator that index value number is seen already
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0 || nums[i] == Integer.MIN_VALUE) {
                continue;
            }
            int originalNumber = Math.abs(nums[i]);
            if (nums[originalNumber -  1] == 0) {
                nums[originalNumber - 1] = Integer.MIN_VALUE; // something that is out of range for sure, to mark the orignal number as seen. First I made it as -1, but it caused a edge case as it was like 1 is seen.
            }
            if (nums[originalNumber - 1] > 0) {
                nums[originalNumber - 1] *= -1;
            }
        }
        int missingNumber = nums.length + 1;// assume no missing
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0) {// something not negative, meaning missing is found, i.e index is the ans
                missingNumber = i + 1;
                break;
            }
        }
        return missingNumber;
    }
}
