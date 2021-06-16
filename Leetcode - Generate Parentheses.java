// https://leetcode.com/explore/challenge/card/june-leetcoding-challenge-2021/605/week-3-june-15th-june-21st/3781/
class Solution {
    private Set<String> set = new HashSet<>();
    
    private void recur(int index, int length, int upDownCount, String s) {
        if (index > length) {
            return;
        }
        
        if (index == length && upDownCount == 0) {
            set.add(s);
        }
        
        recur(index + 1, length, upDownCount + 1, s + "(");
        // to avoid )()(
        if (upDownCount > 0) {
             recur(index + 1, length, upDownCount - 1, s + ")");
        }
       
    }
    
    
    public List<String> generateParenthesis(int n) {
        // create vaid parenthesis with string length = n * 2
        recur(0, n * 2, 0, "");
        return new ArrayList<>(set);
    }
}


// Faster solution
// Instead of keeping track of opening - closing brackets (upDownCount), we can simply track number of opening, closing.
// This will keep it  more strict to call recursion. More validations ahead of time checked before calling recur again.


class Solution {
    private Set<String> set = new HashSet<>();

    private void recur(int index, int length, String s, int opening, int closing, int allowed) {
        if (index > length) {
            return;
        }

        if (index == length && opening == closing) {
            set.add(s);
        }

        if (opening < allowed) {
            recur(index + 1, length, s + "(", opening + 1, closing, allowed);
        }
        // to avoid )()(
        if (closing < opening) {
             recur(index + 1, length, s + ")", opening, closing + 1, allowed);
        }

    }


    public List<String> generateParenthesis(int n) {
        // create vaid parenthesis with string length = n * 2
        recur(0, n * 2, "", 0, 0, n);
        return new ArrayList<>(set);
    }
}
