//https://leetcode.com/explore/featured/card/april-leetcoding-challenge-2021/595/week-3-april-15th-april-21st/3710/
class Node{
    char ch;
    int count;
    Node(char x , int y){
        ch=x;
        count=y;
    }
}

class Solution {
    public String removeDuplicates(String s, int k) {
        Stack<Node>stack = new Stack<>();
        
        for (int i=0;i<s.length();i++) {
            char c = s.charAt(i);
            if (stack.isEmpty() || stack.peek().ch!=c) {
                stack.push(new Node(c, 1));
            } else {
                stack.push(new Node(c, stack.peek().count+1));
                if (stack.peek().count == k) {
                    int temp = k;
                    while (temp-- > 0) {
                        stack.pop();
                    }
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop().ch);
        }
        return sb.reverse().toString();
    }
}
