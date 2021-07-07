// https://leetcode.com/problems/reduce-array-size-to-the-half/

// O(NlogN) solution using MaxHeap. Can be done with sorting as well with same Time complexity

class Pair implements Comparable<Pair>{
    int number;
    int freq;
    
    Pair(int number, int freq) {
        this.number = number;
        this.freq = freq;
    }
    
    @Override
    public int compareTo(Pair pair) {
        return pair.freq - this.freq;
    }
    
    @Override 
    public String toString() {
        return this.number + " " + this.freq;
    }
}

class Solution {
    public int minSetSize(int[] arr) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        
        for (int i = 0; i < arr.length; i++) {
            freqMap.put(arr[i], freqMap.getOrDefault(arr[i], 0) + 1);
        }
        
        PriorityQueue<Pair> pq = new PriorityQueue<>(); // maxheap
        
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            pq.add(new Pair(entry.getKey(), entry.getValue()));
        }
        
        int eliminated = 0, total = arr.length;
        int count = 0;
        while (!pq.isEmpty()) {
            Pair current = pq.poll();
            eliminated += current.freq;
            count++;
            
            if (eliminated >= total / 2) {
                break;
            }
        }
        
        return count;
    }
}
