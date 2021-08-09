// https://leetcode.com/explore/challenge/card/august-leetcoding-challenge-2021/614/week-2-august-8th-august-14th/3875/

// Using Library:
import java.math.BigInteger;

class Solution {
    public String addStrings(String num1, String num2) {
        BigInteger b1 = new BigInteger(num1);
        BigInteger b2 = new BigInteger(num2);
        b1 = b1.add(b2);
        return b1.toString();
    }
}

// Own implementation:
class Solution {
    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder("");
        int carry = 0;
        
        if (num2.length() > num1.length()) {
            String temp = num1;
            num1 = num2;
            num2 = temp;
        }
        int i = num1.length() - 1, j = num2.length() - 1;
        while (j >= 0) {
            int current = (num2.charAt(j) - '0') + (num1.charAt(i) - '0') + carry;
            if (current > 9) {
                sb.append(current % 10);
                carry = current / 10;
            } else {
                sb.append(current);
                carry = 0;
            }
            i--;
            j--;
        }

        while (i >= 0) {
            int current = (num1.charAt(i) - '0') + carry;
            if (current > 9) {
                sb.append(current % 10);
                carry = current / 10;
            } else {
                sb.append(current);
                carry = 0;
            }
            i--;
        }
        
        if (carry > 0) {
            sb.append(carry);
        }
        
        sb = sb.reverse();
        return sb.toString();
    }
}
