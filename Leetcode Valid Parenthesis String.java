// https://leetcode.com/problems/valid-parenthesis-string/description/?envType=daily-question&envId=2024-04-01
class Solution {
    public boolean checkValidString(String s) {
        // * can be '(' or ')' or ''
        Stack<Integer> openBracketStack = new Stack<>(); // storing indexes
        Stack<Integer> wildCardStack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                openBracketStack.push(i);
            } else if (s.charAt(i) == '*') {
                wildCardStack.push(i);
            } else {
                if (!openBracketStack.isEmpty()) {
                    openBracketStack.pop();
                } else if (!wildCardStack.isEmpty()) {
                    wildCardStack.pop();
                } else {
                    return false;
                }
            }
        }

        // see if openbracketstack can be emptied with * considerd as )
        while (!openBracketStack.isEmpty() && !wildCardStack.isEmpty()) {
            if (openBracketStack.peek() > wildCardStack.peek()) {
                return false;
            }
            openBracketStack.pop();
            wildCardStack.pop();
        }

        return openBracketStack.isEmpty(); // see if open brackets are balanced. if wildcard stack is not empty, those wildcards are empty strings
    }
}
