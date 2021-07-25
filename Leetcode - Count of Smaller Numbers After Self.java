/*
5 star problem. amazing

https://leetcode.com/explore/challenge/card/june-leetcoding-challenge-2021/606/week-4-june-22nd-june-28th/3792/
Problem:
 
You are given an integer array nums and you have to return a new counts array. 
The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
This problem is an enhancement of the famous problem: count inversion. it is an application of merge sort

instead of counting total of all inversions, we have to return an array, which contains inversion for each element, comparing to all in right to it.
i) modify the solution to bucket the inversions by the Pair obj and not just do the regular total count. Pair obj is used as a number in original array can be repeated, so we need to create 
a pair with original index in mind.

Now with the above point we get TLE. See the bottleneck of TLE in the EOF comment.

ii) Here we update all elements with which there is an inversion. we can smartly modify our solution
 to do a descending order merge sort, and count inversion as if we were sorting in ascending order. check comment in line 83.
 
 TC: O(NlogN)
*/

class Pair implements Comparable<Pair> {
    final int num;
    final int originalIndex;
    
    
    public Pair(int num, int originalIndex) {
        this.num = num;
        this.originalIndex = originalIndex;
    }
    
    public int compareTo(Pair p) {
        if (this.num == p.num) {
            return this.originalIndex - p.originalIndex;
        }
        return this.num - p.num;
    }
    
    public String toString() {
        return "num = " + this.num + " original index = " + this.originalIndex;
    }
}


class Solution {
    public List<Integer> countSmaller(int[] nums) {
        Map<Pair, Integer> map = new HashMap<>();
        
        Pair[] numPairs = new Pair[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numPairs[i] = new Pair(nums[i], i);
        }
        
        Pair[] sorted = new Pair[nums.length];
        mergeSort(numPairs, map, 0, nums.length - 1, sorted);
        List<Integer> result = new ArrayList<>();
        
        for (int i = 0; i < numPairs.length; i++) {
            result.add(map.getOrDefault(numPairs[i], 0));
        }
        return result;
    }
    
    
    private void mergeSort(Pair[] nums, Map<Pair, Integer> result, int low, int high, Pair[] sorted) {
        if (high == low) {
            sorted[0] = nums[low];
            return;
        }
        
        int mid = (low + high) / 2;
        Pair[] left = Arrays.copyOfRange(nums, low, mid + 1);
        Pair[] right = Arrays.copyOfRange(nums, mid + 1, high + 1);
        
        mergeSort(nums, result, low, mid, left);
        mergeSort(nums, result, mid + 1, high, right);
        merge(left, right, sorted, result);
    }
    
    private void merge(Pair[] left, Pair[] right, Pair[] sorted, Map<Pair, Integer> result) {
        int m = left.length, n = right.length, i = 0, j = 0, k = 0;
        
        while (i < m && j < n) {
            // desc order merge sort. in desc order, there is no inversion, but our problem expects us to count the inversion if we were doing asc order
           //
            if (left[i].num > right[j].num) {
                //// no inversion should be tackled here as we are doing sort by desc order. But our original problem
              // expects that inversions will be counted if the original array is to be sorted in ascending order. 
              // so in O(1), we update inversion for that key!!
                result.put(left[i], result.getOrDefault(left[i], 0) + (n - j));
                sorted[k++] = left[i++];
            } else { 
               // inversion wrt to descending order should be tackled here
                sorted[k++] = right[j++];
            }
        }
        
        while (i < m) {
            sorted[k++] = left[i++];
        }
        
        while (j < n) {
            sorted[k++] = right[j++];
        }
        return;
    } 
}


/*
bottleneck with my first submission using merge sort
calucating inversion when sorting in asc order
in merge function: 
while (i < m && j < n) {
            if (left[i].num <= right[j].num) {
                sorted[k++] = left[i++];
            } else { // inversion as left[i] is bigger. right[i] has inversions with all left[i.. left.length]. Update inversion count for rest of the elements in left
                // total inversion count for this element is m - i. distribute it to the required buckets
                for (int inv = i; inv < m; inv++) {
                    result.put(left[inv], result.getOrDefault(left[inv], 0) + 1);
                }
                sorted[k++] = right[j++];
            }
        }
*/
