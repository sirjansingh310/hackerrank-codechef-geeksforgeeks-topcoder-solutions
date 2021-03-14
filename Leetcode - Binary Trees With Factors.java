//https://leetcode.com/explore/challenge/card/march-leetcoding-challenge-2021/589/week-2-march-8th-march-14th/3670/
class Solution {
    public int numFactoredBinaryTrees(int[] arr) {
        Map<Integer, Long> treeCountMap = new HashMap<>();
        int mod = (int)(Math.pow(10, 9)) + 7;
        Arrays.sort(arr);// to get smaller ones before 
        
        for (int i = 0; i < arr.length; i++) {
            long count = 1L;
            for (int j = 0; j < i; j++) {
                if (arr[i] % arr[j] == 0 && treeCountMap.containsKey(arr[i] / arr[j])) {
                    count += (treeCountMap.get(arr[j]) * treeCountMap.get(arr[i] / arr[j]));
                }
            }
            treeCountMap.put(arr[i], count);
        }
        
        long total = 0L;
        List<Long> values = new ArrayList<>(treeCountMap.values());
        for (long v : values) {
            total += v;
        }
        
        return (int)(total % mod);
    }
}

