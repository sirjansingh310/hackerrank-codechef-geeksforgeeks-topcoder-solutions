// https://leetcode.com/problems/top-k-frequent-elements/


class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i : nums) {
            freq.put(i, freq.getOrDefault(i, 0) + 1);
        }
        
        Map<Integer, Integer> sortedByFreq = freq.entrySet().stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, 
            (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        
        List<Integer> bestKeys = new ArrayList(sortedByFreq.keySet());
        
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = bestKeys.get(i);
        }
        
        return result;
    }
}
