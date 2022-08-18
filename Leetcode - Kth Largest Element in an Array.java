// https://www.youtube.com/watch?v=XEmy13g1Qxc
// Quick select solution, variation of quick sort. avg case O(N), worst Case O(N^2)
// watch video for understanding it. 
// this algo is like quick sort + binary search, code wise

// we can solve this in O(KlogK) time also see solution below

class Solution {
    private int quickSelect(int[] nums, int k, int left, int right) {
        int pivotValue = nums[right];
        int positionToInsert = left;
        
        for (int i = left; i <= right - 1; i++) {
            if (nums[i] <= pivotValue) { // move all value <= pivotValue in left half. Right half will be values > pivotValue. nums[right] will be pivot.
                int temp = nums[i];
                nums[i] = nums[positionToInsert];
                nums[positionToInsert] = temp;
                positionToInsert++;
            }
        }
        
        // swap pivot
        int temp = nums[right];
        nums[right] = nums[positionToInsert];
        nums[positionToInsert] = temp;
        
        // now all values on left of positionToInsert are <= pivotValue
        // all elements more than pivotValue are on right. 
        // of course both left and right half is unsorted.
        
        int kthLargestMatchIndex = nums.length - k;
        
        // Notice we go to either left or right half, instead of both halves like quick sort.
        // So initially we loop n elements, then n/2, then n/4... 
        // this is an inf series which sums to 2n. therefore the avg time is O(N)
        // but this is when we get 2 equal halves. worst case is our pivot is always extreme element(case when array is sorted). So it will be O(N^2) in worst case
       
        if (kthLargestMatchIndex > positionToInsert) {// we know number is on right
            return quickSelect(nums, k, positionToInsert + 1, right);
        } else if (kthLargestMatchIndex < positionToInsert) {
            return quickSelect(nums, k, left, positionToInsert - 1);
        }
        return nums[positionToInsert];
    }
    
    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, k, 0, nums.length - 1);
    }
}


// O(KlogK) Min Heap

class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // min heap storing k big numbers. The kth rank number in the order is on root
        
        for (int i : nums) {
            pq.add(i);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        
        return pq.peek();
    }
}
