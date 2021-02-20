class Solution {
    public int romanToInt(String s) {
        Map<String, Integer> special = new HashMap<>();
        special.put("IV", 4);
        special.put("IX", 9);
        special.put("XL", 40);
        special.put("XC", 90);
        special.put("CD", 400);
        special.put("CM", 900);
        
        Map<String, Integer> regular = new HashMap<>();
        regular.put("I", 1);
        regular.put("V", 5);
        regular.put("X", 10);
        regular.put("L", 50);
        regular.put("C", 100);
        regular.put("D", 500);
        regular.put("M", 1000);
        
        int intValue = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i + 1 < s.length() && special.containsKey("" + s.charAt(i) + s.charAt(i + 1))) {
                intValue += special.get("" + s.charAt(i) + s.charAt(i + 1));
                i++;
            } else {
                intValue += regular.get("" + s.charAt(i));
            }
        }
        return intValue;
    }
}
