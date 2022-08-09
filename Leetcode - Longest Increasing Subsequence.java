// simple DP Approach. dp[i] represents length of subseq which ends at nums[i]
// Time Complexity: O(N^2)
class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        
        Arrays.fill(dp, 1);
        int best = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                best = Math.max(best, dp[i]);
            }
        }
        return best;
    }
}



// watch this video for intuition https://www.youtube.com/watch?v=on2hvxBXJH4&ab

/* Binary search approach
Build LIS intuitively 
Let lis be our Longest increase subseq
the first element of array is to be included
Now iterating from the second element, we do the following:
    if current > lis.get(lis.size() - 1)
        append current to lis
    else as lis is sorted, we perform binary search on it
    and find the first number just greater or equal to current
    replace that number with current
One thing to add: 
this algorithm does not always generate a valid subsequence of the input, 
but the length of the subsequence will always equal the length of the longest increasing subsequence.
For example, with the input [3, 4, 5, 1], at the end we will have sub = [1, 4, 5], 
which isn't a subsequence(correct is [3, 4, 5]. But the length is still correct.
The length remains correct because the length only changes when a new element is larger than any element in the subsequence.
In that case, the element is appended to the subsequence instead of replacing an existing element.

*/
class Solution {
    public int lengthOfLIS(int[] nums) {
        List<Integer> lis = new ArrayList<>();
        lis.add(nums[0]);
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > lis.get(lis.size() - 1)) {
                lis.add(nums[i]);
            } else {
                int index = binarySearch(lis, nums[i]); // find number >= to nums[i]
                lis.set(index, nums[i]);
            }
        }
        
        return lis.size();
    }
    
    
    private int binarySearch(List<Integer> list, int num) {
        int low = 0, high = list.size() - 1;
        
        while (low <= high) {
            int mid = (low + high) / 2;
            if (list.get(mid) == num) { // exact match
                return mid;
            }
            if (list.get(mid) < num) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        
        return low; // we never reached exact num. low was always increased with init value to 0. low will point to first number in list > num
    }
}

