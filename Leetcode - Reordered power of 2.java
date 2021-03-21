//https://leetcode.com/explore/challenge/card/march-leetcoding-challenge-2021/590/week-3-march-15th-march-21st/3679/
class Solution {
    private String sortNum(long num) {
        String s = Long.toString(num);
        char[] ch = s.toCharArray();
        Arrays.sort(ch);
        return new String(ch);
    }
    
    public boolean reorderedPowerOf2(int N) {
        String sortedNum = sortNum(N);
        
        long current = 1L;
        
        while (true) {
            String sortedCurrent = sortNum(current);
            if (sortedCurrent.length() > sortedNum.length()) {
                return false;
            }
            
            if (sortedCurrent.equals(sortedNum)) {
                return true;
            }
            current *= 2;
        }
        // return false;
    }
}
