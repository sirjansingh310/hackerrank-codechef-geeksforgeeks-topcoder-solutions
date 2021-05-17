class Solution {
  //https://leetcode.com/explore/challenge/card/may-leetcoding-challenge-2021/599/week-2-may-8th-may-14th/3737/  
  public boolean isPossible(int[] target) {
        
        if (target.length == 1)
            return target[0] == 1;
        
        int totalSum = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        for (int num : target) {
            totalSum += num;
            pq.add(num);
        }
        
        while (totalSum != target.length) {
            int largest = pq.remove();
            int rest = totalSum - largest;
            
            // This will only occur if n = 2.
            if (rest == 1) {
                return true;
            }
            int newValueAtLargest = largest % rest;
            
            // If newValueAtLargest is now 0 (invalid) or didn't
            // change, then we know this is impossible.
            if (newValueAtLargest == 0 || newValueAtLargest == largest) {
                return false;
            }
            pq.add(newValueAtLargest);
            totalSum = totalSum - largest + newValueAtLargest;
        }
        
        return true;
    }
}
