// https://practice.geeksforgeeks.org/problems/find-missing-and-repeating2512
// Since we know numbers are 1 to n, we can use them as index values also. We manipulate the array by multiplying nums[nums[i] - 1] with -1 to indicate
// nums[i] is present. If a number is already < 0, that means current index we want to manipuulate is a repeating number, and we ignore it.
// finally number which is positive was not touched, meaning that index value is the missing number is the list.
// Time: O(n), space O(1). 
class Solve { 
    int[] findTwoElement(int nums[], int n) {
        // code here
        int missing = -1, repeating = -1;
        
        for (int i = 0; i < nums.length; i++) {
            int indexToJump = Math.abs(nums[i]) - 1;
            if (nums[indexToJump] < 0) {
                repeating = indexToJump + 1;
            } else {
                nums[indexToJump] *= -1;
            }
            
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                missing = i + 1;
            }
        }
        
        return new int[]{repeating, missing};
    }
}
