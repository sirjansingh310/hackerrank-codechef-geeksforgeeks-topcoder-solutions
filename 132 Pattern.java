// https://leetcode.com/problems/132-pattern/
class Solution {
    public boolean find132pattern(int[] nums) {
        // we are going to use a monotonic stack (stack which is inc or dec)
        // stack will have smaller at top and bottom will be bigger in this case
        // the stack will be used to determine the 32 in the 132 pattern, and we will
        // check if the current third element from the 32 (i.e 2 in pattern) is smaller than
        // nums[i]. if yes, we got 132
        
        Stack<Integer> stack = new Stack<>();
        int third = Integer.MIN_VALUE;
        
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] < third) {
                return true;
            }
            
            // why are we doing this?
            // consider the example 35024 the ans is 3 5 4 which is a 132 pattern
            // we insert [0, 2, 4] in stack, when it comes to 5, which is bigger than stack
            // elements, we want to minimize the difference between the "3" and "2" element, the biggest
            // will be the last just smaller than 5. In that case, we established 4 as 
            // the third element and 5 as the current 2nd element pushed to stack. The 32 pattern is
            // taken care of in the monotonic stack. 
            // when we find a number just below the third element, that is 3 which is < 4, we have 
            // formed a 132 pattern
            while (!stack.isEmpty() && nums[i] > stack.peek()) {
                third = stack.pop();
            }
            stack.push(nums[i]);
        }
        
        return false;
    }
}
