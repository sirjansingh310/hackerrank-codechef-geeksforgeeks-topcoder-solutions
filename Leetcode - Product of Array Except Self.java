class Solution {
    public int[] productExceptSelf(int[] nums) {
        // without division
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        int product = 1;
        
        for (int i = 0; i < n; i++) {
            product *= nums[i];
            left[i] = product;
        }
        
        product = 1;
        for (int i = n - 1; i >= 0; i--) {
            product *= nums[i];
            right[i] = product;
        }
        
        int[] result = new int[n];
        result[0] = right[1];
        result[n - 1] = left[n - 2];
        for (int i = 1; i < n - 1; i++) {
            result[i] = left[i - 1] * right[i + 1];
        }
        
        return result;
    }
}
class Solution {
    // With division, need to handle when nums[i] is zero.
    public int[] productExceptSelf(int[] nums) {
        int zeroCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeroCount++;
            }
        }
        int product = 1, productWithoutZero = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                productWithoutZero *= nums[i];
            }
            product *= nums[i];
        }
        
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0 && zeroCount == 1) {
                result[i] = productWithoutZero;
            } else if (nums[i] == 0) {
                result[i] = 0;
            } else {
                result[i] = product / nums[i];
            }
        }
        
        return result;
    }
}
