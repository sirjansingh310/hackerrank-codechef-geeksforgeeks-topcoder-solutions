// https://leetcode.com/explore/challenge/card/january-leetcoding-challenge-2021/581/week-3-january-15th-january-21st/3611/
class Solution {
    public int[] mostCompetitive(int[] nums, int k) {
        Stack<Integer> stack = new Stack<>();
        int numberOfPopsAllowed = nums.length - k;
        
        for (int i : nums) {
            while (!stack.isEmpty() && i < stack.peek() && numberOfPopsAllowed > 0) {
                stack.pop();
                numberOfPopsAllowed--;
            }
            stack.push(i);
        }
        
        int result[] = new int[k];
        while(stack.size() > k) {
            stack.pop();
        }
        for (int i = k - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }
        
        return result;
    }
}
