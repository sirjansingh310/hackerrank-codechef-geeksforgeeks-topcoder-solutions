// https://leetcode.com/explore/challenge/card/february-leetcoding-challenge-2021/586/week-3-february-15th-february-21st/3645/
class Solution {
    public String minRemoveToMakeValid(String s) {
        Stack<Integer> stack = new Stack<>();
        char[] ch = s.toCharArray();
        
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (c == ')' || c == '(') {
                if (c == '(') {
                    stack.push(i);
                    ch[i] = 'X'; // to be removed
                 } else {
                    if (!stack.isEmpty()) {
                        int index = stack.pop();
                        ch[index] = '('; // restore good opening bracket
                    } else {
                        ch[i] = 'X'; // mark this extra closing bracket as to be removed.
                    }
                }
            }
        }
        
        while (!stack.isEmpty()) {
            ch[stack.pop()] = 'X';
        }
        StringBuilder result = new StringBuilder();
        for (char c : ch) {
            if (c == 'X') {
                continue;
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
}
