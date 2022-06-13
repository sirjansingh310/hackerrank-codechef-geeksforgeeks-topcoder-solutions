// https://leetcode.com/problems/candy/
// Main logic. Find peeks in the array, for example 1,2,1 -> 2 is peak. 1,2,2,1 both 2's are peak
// If only one peak is present, go to left bottom, fill up candies from bottom to peak starting from 1. If there is a tie, reset candy count to 1
// Same for right, now we have filled left and right correctly. For peak index itself, it will be maximum from left or right.
// if multiple peaks exist, do the same. But no need to choose for maximum as they are different and their respective values are returned from fillLeft and fillRight.
// Since we are dealing with List<Integer>, avoid mistake of == check, use .equals() or .intValue()
// Greedy solution. O(N) O(N) time and space.

// Since we are dealing with peaks/slopes here and filling from 1 when we are at bottom, notice we need not do this process left to right once, 
// and right to left once. Since we are filling from bottom, the bottom value, which is part of 2 slopes(ex 1,2,1,2, here 2nd 1 is part of 2 slopes),
// will always have optimal candy count, i.e 1, and we start filling from it. So a single iteration from left to right will always be optimal

class Solution {
    private final class Peak {
        int peakIndex;
        int peakCount;
        
        Peak(int peakIndex, int peakCount) {
            this.peakIndex = peakIndex;
            this.peakCount = peakCount;
        }
        
        @Override
        public String toString() {
            return String.format("Peak index is %d. Peak count is %d. \n", peakIndex, peakCount);
        }
    }
    
    public int candy(int[] ratings) {
        List<Integer> paddedRatings = new ArrayList<>();
        paddedRatings.add(-1);
        paddedRatings.addAll(Arrays.stream(ratings).boxed().collect(Collectors.toList()));
        paddedRatings.add(-1);
        List<Peak> mergedRatings = new ArrayList<>(); // just reusing Peak class to represent 1 100 100 1 -> [1, 1], [100, 2], [1, 1]
        mergedRatings.add(new Peak(0, 0));
        Peak current = null;
        for (int i = 1; i < paddedRatings.size() - 1; i++) {
            if (paddedRatings.get(i).intValue() != paddedRatings.get(i - 1).intValue()) {
                current = new Peak(i, 1);
                mergedRatings.add(current);
            } else {
                current.peakCount++;
            }
        }
        mergedRatings.add(new Peak(paddedRatings.size() - 1, 0));
        List<Peak> peaks = new ArrayList<>();
        for (int i = 1; i < mergedRatings.size() - 1; i++) {
            int left = paddedRatings.get(mergedRatings.get(i - 1).peakIndex);
            int right = paddedRatings.get(mergedRatings.get(i + 1).peakIndex);
            int currentValue = paddedRatings.get(mergedRatings.get(i).peakIndex);
            if (currentValue > left && currentValue > right) {
                peaks.add(mergedRatings.get(i));
            }
        }
        List<Integer> candies = fillCandies(peaks, paddedRatings);
        int candyCount = getFinalCount(candies);
        
        return candyCount;
    }
    
    
    private int fillLeft(List<Integer> list, int index, List<Integer> candies) {
        int count = 1;
        int indexCopy = index;
        while (index != 0 && list.get(index - 1) <= list.get(index)) {
            count++;
            index--;
        }
        if (index == 0) {
            index++;
        }
        int candy = 1;
        while (index <= indexCopy) {
            if (list.get(index).intValue() == list.get(index - 1).intValue()) {
                candy = 1;
            }
            candies.set(index, candy);
            index++;
            candy++;
        }
        return candies.get(indexCopy);
    }
    
    private int fillRight(List<Integer> list, int index, List<Integer> candies) {
        int count = 1;
        int indexCopy = index;
        while (index != list.size() -1 && list.get(index + 1) <= list.get(index)) {
            count++;
            index++;
        }
        if (index == list.size() - 1) {
            index--;
        }
        int candy = 1;
        while (index >= indexCopy) {
            if (list.get(index).intValue() == list.get(index + 1).intValue()) {
                candy = 1;
            }
            candies.set(index, candy);
            candy++;
            index--;
        }
        return candies.get(indexCopy);
    }
    
    private List<Integer> fillCandies(List<Peak> peaks, List<Integer> paddedRatings) {
        List<Integer> candies = new ArrayList<Integer>(paddedRatings);
        Collections.fill(candies, 1);
        
        for (Peak peak : peaks) {
            int leftCandy = fillLeft(paddedRatings, peak.peakIndex, candies);
            int rightCandy = fillRight(paddedRatings, peak.peakIndex + peak.peakCount - 1, candies);
            // only 1 peak, we need to fill with max, otherwise candies we filled till now are fine
            if (peak.peakCount == 1) {
                candies.set(peak.peakIndex, Math.max(leftCandy, rightCandy));
            }
        }
        return candies;
    }
    
    private int getFinalCount(List<Integer> candies) {
        int sum = 0;
        for (int i = 1; i < candies.size() - 1; i++) {
            sum += candies.get(i);
        }
        return sum;
    }
}
