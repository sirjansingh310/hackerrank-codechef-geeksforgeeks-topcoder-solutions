// https://leetcode.com/problems/dota2-senate/
class Solution {
    public static final String RADIANT = "Radiant";
    public static final String DIRE = "Dire";
    
    public String predictPartyVictory(String senate) {
        int direCount = 0, radiantCount = 0;
        Queue<Character> queue = new LinkedList<>();
        
        for (int i = 0; i < senate.length(); i++) {
            char c = senate.charAt(i);
            if (c == 'R') {
                radiantCount++;
            } else {
                direCount++;
            }
            queue.add(c);
        }
        
        int direKillPending = 0, radiantKillPending = 0;
        
        while (direCount > 0 && radiantCount > 0) {
            char current = queue.poll();
            // turn skipped and kicked out of senate queue
            if (current == 'R' && radiantKillPending > 0) {
                radiantKillPending--;
                radiantCount--;
                continue;
            }
            
            if (current == 'D' && direKillPending > 0) {
                direKillPending--;
                direCount--;
                continue;
            }
            
            if (current == 'D') {
                radiantKillPending++;
            } else {
                direKillPending++;
            } 
            queue.add(current);
        }
        
        return radiantCount == 0 ? DIRE : RADIANT;
    }
}
