class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        // problem:
        // k = number of bananas to eat in one hour from one pile
        // if that pile has less than k, the monkey has to wait
        // return least k(rate of bananas per hour) such that monkey 
        // eats all bananas in h hours
        
        // we know with the value at max of the piles array, if considered
        // as k is okay, and monkey can finish all bananas from piles
        // but it is not the minimum, it is the max bound we can think
        // min bound is 1. do binary search between these 2 and see if the 
        // value at mid is good enough as k. if yes, try to reduce it as we want minimum. 
        
        int max = -1;
        for (int i : piles) {
            max = Math.max(i, max);
        }
        
        int low = 1, high = max;
        int k = high;
        
        while (low <= high) {
            int mid = (low + high) / 2;
            boolean canEatAll = canEatAll(mid, h, piles);
            
            if (canEatAll) {
                k = mid;
                high = mid - 1;// try to minimize with better solution
            } else {
                low = mid + 1;
            }
        }
        
        return k;
    }
    
    private boolean canEatAll(int rate, int hourLimit, int[] piles) {
        long overflowPile = 0L;
        // simulate to check if possible
        for (int i = 0; i < piles.length; i++) {
            if (piles[i] > rate) {

                // 100 overflow, with rate 20, we need to finish this bucket in 5 sittings, 1 sitting done now, so 4 sittings overflow
                overflowPile += (long)(Math.ceil((double)(piles[i] - rate) / rate));
            }
            hourLimit--;
        }
    
        return overflowPile <= hourLimit; // see left over can be eaten in remaining time
    }
}
