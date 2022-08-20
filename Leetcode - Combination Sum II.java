// https://leetcode.com/problems/combination-sum-ii/

// Brute force - TLE
class Solution {
    private Set<List<Integer>> result = new HashSet<>();
    
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        recur(candidates, 0, target, new ArrayList<>());
        return new ArrayList<>(result);
    }
    
    private void recur(int[] candidates, int index, int target, List<Integer> list) {
        if (target == 0) {
            result.add(new ArrayList<>(list));
            return;
        } else if (index >= candidates.length || target < 0) {
            return;
        }
        
        // skip current
        recur(candidates, index + 1, target, list);
        // add current and move ahead since a number can be chosen only once
        list.add(candidates[index]);
        recur(candidates, index + 1, target - candidates[index], list);
        list.remove(list.size() - 1); 
    }
}

// Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.

// Each number in candidates may only be used once in the combination.

// Note: The solution set must not contain duplicate combinations.
// This Note is the tricky part of the question
// We sort the candidates. 
// In current recursion, for sorted candidates [1,1,2,3,5] if our pointer is at index 0, we are going to form combinations with 1 at index 0 with all other values, [1,2,3,5]. Now when are loop pointer increments, we are still at value 1 and if we recur, it is going to lead to duplicates as we did it in previous iteration.
// this is the crux of the problem and trick to avoid TLE

// By any means we are not saying our combination will contain only one 1, we are giving chance to all indexes of candidates. But we are back tracking very smartly just to avoid duplicates, instead of duing brute force and removing duplicates by adding in set at last. That is slow. 
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        recur(candidates, 0, target, result, new HashSet<>());
        return result;
    }
    
    private void recur(int[] candidates, int index, int target, List<List<Integer>> result, Set<Integer> indexSet) {
        if (target == 0) {
            List<Integer> list = new ArrayList<>();
            for (int i : indexSet) {
                list.add(candidates[i]);
            }
            result.add(list);
            return;
        } else if (index >= candidates.length || target < 0) {
            return;
        }
        
        for (int start = index; start < candidates.length; start++) {
            if (start > index && candidates[start] == candidates[start - 1]) {
                continue;//avoid duplicates now itself rather than doing deep recursion and eliminating
            }
            // Also in the brute force we did ignore an element and move ahead, that is implicitly achieved here since we loop from start..candidate.length
            // and in each iteration of the loop we already backtracked. So each iteration will also take care of the case where previous element was skipped.
            int newTarget = target - candidates[start];
            if (newTarget < 0) {// since candidates are sorted
                break;
            }
            
            indexSet.add(start);
            recur(candidates, start + 1, newTarget, result, indexSet);
            indexSet.remove(start);
        }
    }
}
