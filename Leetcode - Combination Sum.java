// https://leetcode.com/problems/combination-sum/
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        
        // coin change problem with result to be returned instead of count
        Map<Integer, List<List<Integer>>> map = new HashMap<>(); // map key is the target. 
        
        for (int i = 1; i <= target; i++) {
            List<List<Integer>> emptyList = new ArrayList<>();
            map.put(i, emptyList);
        }
        
        for (int i = 0; i < candidates.length; i++) {
            for (int j = candidates[i]; j <= target; j++) {
                if (candidates[i] == j) {// base case, just add that coin
                    map.get(j).add(new ArrayList<>(Arrays.asList(candidates[i])));
                } else if (map.containsKey(j - candidates[i])) {
                   for (List<Integer> targetList : map.get(j - candidates[i])) { // for all possible contributions where candidates[i] when included gives target j. 
                       List<Integer> shallowCopy = new ArrayList<>(targetList);
                       shallowCopy.add(candidates[i]);
                       map.get(j).add(shallowCopy);
                   }
                }
            }
        }
        return map.get(target);
    }
}
