//https://leetcode.com/problems/minimum-difference-between-largest-and-smallest-value-in-three-moves/
/*
T.C : O(NLOGN), S.C: O(1)
You are given an array, you can make at most 3 moves and in each move, you are allowed to change a number to any other number.
After making such moves, the difference between max and min of the new array is to be calculated.
Return the minimum possible such difference

Greedy approach. if size of array is less than or eq to 4, ans is 0 for sure(all elements are equal)

Now for general case, first sort the array. Our goal is to minimize the difference between max and min element. 
ex: [6,6,0,1,1,4,6] => [6,6,4,4,4,4,6] => result 2

To minimize diff, Sort the array and we are left with following cases:
i)make big elements smaller: n -1, n - 2, n - 3 indexed elements to n - 4. so diff is nums[n - 4] - nums[0]
ii)make smaller elements bigger: 0, 1, 2, to be equal to element at index 4. so diff is nums[nums.length - 1] - nums[3]
iii) do both operations combined for a better effort, two on the extreme right and one on the extreme left or 
iv) two on the extreme left and one on the extreme right

the answer will be minimum of the above 4 cases.
*/
class Solution {
    public int minDifference(int[] nums) {
        if (nums.length <= 4) {
            return 0;
        }
        Arrays.sort(nums);
        int[] possibleDiff = new int[4];
        possibleDiff[0] = nums[nums.length - 4] - nums[0];
        possibleDiff[1] = nums[nums.length - 1] - nums[3];
        possibleDiff[2] = nums[nums.length - 2] - nums[2];
        possibleDiff[3] = nums[nums.length -3] - nums[1];
        
        int bestDiff = nums[nums.length - 1] - nums[0];
        for (int i : possibleDiff) {
            bestDiff = Math.min(bestDiff, i);
        }
        
        return bestDiff;
    }
}
