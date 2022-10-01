// https://leetcode.com/problems/multiply-strings/ 
// no internal functions used, and no direct conversion of string to int for the given input

class Solution {
    public String multiply(String num1, String num2) {
        if (num1.length() < num2.length()) {
            String t = num1;
            num1 = num2;
            num2 = t;
        }
        
        int carry = 0;
        List<String> additions = new ArrayList<>();
        
        for (int i = num2.length() - 1; i >= 0; i--) {
            additions.add(multiply(num1, num2.charAt(i), additions.size()));
        }
        
        return add(additions);
    }
    
    private String multiply(String number, char character, int size) {
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        while (size-- > 0) {
            sb.append("0");
        }
        
        for (int i = number.length() - 1; i >= 0; i--) {
            int mul = (character - '0') * (number.charAt(i) - '0') + carry;
            if (mul < 10) {
                carry = 0;
                sb.append(mul);
            } else {
                sb.append(mul % 10);
                mul /= 10;
                carry = mul;
            }
        }
        
        if (carry > 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }
    
    
    private String add(List<String> numbers) {
        int carry = 0; 
        String sum = numbers.get(0);
        
        for (int i = 1; i < numbers.size(); i++) {
            String current = numbers.get(i);
            
            while (current.length() < sum.length()) {
                current = "0" + current;
            }
            
            while (sum.length() < current.length()) {
                sum = "0" + sum;
            }
            
            StringBuilder sb = new StringBuilder();
            for (int j = sum.length() - 1; j >= 0; j--) {
                int s = (current.charAt(j) - '0') + (sum.charAt(j) - '0') + carry;
                if (s < 10) {
                    carry = 0;
                    sb.append(s);
                } else {
                    sb.append(s % 10);
                    s /= 10;
                    carry = s;
                }
            }
            if (carry > 0) {
                sb.append(carry);
                carry = 0;
            }
            sum = sb.reverse().toString();
        }
        
        if (allZeros(sum)) {
            return "0";
        }
        return sum;
    }
    
    private boolean allZeros(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '0') {
                return false;
            }
        }
        return true;
    }
}
