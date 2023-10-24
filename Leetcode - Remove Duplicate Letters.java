// https://leetcode.com/problems/remove-duplicate-letters
class Solution {
    public String removeDuplicateLetters(String s) {
        int[] lastIndex = new int[26];
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }
        boolean[] insideStack = new boolean[26];
        Stack<Character> stack = new Stack<>(); // monotonic stack for lexographic order

        for (int i = 0; i < s.length(); i++) {
            if (insideStack[s.charAt(i) - 'a']) {
                continue;
            }
            while (!stack.isEmpty() && stack.peek() > s.charAt(i) 
            && i < lastIndex[stack.peek() - 'a']) { // i < lastIndex[stack.peek() - 'a'] is very important to make sure the one we popped is getting a chance again to be in the final string
                char popped = stack.pop();
                insideStack[popped - 'a'] = false;
            }
            stack.push(s.charAt(i));
            insideStack[s.charAt(i) - 'a'] = true;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.reverse().toString();
    }
}
