// https://leetcode.com/explore/challenge/card/july-leetcoding-challenge-2021/610/week-3-july-15th-july-21st/3821/
class Solution {
    public String pushDominoes(String dominoes) {
        int[] left = new int[dominoes.length()]; // time to be thrown be a left dominoe
        int[] right = new int[dominoes.length()];
        
        Arrays.fill(left, Integer.MAX_VALUE);
        Arrays.fill(right, Integer.MAX_VALUE);
        
        int count = 0;
        for (int i = 0; i < dominoes.length(); i++) {
            if (dominoes.charAt(i) == '.' && count > 0) {
                right[i] = count++;
            } else if (dominoes.charAt(i) == 'R') {
                count = 1;
            } else {
                count = 0;
            }
        }
        
        for (int i = dominoes.length() - 1; i >= 0; i--) {
            if (dominoes.charAt(i) == '.' && count > 0) {
                left[i] = count++;
            } else if (dominoes.charAt(i) == 'L') {
                count = 1;
            } else {
                count = 0;
            }
        }
        
        
        char[] finalState = dominoes.toCharArray();
        for (int i = 0; i < dominoes.length(); i++) {
            if (dominoes.charAt(i) == '.') {
                if (left[i] < right[i]) {
                    finalState[i] = 'L';
                } else if (right[i] < left[i]) {
                    finalState[i] = 'R';
                }
            }
        }
        
        return new String(finalState);
    }
}
