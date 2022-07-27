// O(KlogN) solution + O(N) solution
// Actually both are fast since K is small, but when k -> n, O(N) is much faster
// solution 1, O(KlogN), store freq in Max heap, and poll k times

class Solution {
    private class Pair implements Comparable<Pair> {
        int num;
        int freq;
        
        public Pair(int num, int freq) {
            this.num = num;
            this.freq = freq;
        }
        
        @Override
        public int compareTo(Pair other) {
            return other.freq - this.freq;
        }
    }
    
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);
        }
        
        PriorityQueue<Pair> queue = new PriorityQueue<>();
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            queue.add(new Pair(entry.getKey(), entry.getValue()));
        }
        
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = queue.poll().num;
        }
        return result;
    }
}

// O(N) Solution, using inverted index

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        /*
        Brilliant solution. O(N) TC and O(N) space
        we first create a freq map
        then we create an inverse freq array, where key/index is freq, and value is list of numbers with that freq
        Notice that bothare O(N) space. For the second one, even if each element is at most 1
        time, we will have N elements in the array.
        Now go over this in reverse to fetch top k elements
        */
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);
        }
        
        List<List<Integer>> inverseFreq = new ArrayList<>();
        
        for (int i = 0; i <= nums.length; i++) {
            inverseFreq.add(new ArrayList<>());
        }
        
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            inverseFreq.get(entry.getValue()).add(entry.getKey());
        }
        
        int[] result = new int[k];
        int idx = 0;
        
        // nested but O(N) time
        for (int i = inverseFreq.size() - 1; i >= 0; i--) {
            for (int j = 0; j < inverseFreq.get(i).size(); j++) {
                if (idx == k) {
                    return result;
                }
                result[idx++] = inverseFreq.get(i).get(j);
            }
        }
        return result;
    }
}
