class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int ans = 0, right = 0;
        
        while (right < heights.length) {
            if (stack.isEmpty() || heights[right] >= heights[stack.peek()]) {
                stack.push(right);
                right++;
            } else {
                while (!stack.isEmpty() && heights[right] <= heights[stack.peek()]) {
                    int currentMinimum = heights[stack.peek()];
                    stack.pop();
                    int elementsWithMinimum = stack.isEmpty() ? right : right - stack.peek() - 1;
                    ans = Math.max(ans, currentMinimum * elementsWithMinimum);
                }
                
            }
        }
        
        while (!stack.isEmpty()) {
            int currentMinimum = heights[stack.peek()];
            stack.pop();
            int elementsWithMinimum = stack.isEmpty() ? right : right - stack.peek() - 1;
            ans = Math.max(ans, currentMinimum * elementsWithMinimum);
        }
       
        return ans;
    }
}
