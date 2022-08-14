// O(N) time and O(1) space without touching the original nums array
// https://www.youtube.com/watch?v=wjYnzkAhcNk&ab_channel=NeetCode
// Not a very intuitive solution. The question boils down to 
// finding where loop starts in a linked list with loop
//https://leetcode.com/problems/find-the-duplicate-number/
class Solution {
    public int findDuplicate(int[] nums) {
        int slow = 0, fast = 0;
        while (true) {
            slow = nums[slow];
            fast = nums[nums[fast]]; // jump by 2 positions
            
            if (slow == fast) {
                break;
            }
        }
        int slow2 = 0;
        
        while (true) {
            slow = nums[slow];
            slow2 = nums[slow2];
            if (slow == slow2) {
                break;
            }
        }
        
        return slow;
    }
}
