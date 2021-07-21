// https://leetcode.com/explore/challenge/card/july-leetcoding-challenge-2021/610/week-3-july-15th-july-21st/3821/
/*
we divide dots (straight dominoes) into groups, which have boundaries L R or nothing(index 0 or index n - 1 is a dot)
for each of these local groups, we see the context obj, which has all the info of the group and determine the final state
*/
class DominoeContext { // a local group of dots
    int count, startIndex, endIndex;
    boolean surroundedByFallingLeft, surroundedByFallingRight; // true right dominoe on the left side and left dominoe on the right side
    
    
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
        List<DominoeContext> straightDominoesContexts = new ArrayList<>();
        
        DominoeContext currentContext = null;
        for (int i = 0; i < dominoes.length(); i++) {
            char dominoe = dominoes.charAt(i);
    
            if (currentContext != null && dominoe != '.') {
                currentContext.endIndex = i - 1;
                straightDominoesContexts.add(currentContext);
                
                if (dominoe == 'L') {
                    currentContext.surroundedByFallingLeft = true;
                }
                currentContext = null;
            }
            
            if (currentContext == null && dominoe != '.' && i + 1 < dominoes.length() && dominoes.charAt(i + 1) == '.') {
                currentContext = new DominoeContext();
                currentContext.startIndex = i + 1;
                
                if (dominoe == 'R') {
                    currentContext.surroundedByFallingRight = true;
                }
            }
            
            if (dominoe == '.') {
                if (currentContext == null) {
                    currentContext = new DominoeContext();
                    currentContext.startIndex = i;
                }
                currentContext.count++;
            }
            
        }
        
        if (currentContext != null) {
            currentContext.endIndex = dominoes.length() - 1;
            straightDominoesContexts.add(currentContext);
        }
        
        char[] arr = dominoes.toCharArray();
        convertDominoesToFinalState(arr, straightDominoesContexts);
        return new String(arr);
    }
    
    private void convertDominoesToFinalState(final char[] arr, final List<DominoeContext> straightDominoesContexts) {
        
        for (DominoeContext context : straightDominoesContexts) {
            if (context.surroundedByFallingLeft && context.surroundedByFallingRight) {
                for (int i = context.startIndex, filled = 0; i <= context.endIndex; i++, filled++) {
                    if (context.count % 2 != 0 && filled == context.count / 2) {
                        continue;/// (RR...LL => RRR.LL odd number of count, middle one balances out)
                    }
                    if (filled >= context.count / 2) {
                        arr[i] = 'L';
                    } else {
                        arr[i] = 'R';
                    }
                }
            } else {
                char toFill = '.'; // default
                if (context.surroundedByFallingLeft) {
                    toFill = 'L';
                } else if (context.surroundedByFallingRight) {
                    toFill = 'R';
                }
                
                for (int i = context.startIndex; i <= context.endIndex; i++) {
                    arr[i] = toFill;
                }
            }
        }
    }
}Le
