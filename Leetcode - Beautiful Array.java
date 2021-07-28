//https://leetcode.com/problems/beautiful-array/
class Solution {
    public int[] beautifulArray(int n) {
        List<Integer> list = IntStream.range(1, n + 1)
            .boxed()
            .collect(Collectors.toList());
       
        
        List<Integer> result = recur(list);
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = result.get(i);
        }
        return arr;
    }
    
    
    private List<Integer> recur(List<Integer> list) {
        
        if (list.size() <= 2) {
            return list;
        }
        
        List<Integer> odds = new ArrayList<>();
        List<Integer> evens = new ArrayList<>();
        
        for (int i = 0; i < list.size(); i++) {
            if (i % 2 == 0) {
                evens.add(list.get(i));
            } else {
                odds.add(list.get(i));
            }
        }
        
        // our list will always be sorted, we are never making a swap, only filtering into 2 lists
        // of even index numbers and odd index numbers. a number from first list and a number
        // from second list when summed together, will always be odd. given condition, 2 * nums[k] should not be equal to nums[i] + nums[j]. this means we don't want nums[i] + nums[j] to be even sum.
        // now we recursively solve for both lists and return the concat of both
        evens = recur(evens);
        evens.addAll(recur(odds));
        return evens;
        
    }
}
