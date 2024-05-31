// https://leetcode.com/problems/single-number-iii/
class Solution {
    public int[] singleNumber(int[] nums) {
        int xor = 0; 
        
        for (int i : nums) {
            xor ^= i;
        }

        int result1 = 0, result2 = 0;
        // in xor, take any set bit, for one result that bit will be 0 and for other it will be 1

        int setBit = 1;
        while ((setBit & xor) == 0) {
            setBit <<= 1;
        }
        
        // grouping based on the set bit so xor in each group will give result1 and result2
        for (int i : nums) {
            if ((setBit & i) != 0) {
                result1 ^= i;
            } else {
                result2 ^= i;
            }
        }

        return new int[]{result1, result2};
    }
}
