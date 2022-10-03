// https://www.youtube.com/embed/q6IEA26hvXc
// https://leetcode.com/problems/median-of-two-sorted-arrays/
// O(log(M + N)) solution. Not trivial, watch the video again
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums2.length < nums1.length) {
            int[] t = nums1;
            nums1 = nums2;
            nums2 = t;
        }
        
        int left = 0, right = nums1.length - 1;
        int halfSize = (nums1.length + nums2.length) / 2;
        while (true) {
            int nums1MidIndex = (int)(Math.floor(((double)left + right) / 2)); // needed to do in this question and not simply (left + right) / 2 since left right can be negative and we solve them using -INF and INF below
            int nums2MidIndex = halfSize - nums1MidIndex - 2; // since we are dealing with index, length - 1 is last most index. But here we are having 2 halves so -2
        
            int nums1LeftPartitionRightNumber = Integer.MIN_VALUE;
            int nums1RightPartitionLeftNumber = Integer.MAX_VALUE;
            
            if (nums1MidIndex >= 0) {
                nums1LeftPartitionRightNumber = nums1[nums1MidIndex];
            }
           
            if ((nums1MidIndex + 1) < nums1.length) {
                nums1RightPartitionLeftNumber = nums1[nums1MidIndex + 1];
            }
            
            int nums2LeftPartitionRightNumber = Integer.MIN_VALUE;
            int nums2RightPartitionLeftNumber = Integer.MAX_VALUE;
            
            if (nums2MidIndex >= 0) {
                nums2LeftPartitionRightNumber = nums2[nums2MidIndex];
            }
            
            if ((nums2MidIndex + 1) < nums2.length) {
                nums2RightPartitionLeftNumber = nums2[nums2MidIndex + 1];
            }
            
            // our nums1 and nums2 left partitions are correct, we can find the median
            if (nums1LeftPartitionRightNumber <= nums2RightPartitionLeftNumber && nums2LeftPartitionRightNumber <= nums1RightPartitionLeftNumber) {
                if ((nums1.length + nums2.length) % 2 != 0) { // odd
                    return Math.min(nums1RightPartitionLeftNumber, nums2RightPartitionLeftNumber);
                } else {
                    return ((double)Math.max(nums1LeftPartitionRightNumber, nums2LeftPartitionRightNumber) + Math.min(nums1RightPartitionLeftNumber, nums2RightPartitionLeftNumber)) / 2;
                }
            } else if (nums1LeftPartitionRightNumber > nums2RightPartitionLeftNumber) {
                right = nums1MidIndex - 1;
            } else {
                left = nums1MidIndex + 1;
            }
        }
    }
}
