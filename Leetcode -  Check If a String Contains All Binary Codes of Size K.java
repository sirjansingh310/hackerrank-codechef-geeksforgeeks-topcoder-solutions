//https://leetcode.com/explore/challenge/card/march-leetcoding-challenge-2021/589/week-2-march-8th-march-14th/3669/
class Solution {
    public boolean hasAllCodes(String s, int k) {
        if (s.length() < k) {
            return false;
        }
        Set<String> binaryRepresentations = new HashSet<>();
        for (int i = 0; i <= s.length() - k; i++) {
            binaryRepresentations.add(s.substring(i, i + k));
        }
        // for k, exactly 2 ^ k binary numbers are present
        return binaryRepresentations.size() == (int)Math.pow(2, k);
    }
}
