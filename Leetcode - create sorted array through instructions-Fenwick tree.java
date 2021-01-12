//https://leetcode.com/explore/challenge/card/january-leetcoding-challenge-2021/580/week-2-january-8th-january-14th/3599/

class Fenwick {
    private int tree[]; // tree storing frequency of a number in nums as and when it is inserted into
    // nums from instructions array
    
    Fenwick(int maxNumber) {
        tree = new int[maxNumber + 1];
    }
    
    public void updateFrequency(int index) {
        while (index < tree.length) {
            tree[index] += 1;
            index = getNext(index);
        }
    }
    
    public int getRangeSum(int start, int end) {
        return getPrefixSum(end) - getPrefixSum(start - 1);
    }
    
    private int getPrefixSum(int index) {
        // index++. need not do as instructions[i] itself is the 1 based index value for the fenwick tree. As the tree is a frequency array
        int sum = 0;        
        while (index > 0) {
            sum += tree[index];
            index = getParent(index);
        }
        
        return sum;
    }
    
    private int getParent(int index) {
        return index - (index & -index);
    }
    
    private int getNext(int index) {
        return index + (index & -index);
    }
}
class Solution {
    public int createSortedArray(int[] instructions) {
        if (instructions.length == 0) {
            return 0;
        }
        
        int MOD = 1000000007;
        
        int maxNumber = instructions[0];
        for (int i : instructions) {
            maxNumber = Math.max(i, maxNumber);
        }
        
        Fenwick fenwickTree = new Fenwick(maxNumber);

        int totalCost = 0;
        for (int i = 0; i < instructions.length; i++) {
            int numberOfStrictlyLessThan = fenwickTree.getRangeSum(0, instructions[i] - 1);
            int numberOfStrictlyGreaterThan = fenwickTree.getRangeSum(instructions[i] + 1, maxNumber);
            int min = Math.min(numberOfStrictlyLessThan, numberOfStrictlyGreaterThan);
            totalCost = ((totalCost % MOD) + (min % MOD )) % MOD;
            fenwickTree.updateFrequency(instructions[i]);
        }
        
        return totalCost;
    }
}
