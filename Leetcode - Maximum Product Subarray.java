// https://leetcode.com/problems/maximum-product-subarray/
// Simple dp, using precomputed produts, same as prefix sum
// divide into chunks of not containing zero, precompute their products.
// if final product > 0, return it, otherwise it means we need to skip one negative number to get a positive product
// iterate and choose max using our precomputed products array, product left of skipped number vs product on right of skipped number
class Solution {
    public int maxProduct(int[] nums) {
        List<int[]> chunks = new ArrayList<>();
        int start = 0;
        int maxPro = Integer.MIN_VALUE;
        
        while (start < nums.length && nums[start] == 0) {
            start++;
        }
        
        if (start == nums.length) {
            return 0;
        }
        
        for (int i = start; i < nums.length; i++) {
            if (i > 0 && nums[i] == 0) {
                chunks.add(new int[]{start, i - 1});
                start = i + 1;
            }
            maxPro = Math.max(maxPro, nums[i]);
        }
        
        if (start < nums.length) {
           chunks.add(new int[]{start, nums.length - 1}); 
        }
        
        for (int[] ch : chunks) {
            maxPro = Math.max(maxPro, getMaxPro(nums, ch[0], ch[1]));
        }
        return maxPro;
    }
    
    private int getMaxPro(int[] nums, int start, int end) {
        int[] products = new int[nums.length];
        int currentProduct = 1;
        int maxProduct = Integer.MIN_VALUE;
        
        for (int i = start; i <= end; i++) {
            products[i] = currentProduct * nums[i];
            currentProduct = products[i];
        }
        
        if (products[end] > 0) {
            return products[end];
        } else { // get positve result by ignoring one negative number
            for (int i = start; i <= end; i++) {
                if (nums[i] < 0 && i > start) {
                    maxProduct = Math.max(maxProduct, products[i - 1]);
                }
                if (nums[i] < 0 && i < end) {
                    maxProduct = Math.max(maxProduct, products[end] / products[i]);
                }
            }
            return maxProduct;
        }
    }
}


// SOLUTION 2, Iterate from forward and backward both, so if we miss something, it is covered by the other

class Solution {
    public int maxProduct(int[] nums) {
        int forwardProduct = 1, backwardProduct = 1, maxProduct = Integer.MIN_VALUE;
        
        for (int i = 0; i < nums.length; i++) {
            forwardProduct *= nums[i];
            maxProduct = Math.max(maxProduct, forwardProduct);
            
            if (forwardProduct == 0) {
                forwardProduct = 1;
            }
            
            backwardProduct *= nums[nums.length - i - 1];
            maxProduct = Math.max(maxProduct, backwardProduct);
            if (backwardProduct == 0) {
                backwardProduct = 1;
            }
        }
        
        return maxProduct;
    }
}
