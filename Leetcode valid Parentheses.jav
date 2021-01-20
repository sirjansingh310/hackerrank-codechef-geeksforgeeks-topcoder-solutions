//// https://leetcode.com/explore/challenge/card/january-leetcoding-challenge-2021/581/week-3-january-15th-january-21st/3610

class Solution {
    private boolean isValid(char c, char topOfStack) {
        if (c == ')') {
            return topOfStack == '(';
        }
        if (c == ']') {
            return topOfStack == '[';
        }
        if (c == '}') {
            return topOfStack == '{';
        }
        return false;
    }
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        
        try {
            char ch[] = s.toCharArray();
            for (char c : ch) {
                if (c == '(' || c == '[' || c == '{') {
                    stack.push(c);
                } else if (isValid(c, stack.peek())) { // closing bracket seen, time to remove
                    stack.pop();
                } else { // invalid case
                    return false;
                }
            }
        } catch (Exception e) {
            return false;
        }
        
        return stack.isEmpty();
    }
}
