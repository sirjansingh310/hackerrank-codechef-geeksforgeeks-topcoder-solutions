class Solution {
    private String countAndSay(String s, int n) {
        if (n == 0) {
            return s;
        }
        if (s.equals("")) {
            return countAndSay("1", n - 1);
        } else {
            StringBuilder sb = new StringBuilder("");
            char current = s.charAt(0);
            int count = 1;
            
            for (int i = 1; i < s.length(); i++) {
                if (s.charAt(i) == current) {
                    count++;
                } else {
                    sb.append(Integer.toString(count));
                    sb.append(current);
                    count = 1;
                    current = s.charAt(i);
                }
            }
            
            sb.append(Integer.toString(count));
            sb.append(current);
            
            return countAndSay(sb.toString(), n - 1);
        }
    }
    
    public String countAndSay(int n) {
        return countAndSay("", n);
    }
}
