//https://leetcode.com/problems/trapping-rain-water/
class Solution {
    public int trap(int[] height) {
        int water = 0;
        int[] maxTillNowLeft = new int[height.length]; // tells which number is max from index 0... i
        int[] maxTillNowRight = new int[height.length];// tells which number is max from index n-1..i
        int[] wall = new int[height.length]; // min of both arrays at i index. tells which wall will block flow for element at i. That we will consider for calculation
        
        maxTillNowLeft[0] = 0;
        maxTillNowRight[height.length - 1] = 0;
        int max = height[0];
        
        for (int i = 1; i < height.length; i++) {
            maxTillNowLeft[i] = max;
            max = Math.max(height[i], max);
        }
        
        max = height[height.length - 1];
        for (int i = height.length - 2; i >= 0; i--) {
            maxTillNowRight[i] = max;
            max = Math.max(height[i], max);
        }
        
        for (int i = 0; i < height.length; i++) {
            wall[i] = Math.min(maxTillNowLeft[i], maxTillNowRight[i]);
        }
              
        for (int i = 0; i < height.length; i++) {
            if (wall[i] > height[i]) {
                water += wall[i] - height[i];
            }
        }
        
        return water;
    }
}
