class Solution {
    private static final int MOD = 1000000007;
    private static final int NOT_POSSIBLE = Integer.MIN_VALUE;
    private final Map<String, Integer> memo = new HashMap<>();
    
    private int isPossible(int index, int[] arr, int coinCount, int sum) {
        String key = index + "_" + coinCount +"_" + sum;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        if (coinCount == 3) {
            return sum == 0 ? 1 : 0;
        }
        if (index < 0) {
            return 0;
        }
        int result = 0;
        int possibilities = isPossible(index - 1, arr, coinCount + 1, sum - arr[index]);
        if (possibilities != 0) {
            result = possibilities % MOD;
        }
        possibilities = isPossible(index - 1, arr, coinCount, sum);
        if (possibilities != 0) {
            result = ((result % MOD) + (possibilities % MOD)) % MOD;
        }
        
        memo.put(key, result);
        return result;
    }
    public int threeSumMulti(int[] arr, int target) {
        Arrays.sort(arr);
        // sorting and going in reverse so we subtract big numbers from sum fast and need not do recursion deep
        return isPossible(arr.length - 1, arr, 0, target);
    }
}
