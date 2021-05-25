//https://leetcode.com/problems/evaluate-reverse-polish-notation/

//recursive:


class Solution {
    private boolean isOperator(String s) {
        if (s.length() == 1) {
            char c = s.charAt(0);
            return c == '*' || c == '/' || c == '+' || c == '-';
        }
        return false;
    }
    
    private int evaluate(Stack<String> stack) {
        if (stack.isEmpty()) {
            return 0;
        }
        
        String top = stack.pop();
        if (!isOperator(top)) {
            return Integer.parseInt(top);
        }
        
        int left = evaluate(stack);
        int right = evaluate(stack);

        switch (top) {
            case "+":
                return right + left;
            case "-":
                return right - left;
            case "*":
                return right * left;
            case "/":
                return right / left;
        }
        
        return 0;
    }
    
    public int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack();
        for (int i = 0; i < tokens.length; i++) {
            stack.push(tokens[i]);
        }
        return evaluate(stack);
    }
}


// Iterative
class Solution {
    private boolean isOperator(String s) {
        if (s.length() == 1) {
            char c = s.charAt(0);
            return c == '*' || c == '/' || c == '+' || c == '-';
        }
        return false;
    }
    
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack();
        int result = 0;
        
        for (int i = 0; i < tokens.length; i++) {
            if (!isOperator(tokens[i])) {
                stack.push(Integer.parseInt(tokens[i]));
                continue;
            }
            int left = stack.pop();
            int right = stack.pop();
            switch (tokens[i]) {
                case "+":
                    stack.push(right + left);
                    break;
                case "-":
                    stack.push(right - left);
                    break;
                case "*":
                    stack.push(right * left);
                    break;
                case "/":
                    stack.push(right / left);
                    break;
            }
        }
        return stack.peek();
    }
}
