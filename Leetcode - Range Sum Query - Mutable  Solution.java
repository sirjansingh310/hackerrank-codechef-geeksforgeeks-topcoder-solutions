// https://leetcode.com/explore/challenge/card/june-leetcoding-challenge-2021/605/week-3-june-15th-june-21st/3783/
class NumArray {
    private int[] bitTree;
    private int[] nums;
    private boolean constructed;
    public NumArray(int[] nums) {
        this.nums = nums;
        this.bitTree = new int[nums.length + 1];
        // O(NlogN)
        for (int i = 0; i < nums.length; i++) {
            update(i, nums[i]);
        }
        this.constructed = true;
    }
    
    // does nums[index] = val and not nums[index] += val
    // O(logN)
    public void update(int index, int val) {
        if (constructed) {
            int copy = val;
            val = val - nums[index]; // update only the diff
            nums[index] = copy;
        }
        index = getBitIndex(index);
        while (index < bitTree.length) {
            bitTree[index] += val;
            index = getNext(index);
        }
        
    }
  
    // O(logN)
    public int sumRange(int left, int right) {
        return getPrefixSum(right) - getPrefixSum(left - 1);
    }
    
    
    private int getPrefixSum(int index) {
        index = getBitIndex(index);
        // sum from index till 0
        int sum = 0;
        while (index > 0) { // 0 index is dummy node
            sum += bitTree[index];
            index = getParent(index);
        }
        return sum;
    }
    
    private int getBitIndex(int index) {
        return index + 1;// index in BIT is one more than nums. Parent root is dummy
    }
    
    private int getNext(int index) {
        return index + (index & -index);
    }
    
    private int getParent(int index) {
        return index - (index & -index);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */
