/// https://leetcode.com/explore/challenge/card/february-leetcoding-challenge-2021/586/week-3-february-15th-february-21st/3642/
class Solution {
    private final Set<String> all = new HashSet<>();
    private void recur(String leftString, int index, String rightString, String original) {
        if (index >= original.length()) {
            return;
        }
        String currentUpperCase = "", currentLowerCase = "";
        if (leftString.equals("")) {
            currentLowerCase = original;
            currentUpperCase = ("" + original.charAt(index)).toUpperCase() + rightString;            
        } else if (rightString.equals("")) {
            currentLowerCase = leftString + original.charAt(index);
            currentUpperCase = leftString + ("" + original.charAt(index)).toUpperCase();
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(leftString);
            sb.append(original.charAt(index));
            sb.append(rightString);
            currentLowerCase = sb.toString();
            
            sb = new StringBuilder();
            sb.append(leftString);
            sb.append(("" +original.charAt(index)).toUpperCase());
            sb.append(rightString);
            currentUpperCase = sb.toString();
        }
        
        all.add(currentLowerCase);
        all.add(currentUpperCase);
        
        leftString = currentLowerCase.substring(0, index + 1);
        if (index + 2 < original.length()) {
            rightString = currentLowerCase.substring(index + 2, original.length());
        } else {
            rightString = "";
        }
        recur(leftString, index + 1, rightString, original);
        
        leftString = currentUpperCase.substring(0, index + 1);
        if (index + 2 < original.length()) {
            rightString = currentUpperCase.substring(index + 2, original.length());
        } else {
            rightString = "";
        }
        
        recur(leftString, index + 1, rightString, original);
        
            
                    
    }
    public List<String> letterCasePermutation(String S) {
        if (S == null || S.equals("")) {
            return Collections.emptyList();
        }
        S = S.toLowerCase();
        recur("", 0, S.substring(1, S.length()), S);
        return new ArrayList<String>(all);
    }
}
