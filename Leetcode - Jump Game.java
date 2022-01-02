//https://leetcode.com/problems/jump-game/
// O(N) Time, O(1) Space. Just go backwards. currentPos - current position, prevPos - best index last recorded from where we can reach till end for sure
class Solution {
    public boolean canJump(int[] nums) {
        int currentPos = nums.length - 1;
        int prevPos = nums.length - 1;
        
        while (currentPos >= 0) {
            if (currentPos + nums[currentPos] >= prevPos) {
                prevPos = currentPos;
            }
            currentPos--;
        }
        
        return prevPos == 0;
    }
}
