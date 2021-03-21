//https://leetcode.com/problems/next-greater-element-i/
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> numberToIdx = new HashMap<>();
        
        for (int i = 0; i < nums2.length; i++) {
            numberToIdx.put(nums2[i], i);
        }
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            int positionInNums2 = numberToIdx.get(nums1[i]);
            result[i] = getNext(nums1[i], positionInNums2, nums2);
        }
        
        return result;
    }
    
    private int getNext(int number, int index, int[] nums2) {
        for (int i = index + 1; i < nums2.length; i++) {
            if (nums2[i] > number) {
                return nums2[i];
            }
        }
        return -1;
    }
}


// stack + map sol
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // numbers in num2 and nums1 are unique, lets utilize this fact
        
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> resultMap = new HashMap<>();
        
        int right = nums2.length - 1;
        stack.push(nums2[right]);
        resultMap.put(nums2[right], -1); // no elements for it on right 
        right--;
        
        for (int i = right; i >=0; i--) {
            while (!stack.isEmpty() && stack.peek() < nums2[i]) {
                stack.pop();
            }
            resultMap.put(nums2[i], stack.isEmpty() ? -1 : stack.peek());// bigger than this in stack, i.e number in right of this positon
            stack.push(nums2[i]);// for numbers in loop after, that is right - 1 pos
        }
        
        // System.out.println(resultMap);
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = resultMap.getOrDefault(nums1[i], -1);
        }
        
        return result;
    }
}
