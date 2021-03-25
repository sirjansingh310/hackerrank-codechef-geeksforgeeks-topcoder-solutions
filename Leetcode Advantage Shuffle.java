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
