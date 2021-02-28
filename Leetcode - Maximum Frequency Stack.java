/// https://leetcode.com/explore/featured/card/february-leetcoding-challenge-2021/587/week-4-february-22nd-february-28th/3655/
class FreqStack {
    private Map<Integer, Integer> freqMap;
    private PriorityQueue<Integer> priorityQueue;
    private int currentInsertOrder;
    private Map<Integer, List<Integer>> insertOrdersMap;
    
    public FreqStack() {
        freqMap = new HashMap<>();
        insertOrdersMap = new HashMap<>();
        priorityQueue = new PriorityQueue((a, b) -> {
            if (freqMap.containsKey(a) && freqMap.containsKey(b)) {
                if (freqMap.get(a).equals(freqMap.get(b))) {
                    return compareByInsertOrder((Integer)a, (Integer)b);
                }
                return freqMap.get(b).compareTo(freqMap.get(a));
            } else if (freqMap.containsKey(a)) {
                return -1;
            } else {
                return 1;
            }
        });
    }
    
    public void push(int x) {
        if (insertOrdersMap.containsKey(x)) {
            insertOrdersMap.get(x).add(currentInsertOrder);
        } else {
            insertOrdersMap.put(x, new ArrayList<>(Arrays.asList(currentInsertOrder)));
        }
        
        if (priorityQueue.contains(x)) {// to re heapify after insertOrderMap & freqMap updates
            priorityQueue.remove(x);
        }
        
        freqMap.put(x, freqMap.getOrDefault(x, 0) + 1);
        priorityQueue.add(x); // re heapify
        currentInsertOrder++;
    }
    
    public int pop() {
        int maxFreqElement = priorityQueue.poll();

        List<Integer> insertList = insertOrdersMap.get(maxFreqElement);
        insertList.remove(insertList.get(insertList.size() - 1));
        
        freqMap.put(maxFreqElement, freqMap.get(maxFreqElement) - 1);
        priorityQueue.add(maxFreqElement); // re heapify
        
        return maxFreqElement;
    }
    
    private int compareByInsertOrder(Integer a, Integer b) {
        List<Integer> first = insertOrdersMap.get(a);
        List<Integer> second = insertOrdersMap.get(b);
        
        if (validList(first) && validList(second)) {
            return second.get(second.size() - 1).compareTo(first.get(first.size() - 1));
        }
        
        if (validList(first)) {
            return -1;
        } else if (validList(second)) {
            return 1;
        }
        return 0;
    }
    
    private boolean validList(List<Integer> list) { // Collections.isEmpty implementation
        return list != null && !list.isEmpty();
    }
}
/*
The priortyQueue used here will contain no duplicate element. As a number is pushed/pop, its insertOrder / freq is updated and the heap is re-heapify by removing it 
first, update freq and insert order data structure, and add the number again.
In this way, our PQ contains no duplicate and always accurate with numbers heapified according to insert order + freq comparasion. 

It is a good solution, as we save space on PQ, but time is bit more, multiple (O(logN)) operations on it, overall O(logN) in push / pop on FreqStack, but still. 

Another solution is to create a "Element" class obj.

class Element {
int number;
int insertOrder;
int freqWhileElementInserted;
}

Element would be the snapshot of a number being pushed / popped out of FreqStack
Our PQ would be of type Element.
In push operation, we would simply push a new Element to the PQ. And during pop, remove the top most of the heap.

We would not do a explicit re heapify at all. But our PQ would be big, and during push / pop, re heapify is anyways done internally.
*/

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 */
