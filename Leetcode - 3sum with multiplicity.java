
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



// Better solution using P and C
class Solution {
    private static final int MOD = 1000000007;
    public int threeSumMulti(int[] arr, int target) {
        int[] freq = new int[101];
        for (int i : arr) {
            freq[i]++;
        }
        
        // lets say we have 3 numbers x, y, z which make sum target
        // if x != y != z, freq(x) * freq(y) * freq(z) is total cases possibile with x, y, z
        
        // if x == y, y != z, then nc2 of (freq(x)) * freq(z). Simple P and C ( example we have 50 red balls, we can pick 2 balls in 50c2 ways)
        // if x == y == z. nc3 of (freq(x))
        
        long result = 0;
        for (int x = 0; x <= 100; x++) {
            for (int y = x + 1; y <= 100; y++) {
                int z = target - x - y;
                if (y < z && z <= 100) {
                    result += (freq[x] * freq[y] * freq[z]);
                    result %= MOD;
                }
            }
        }
        
        // x == y, y != z
        
        for (int x = 0; x <=100; x++) {
            int z = target - (2 * x);
            if (x < z && z <= 100) {
                result += (nc2(freq[x]) * freq[z]);
                result %= MOD;
            }
        }
        
        // x != y, y == z
        
        for (int x = 0; x <= 100; x++) {
            if (target % 2 == x % 2) {
                int z = (target - x) / 2;// target - x == 2 * y as y == z
                if (x < z && z <= 100) {
                    result += (freq[x] * nc2(freq[z]));
                }
            }
        }
        // x == y == z
        if (target % 3 == 0) {
            int x = target / 3;
            if (x >= 0 && x <= 100) {
                result += nc3(freq[x]);
                result %= MOD;
            }
        }
        
        return (int)result;
    }
    
    private long nc2(long n) {
        return n * (n - 1) / 2;
    }
    
    private long nc3(long n) {
        return n * (n - 1) * (n - 2) / 6;
    }
}
