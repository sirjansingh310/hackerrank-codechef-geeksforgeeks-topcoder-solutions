// https://leetcode.com/explore/challenge/card/november-leetcoding-challenge/567/week-4-november-22nd-november-28th/3542/
class Solution {
    private Map<String, Integer> precedence;
    
    Solution() {
        precedence = new HashMap<>();
        precedence.put("/", 2);
        precedence.put("*", 2);
        precedence.put("+", 1);
        precedence.put("-", 1);
    }
    
    
    private boolean isOperator(char c) {
        return (c == '/' || c == '*' || c == '-' || c == '+');
    }

    private List<String> convertToPostfix(List<String> tokens) {
        Stack<String> stack = new Stack<>();
        List<String> result = new ArrayList<>();
        
        for(String token : tokens) {
            if(!isOperator(token.charAt(0))) { // number
                result.add(token);
            } else if(token.equals(")")) {
                while(!stack.peek().equals(")")) {
                    result.add(stack.pop());
                }
                stack.pop();
            } else {
                while(stack.size() > 0 && precedence.get(token) <= precedence.get(stack.peek())) {
                    result.add(stack.pop());
                }
                stack.push(token);
            }
        }
        while(stack.size() > 0) {
            result.add(stack.pop());
        }
        return result;
    }
    
    public int evalPostfix(List<String> exp) {
        if(exp.size() == 1) {
            return Integer.parseInt(exp.get(0));
        }
        Stack<Integer> stack = new Stack();
        
        for(String token: exp) {
            if(!isOperator(token.charAt(0))){
                stack.push(Integer.parseInt(token));
            } else {
                int first = stack.pop();
                int second = stack.pop();
                switch (token) {
                    case "/":
                        stack.push(second / first);
                        break;
                    case "*":
                        stack.push(second * first);
                        break;
                    case "+":
                        stack.push(second + first);
                        break;
                    case "-":
                        stack.push(second - first);
                        break;
                }
            }
        }
        return stack.pop();
    }
    
    private List<String> getTokens(String s) {
        List<String> tokens = new ArrayList<>();
        s = s.replaceAll("\\s", "");
        String token = "" + s.charAt(0);
        
        for(int i = 1; i < s.length(); i++) {
            if(isOperator(s.charAt(i))) {
                tokens.add(token);
                tokens.add(s.charAt(i) + "");
                token = "";
            } else {
                token += (s.charAt(i) + "");
            }
        }
        tokens.add(token);
        return tokens;
    }
    public int calculate(String s) {
    // s can be any string containing,[+, -, *, / , " ", digit]
        List<String> postfix = convertToPostfix(getTokens(s));
        return evalPostfix(postfix);
    }
}
