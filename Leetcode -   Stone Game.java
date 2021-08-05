// https://leetcode.com/explore/challenge/card/august-leetcoding-challenge-2021/613/week-1-august-1st-august-7th/3870/
// Both play optimally and the total stones are odd in number so there is never a tie.
// We find the best possible alex score, if that is more than 50% of all score, that means alex will win. otherwise bob will win 
class Solution {
    private Map<String, Integer> map = new HashMap<>();
    
    private int recur(int currentAlexScore, int start, int end, int[] piles, boolean alexTurn) {
        String key = start + "_" + end;
        if (map.containsKey(key)) {
            return map.get(key);
        }
        if (start == end) {
            if (alexTurn) {
                map.put(key, currentAlexScore + piles[start]);
            } else {
                map.put(key, currentAlexScore);
            }
            return map.get(key);
        }
        if (!alexTurn) {
            int fromLeft = recur(currentAlexScore, start + 1, end, piles, true);
            int fromRight = recur(currentAlexScore, start, end - 1, piles, true);
            map.put(key, Math.max(fromLeft, fromRight));
        } else {
            int fromLeft = recur(currentAlexScore + piles[start], start + 1, end, piles, false);
            int fromRight = recur(currentAlexScore + piles[end], start, end - 1, piles, false);
            map.put(key, Math.max(fromLeft, fromRight));
        }
        
        return map.get(key);
    }
    
    public boolean stoneGame(int[] piles) {
        int sum = 0;
        for (int i = 0; i < piles.length; i++) {
            sum += piles[i];
        }
        int finalAlexOptimalScore = recur(0, 0, piles.length - 1, piles, true);
        return finalAlexOptimalScore > sum / 2;
    }
}
