//https://leetcode.com/problems/restore-ip-addresses/
class Solution {
    private Set<String> ipSet = new HashSet<>();
    private void recur(String prefix, int index, char[] ch, int count) {
        if (count >= 5) {
            return;
        }
        if (count == 4 && isValidIp(prefix, ch.length)) {
            ipSet.add(prefix.substring(0, prefix.length() - 1));
        }
        
        StringBuilder sb = new StringBuilder("");
        if (index < ch.length && isValidNumber(sb.append(ch[index]).toString())) {
            recur(prefix + sb.append('.').toString(), index + 1, ch, count + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (index + 1 < ch.length && isValidNumber(sb.append(ch[index + 1]).toString())) {
            recur(prefix + sb.append('.').toString(), index + 2, ch, count + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (index + 2 < ch.length && isValidNumber(sb.append(ch[index + 2]).toString())) {
            recur(prefix + sb.append('.').toString(), index + 3, ch, count + 1); 
            sb.deleteCharAt(sb.length() - 1);
        }
        return;
    }    
    private boolean isValidNumber(String s) {
        int intValue = Integer.parseInt(s);
        if (s.length() > 1 && s.charAt(0) == '0') { // leading zeros
            return false;
        }
        return intValue >= 0 && intValue <= 255;
    }
    private boolean isValidIp(String ip, int originalInputLength) {
        return ip.length() == originalInputLength + 4;
    }
    public List<String> restoreIpAddresses(String s) {
        char[] ch = s.toCharArray();
        recur("", 0, ch, 0);
        return new ArrayList<String>(ipSet);
    }
}
