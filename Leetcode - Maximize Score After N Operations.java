// https://leetcode.com/problems/maximize-score-after-n-operations/
class Pair {
    int left;
    int right;
    
    public Pair(int left, int right) {
        this.left = left;
        this.right = right;
    }
    
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Pair)) {
            return false;
        }
        Pair otherPair = (Pair) other;
        return this.left == otherPair.left && this.right == otherPair.right;
    }
    
    @Override
    public int hashCode() {
        return this.left * 31 + this.right;
    }
}

class Solution {
    public int maxScore(int[] nums) {
        Map<Integer, Integer> memo = new HashMap<>();
        Map<Pair, Integer> gcd = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++)  {
                int result = gcd(nums[i], nums[j]);
                gcd.put(new Pair(i, j), result);
            }
        }
        return recur(1, nums, 0, memo, gcd);
    }
    
    
    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
    
    private int recur(int index, int[] nums, int bitMask, Map<Integer, Integer> memo, Map<Pair, Integer> gcd) {
        if (index > (nums.length / 2)) {
            return 0;
        }
        
        if (memo.containsKey(bitMask)) {
            return memo.get(bitMask);
        }
        
        int bestScore = 0;
        for (int i = 0; i < nums.length; i++) {
            if (!isSet(bitMask, i)) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (!isSet(bitMask, j)) {
                        bitMask = setBit(bitMask, i);
                        bitMask = setBit(bitMask, j);
                        
                        int currentScore = index * gcd.get(new Pair(i, j));
                        bestScore = Math.max(bestScore, currentScore + recur(index + 1, nums, bitMask, memo, gcd));

                        bitMask = unsetBit(bitMask, i);
                        bitMask = unsetBit(bitMask, j);
                    }
                }
            }
        }
        
        memo.put(bitMask, bestScore);
        return bestScore;
    }
    
    private boolean isSet(int num, int position) {
        int mask = (1 << position);
        return (num & mask) != 0;
    }
    
    private int setBit(int num, int position) {
        int mask = (1 << position);
        return num | mask;
    }
    
    private int unsetBit(int num, int position) {
        int negatedMask = ~(1 << position);
        return num & negatedMask;
    }
}
