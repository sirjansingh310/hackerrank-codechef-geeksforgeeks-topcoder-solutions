//https://leetcode.com/explore/challenge/card/march-leetcoding-challenge-2021/591/week-4-march-22nd-march-28th/3683/
class Solution {
    private int binarySearchNumberJustHigherThanKey(int key, List<Integer> list) {
        int low = 0, high = list.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            
            if (key > list.get(mid)) {
                low = mid + 1;
            } else if (key < list.get(mid)) {
                high = mid - 1;
            } else if (key == list.get(mid)) {
                low = mid + 1;
            }
        }
        
        if (low >= list.size()) {
            low = 0;
        }
        
        int result = list.get(low);
        list.remove(low);
        return result;
    }
    public int[] advantageCount(int[] A, int[] B) {
        Arrays.sort(A);
        List<Integer> list = new ArrayList<>();
        for (int i : A) {
            list.add(i);
        }
        
        int[] result = new int[A.length];
        for (int i = 0; i < B.length; i++) {
            result[i] = binarySearchNumberJustHigherThanKey(B[i], list);
        }
        return result;
    }
}

// GREEDY SOLUTION
class Solution {
    public int[] advantageCount(int[] A, int[] B) {
        Map<Integer, Queue<Integer>> assigned = new HashMap<>(); // as numbers in list are not unique, we use queue as value of key. We are going to sort B array so need to maintain mapping as we need to 
        // return answer which corresponds to original input B
        for (int i : B) {
            assigned.put(i, new LinkedList<>());
        }
        Queue<Integer> remaining = new LinkedList<>();
        
        int[] bCopy = B.clone();
        
        Arrays.sort(A);
        Arrays.sort(B);
        
        for (int i = 0, j = 0; i < A.length; i++) {
            if (A[i] > B[j]) {// as sorted, A[i] and B[j] are small. Use it, otherwise throw it out to put at last, i.e the ones who could not get beat. We want to maximize positions where A[i] > B[i]
                assigned.get(B[j]).add(A[i]);
                j++;
            } else {
                remaining.add(A[i]);
            }
        }
        
        
        int[] answer = new int[B.length];
        
        
        for (int i = 0; i < bCopy.length; i++) {
            if (assigned.get(bCopy[i]).size() > 0) {
                answer[i] = assigned.get(bCopy[i]).poll();
            } else {
                answer[i] = remaining.poll();
            }
        }
        
        return answer;
    }
}
