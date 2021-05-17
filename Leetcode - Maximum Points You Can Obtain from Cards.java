//https://leetcode.com/explore/challenge/card/may-leetcoding-challenge-2021/599/week-2-may-8th-may-14th/3739/
class Solution {
    public int maxScore(int[] cardPoints, int k) {
       /*
       Think of this problem in opposite way. We can remove a sub array of size n - k, such that
       remaining cards have maximum possible total
       that directly means, remove subarray with least sum!
       We can do this with sliding window
       */
        
        int left = 0, right = 0, minSum = Integer.MAX_VALUE, currentSum = 0;
        
        while (left <= right && right < cardPoints.length) {
            currentSum += cardPoints[right];
            
            // remove extra if present
            while (right - left + 1 > cardPoints.length - k) {
                currentSum -= cardPoints[left];
                left++;
            }
            // update minSum
            if (right - left  + 1 == (cardPoints.length - k)) {
                minSum = Math.min(minSum, currentSum);
            }
            
            right ++;
        }
        int totalSum = Arrays.stream(cardPoints).reduce(0, (a, b) -> a + b);
        return totalSum - minSum;
    }
}
