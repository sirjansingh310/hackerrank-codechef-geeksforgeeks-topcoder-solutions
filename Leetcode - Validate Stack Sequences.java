//https://leetcode.com/explore/challenge/card/february-leetcoding-challenge-2021/587/week-4-february-22nd-february-28th/3653/
class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int pushIndex = 0, popIndex = 0;
        
        while (pushIndex < pushed.length && popIndex < popped.length) {
            try {
                stack.push(pushed[pushIndex++]);
                while (!stack.isEmpty() && stack.peek() == popped[popIndex]) {
                    stack.pop();
                    popIndex++;
                }
            } catch (Exception e) {
                System.out.println("exception" + e);
                return false;
            }
        }
        
        while (!stack.isEmpty()) {
            if (stack.peek() == popped[popIndex]) {
                stack.pop();
                popIndex++;
            } else {
                return false;
            }
        }
        return true;
    }
}
