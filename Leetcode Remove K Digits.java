// https://leetcode.com/problems/remove-k-digits/description
class Solution {
    public String removeKdigits(String num, int k) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < num.length(); i++) {
            if (k == 0) {
                stack.push(num.charAt(i));
                continue;
            }
            while (!stack.isEmpty() && k > 0 && num.charAt(i) < stack.peek()) {
                k--;
                stack.pop();
            }
            stack.push(num.charAt(i));
        }

        
        
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            if (k > 0) {
                k--;
                stack.pop();
                continue;
            }
            sb.append(stack.pop());
        }
        
        String result = sb.reverse().toString();
        int leadingZeroEnd = 0, i = 0;
        while (i < result.length() && result.charAt(i) == '0') {
            leadingZeroEnd++;
            i++;
        }

        result = result.substring(leadingZeroEnd, result.length());

        if (result.equals("")) {
            return "0";
        }

        return result;
    }
}
