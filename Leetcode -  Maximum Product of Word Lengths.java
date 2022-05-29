// https://leetcode.com/problems/maximum-product-of-word-lengths/
class Solution {
    public int maxProduct(String[] words) {
        // bitMasks[i] will be integer, which when converted to binary will have set bits where
        // character is present in the string. 
        // We calculate it and compare with already calculated bitmasks, if the binary and result 
        // is 0, that means they don't have anything in common, and can be used to calculate product
        int[] bitMasks = new int[words.length];
    
        int maxPro = 0;
        
        for (int i = 0; i < words.length; i++) {
            int bitMask = 0;
            
            for (char c : words[i].toCharArray()) {
                bitMask |= (1 << (c - 'a'));
            }
            bitMasks[i] = bitMask;
            // compare with already computed bitmasks
            for (int j = 0; j < i; j++) {
                if ((bitMasks[i] & bitMasks[j]) == 0) {
                    maxPro = Math.max(maxPro, words[i].length() * words[j].length());
                }
            }
        }
        
        return maxPro;
    }
}
