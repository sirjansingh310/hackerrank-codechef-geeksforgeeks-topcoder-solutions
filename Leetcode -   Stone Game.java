// https://leetcode.com/explore/challenge/card/august-leetcoding-challenge-2021/613/week-1-august-1st-august-7th/3870/


// Mathematical reasoning: o(1) alex wil always win the game !!!!!!!!!. There are even number of piles and total stones in all piles is odd, and alex is at advantage
// by starting first.
// Alex is first to pick pile.
// piles.length is even, and this lead to an interesting fact:
// Alex can always pick odd indexed piles or always pick even indexed piles!

// For example,
// If Alex wants to pick even indexed piles piles[0], piles[2], ....., piles[n-2],
// he picks first piles[0], then Lee can pick either piles[1] or piles[n - 1].
// Every turn, Alex can always pick even indexed piles and Lee can only pick odd indexed piles.

// In the description, we know that sum(piles) is odd.
// If sum(piles[even]) > sum(piles[odd]), Alex just picks all evens and wins.
// If sum(piles[even]) < sum(piles[odd]), Alex just picks all odds and wins.

// So, Alex always defeats Lee in this game.

// Programming approach, my solution
// Both play optimally and the total stones are odd in number so there is never a tie.
// We find the best possible alex score, if that is more than 50% of all score, that means alex will win. otherwise lee will win 
// Time complexity: O(N^2) We reach combination of start and end positions. We memoize it to keep it O(N^2)
// This solution is true solution if the constraints given in problem don't exist. Like odd number of piles. 
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
