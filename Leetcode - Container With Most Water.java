class Solution {
    public int maxArea(int[] height) {
        // different than largest area of histogram.
        // example: 6, 2, 5, 4, 5, 1, 6
        // expected ans here = 36, largest histogram ans = 12
        int left = 0, right = height.length - 1;
        int area = 0;
        while (left <= right) {
            int currentArea = Math.min(height[left], height[right]) * (right - left);
            area = Math.max(area, currentArea);
            if (height[left] < height[right]) { // left can do better, lets increment it
                left++;
            } else {
                right--;
            }
        }
        
        return area;
    }
}
