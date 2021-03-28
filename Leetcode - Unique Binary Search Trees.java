//https://leetcode.com/problems/unique-binary-search-trees/submissions/
class Solution {
    private Map<Integer, Integer> memo = new HashMap<>();
    
    private int recur(int n) {
        if (n <= 1) {
            return 1;
        }
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            // i - 1 number of left sub trees
            // n - i - 1 number of right sub tress
            // for tree rooted at i
            sum += (recur(i - 1) * recur(n - i));
        }
        memo.put(n, sum);
        return sum;
    }
    
    public int numTrees(int n) {
        //  **The intuition here is that since our numbers are from 1 to n
        // ** each number i can act as a root and numbers smaller than i will
        // ** be in the left sub tree with i as root and numbers greater than
        // ** i in the right sub tree this goes on recursively for every sub
        // ** tree too where we can pull out any number from 1 to i-1 as root
        // ** for left sub tree and i+1 to n as root for right sub tree for
        // ** that particular recursive call
        return recur(n);
    }
}
