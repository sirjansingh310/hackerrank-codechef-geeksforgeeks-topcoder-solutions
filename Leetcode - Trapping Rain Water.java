//https://leetcode.com/problems/trapping-rain-water/
class Solution {
    public int trap(int[] height) {
        int water = 0;
        Stack<Integer> stack = new Stack<>();
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
            if (stack.isEmpty()) {
                stack.push(i);
            } else if (height[i] < height[stack.peek()]) { // continue inserting in monotinic stack. 
                // this statement means stack peek element can still contribute as water can spill over to right. 
                stack.push(i);
            } else { // the new height barrier is more that stack top element.
                // Meaning contribution of stack top element is over since water may not(possible to flow if height[i] is not the max from right) flow to right.
                // this barrier may stop water to go spill over right. So we have to calculate its contribution.
                // We already found which wall we need to consider above, so we use it. 
                while (!stack.isEmpty() && height[i] >= height[stack.peek()]) {
                    int elementIndex = stack.pop();
                    if (wall[elementIndex] > height[elementIndex]) {
                        water += (wall[elementIndex] - height[elementIndex]);
                    }
                    
                }
                stack.push(i);
            }
            
        }
        
        return water;
    }
}
