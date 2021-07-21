// https://leetcode.com/explore/challenge/card/july-leetcoding-challenge-2021/610/week-3-july-15th-july-21st/3821/
/*
we divide dots (straight dominoes) into groups, which have boundaries L R or nothing(index 0 or index n - 1 is a dot)
for each of these local groups, we see the group obj, which has all the info of the group and determine the final state
*/
class DominoeGroup { // a local group of dots
    int count, startIndex, endIndex;
    boolean surroundedByFallingLeft, surroundedByFallingRight; // true if right dominoe on the left side and left dominoe on the right side
    
    
    @Override
    public String toString() {
        return "straight dominoe count within boundary = " + count 
            + " started at " + startIndex
            + " ends at " + endIndex
            + " surroundedByFallingRight = " + surroundedByFallingRight
            + " surroundedByFallingLeft = " + surroundedByFallingLeft + "\n";
    }
}

class Solution {
    public String pushDominoes(String dominoes) {
        List<DominoeGroup> straightDominoesGroups = new ArrayList<>();
        
        DominoeGroup currentGroup = null;
        for (int i = 0; i < dominoes.length(); i++) {
            char dominoe = dominoes.charAt(i);
    
            if (currentGroup != null && dominoe != '.') {
                currentGroup.endIndex = i - 1;
                straightDominoesGroups.add(currentGroup);
                
                if (dominoe == 'L') {
                    currentGroup.surroundedByFallingLeft = true;
                }
                currentGroup = null;
            }
            
            if (currentGroup == null && dominoe != '.' && i + 1 < dominoes.length() && dominoes.charAt(i + 1) == '.') {
                currentGroup = new DominoeGroup();
                currentGroup.startIndex = i + 1;
                
                if (dominoe == 'R') {
                    currentGroup.surroundedByFallingRight = true;
                }
            }
            
            if (dominoe == '.') {
                if (currentGroup == null) {
                    currentGroup = new DominoeGroup();
                    currentGroup.startIndex = i;
                }
                currentGroup.count++;
            }
            
        }
        
        if (currentGroup != null) {
            currentGroup.endIndex = dominoes.length() - 1;
            straightDominoesGroups.add(currentGroup);
        }
        
        char[] arr = dominoes.toCharArray();
        convertDominoesToFinalState(arr, straightDominoesGroups);
        return new String(arr);
    }
    
    private void convertDominoesToFinalState(final char[] arr, final List<DominoeGroup> straightDominoesGroups) {
        
        for (DominoeGroup group : straightDominoesGroups) {
            if (group.surroundedByFallingLeft && group.surroundedByFallingRight) {
                for (int i = group.startIndex, filled = 0; i <= group.endIndex; i++, filled++) {
                    if (group.count % 2 != 0 && filled == group.count / 2) {
                        continue;/// (RR...LL => RRR.LL odd number of count, middle one balances out)
                    }
                    if (filled >= group.count / 2) {
                        arr[i] = 'L';
                    } else {
                        arr[i] = 'R';
                    }
                }
            } else {
                char toFill = '.'; // default
                if (group.surroundedByFallingLeft) {
                    toFill = 'L';
                } else if (group.surroundedByFallingRight) {
                    toFill = 'R';
                }
                
                for (int i = group.startIndex; i <= group.endIndex; i++) {
                    arr[i] = toFill;
                }
            }
        }
    }
}
