class Solution {
    public int findLHS(int[] nums) {
        // if a harmonious sequence is sequence with max element of subseq - min element of sub seq = 1
        // then the subsequence will contain only those 2 numbers
        
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i : nums) {
            freq.put(i, freq.getOrDefault(i, 0) + 1);
        }
        int maxLength = 0;
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            int num = entry.getKey();
            int freqOfNum = entry.getValue();
            int currentLength = 0;
            if (freq.containsKey(num + 1)) {
                currentLength = freqOfNum + freq.get(num + 1);
            }
            maxLength = Math.max(maxLength, currentLength);
        }
        return maxLength;
    }
}
