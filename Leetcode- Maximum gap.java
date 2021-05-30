//idea: from bucket sort, but we are not sorting or storing items in bucket, just using max and min elements from each bucket to find the gap
//https://leetcode.com/explore/challenge/card/may-leetcoding-challenge-2021/602/week-5-may-29th-may-31st/3761/
// tc: O(N), sc: O(N)
class Solution {
    public int maximumGap(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }
        
        int min = nums[0];
        int max = nums[0];
        
        for (int i : nums) {
            min = Math.min(min, i);
            max = Math.max(max, i);
        }
        
        int numberOfBuckets = nums.length - 1;
        int bucketSize = (int)Math.ceil((double)(max - min) / numberOfBuckets);
        
        int[] minOfBucket = new int[numberOfBuckets];
        int[] maxOfBucket = new int[numberOfBuckets];
        
        Arrays.fill(minOfBucket, Integer.MAX_VALUE);
        Arrays.fill(maxOfBucket, Integer.MIN_VALUE);
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == min || nums[i] == max) {
                continue;
            }
            int currentBucketIndex = (nums[i] - min) / bucketSize;
            
            minOfBucket[currentBucketIndex] = Math.min(minOfBucket[currentBucketIndex], nums[i]);
            maxOfBucket[currentBucketIndex] = Math.max(maxOfBucket[currentBucketIndex], nums[i]);
            
        }
        // System.out.println(Arrays.toString(minOfBucket));
        // System.out.println(Arrays.toString(maxOfBucket));
        
        // for buckets A and B, min of B - max of A will give the gap, we need to maximize it
        int maxGap = 0;
        
        for (int i = 0; i < numberOfBuckets; i++) {
            if (maxOfBucket[i] == Integer.MIN_VALUE) {// empty bucket, not updated/used
                continue;
            }
            maxGap = Math.max(maxGap, minOfBucket[i] - min);
            min = maxOfBucket[i]; // updating min for next iteration
        }
        maxGap = Math.max(maxGap, max - min);// last bucket
        return maxGap;
    }
}
