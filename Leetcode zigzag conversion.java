//https://leetcode.com/problems/zigzag-conversion
class Solution {
    public String convert(String s, int numRows) {
        if (s.length() <= numRows) {
            return s;
        }
        char ch[][] = new char[numRows][s.length()];
        for (char c[] : ch) {
            Arrays.fill(c, ' ');
        }
        
        int i = 0, j = 0, charPtr = 0;
        
        while (charPtr != s.length()) {
            while (i < numRows && charPtr != s.length()) {
                ch[i++][j] = s.charAt(charPtr++);
            }
            i = numRows - 2;
            j++;
            while (i >= 1 && charPtr != s.length()) {
                ch[i--][j++] = s.charAt(charPtr++);
            }
            i = 0;
        }
        String result = "";
        
        for (char arr[] : ch) {
            result += new String(arr).replace(" ", "");
        }
        
        return result;
    }
}
