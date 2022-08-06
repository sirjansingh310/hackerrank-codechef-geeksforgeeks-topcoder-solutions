// https://leetcode.com/problems/daily-temperatures/
// O(N) solution

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        // monotonic non-decreasing stack
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[temperatures.length];
        
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                ans[stack.peek()] = i - stack.peek();
                stack.pop();
            }
            stack.push(i);
        }
        
        return ans;
    }
}
