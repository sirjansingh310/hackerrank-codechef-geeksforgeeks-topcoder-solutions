// https://leetcode.com/problems/combination-sum-iii/
class Solution {
    private List<List<Integer>> result = new ArrayList<>();
    
    private void combine(Set<Integer> set, int k, int n, int toAdd) {
        if (n == 0 && set.size() == k) {
            result.add(new ArrayList<>(set));
            return;
        } else if (n < 0 || toAdd >= 10) {
            return;
        }
        
        set.add(toAdd);
        combine(set, k, n - toAdd, toAdd + 1);
        set.remove(toAdd);// backtrack
        combine(set, k, n, toAdd + 1);
        
        return;
    }
    
    public List<List<Integer>> combinationSum3(int k, int n) {
        combine(new HashSet<>(), k, n, 1);
        return result;
    }
}
