class Solution {
    public int longestStrChain(String[] words) {
        int[] dp = new int[words.length];// if word[i] is source of the chain, dp[i] will store the length of the longest possible chain.
        int best = 1;
        
        Arrays.sort(words, (w1, w2) -> w1.length() - w2.length()); 
        Arrays.fill(dp, 1);
        for (int i = words.length - 2; i >=0 ; i--) {
            for (int j = i + 1; j < words.length; j++) {                
                if (isAdj(words[i], words[j])) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            best = Math.max(best, dp[i]);
        }
        return best;
    }
    
    private boolean isAdj(String a, String b) {
        if (a.length() + 1 == b.length()) {
            int mismatch = 0, i = 0, j = 0;
            
            while (i < a.length() && j < b.length()) {
                if (a.charAt(i) != b.charAt(j)) {
                    mismatch++;
                    j++;
                    continue;
                }
                i++;
                j++;
            }
            return mismatch <= 1;
        } 
        return false;
    }
}
