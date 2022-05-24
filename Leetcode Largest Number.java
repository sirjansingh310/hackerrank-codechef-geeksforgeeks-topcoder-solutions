// https://leetcode.com/problems/largest-number/

class Solution {
    public String largestNumber(int[] nums)  
    {   
        List<String> strings = Arrays.stream(nums).boxed().map(n -> Integer.toString(n))
            .collect(Collectors.toList());
                
        Collections.sort(strings, (s1, s2) -> {
            String b1 = s1 + s2;
            String b2 = s2 + s1;
            return b2.compareTo(b1);
        });
        
        String result =  strings.stream().collect(Collectors.joining(""));
        boolean allZero = true;
        for (char c : result.toCharArray()) {
            if (c != '0') {
                allZero = false;
                break;
            }
        }
        
        return allZero ? "0" : result;
    }
}
