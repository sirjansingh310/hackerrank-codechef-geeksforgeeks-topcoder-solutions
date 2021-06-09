// Very good question. 
// Please go over the problem statement here https://leetcode.com/explore/challenge/card/june-leetcoding-challenge-2021/604/week-2-june-8th-june-14th/3773/

// O(N*K) DP Approach (TLE)

// The idea is, dp will store the cost of reaching from node i to node n - 1. 
// We start from node n - 1, and update all nodes within range K.
// dp[0] at last will have best score to reach n - 1
class Solution {
    public int maxResult(int[] nums, int k) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[nums.length - 1] = nums[nums.length - 1];
        
        for (int i = nums.length - 1; i >= 0; i--) {
            int jumps = k;
            for (int j = i - 1; j >= 0 && jumps > 0; j--, jumps--) {
                dp[j] = Math.max(dp[j], nums[j] + dp[i]);
            }
        }
        
        return dp[0];
    }
}

// The problem with this approach
// We are updating dp for each i and j
// Now take a step back and think, if you are standing at node i, and all values of dp from i + 1 to n - 1 are calculated already
// You would just pick the best node in front of you (of course upto k). Need not go over all K nodes and check

// The solution: Max heap. Store the best value node (upto range k from given i)
// Update the current dp[i] with value present on top of max heap. If this new value will be more, it will go on top after adding it to the heap(heapify will be called
// internally)
// But before adding the dp[i] value, we need to remove the top value of heap if it is out of bound (i.e after k). So in this way, we maintain heap top to be
// in range i, i + k


class Element implements Comparable<Element>{
    int num;
    int index;
    
    Element(int num, int index) {
        this.num  = num;
        this.index = index;
    }
    
    public int compareTo(Element element) {
        return this.num - element.num;
    }
    
    public String toString() {
        return "num = " + num + " index = " + index + "\n";
    }
}

// O(NlogK) DP + Max-heap
class Solution {
    public int maxResult(int[] nums, int k) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[nums.length - 1] = nums[nums.length - 1];
        PriorityQueue<Element> pq = new PriorityQueue<>(Collections.reverseOrder());
       
        for (int i = nums.length - 1; i >= 0; i--) {
            if (pq.isEmpty()) {
                pq.add(new Element(nums[i], i));
                continue;
            }
            // remove out of bound elements, only if it is on top of max heap. Otherwise, it won't make a diff
            while (pq.peek().index > i + k) {
                pq.poll();
            }
            // now heap has top value within K range. So below opearation becomes safe.
            dp[i] = nums[i] + pq.peek().num;
            pq.add(new Element(dp[i], i));
        }
        
        
        return dp[0];
    }
}
