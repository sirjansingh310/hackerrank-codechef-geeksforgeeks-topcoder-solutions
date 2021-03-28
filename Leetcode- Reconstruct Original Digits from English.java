// Tricky problem. Need to create this digit array in smart fashion to do it in time, otherwise TLE.
//Reconstruct Original Digits from English
//https://leetcode.com/explore/challenge/card/march-leetcoding-challenge-2021/591/week-4-march-22nd-march-28th/3687/
class Solution {
	// Time O(n) || Space O(1), n = len of 's'
    public String originalDigits(String s) {
        int[] charSet = new int[26];
        // fetch all the alphabets and their frequencies in 's'
        for (int i = 0; i < s.length(); i++)
            charSet[s.charAt(i) - 'a']++;

        int[] digit = new int[10];
        digit[0] = charSet['z' - 'a'];     // only zero contains letter 'z'
        digit[2] = charSet['w' - 'a'];     // only two contains letter 'w'
        digit[6] = charSet['x' - 'a'];     // only six contains letter 'x'
        digit[8] = charSet['g' - 'a'];     // only eight contains letter 'g'
        digit[4] = charSet['u' - 'a'];     // only four contains letter 'u'
        digit[5] = charSet['f' - 'a'] - digit[4]; // only five & four contains letter 'f', we already know no of 4s hence subtracting that from no of 'f' gives us 5s
        digit[7] = charSet['v' - 'a'] - digit[5]; // only five & seven contains letter 'v', we already know no of 5s hence subtracting that from no of 'v' gives us 7s
        digit[3] = charSet['r' - 'a'] - digit[4] - digit[0];  // only four, zero & three contains letter 'r', we already know no of 4s & os hence subtracting that from no of 'r' gives us 3s
        digit[9] = charSet['i' - 'a'] - digit[8] - digit[6] - digit[5];
        digit[1] = charSet['o' - 'a'] - digit[4] - digit[2] - digit[0];


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digit.length; i++)
            while (digit[i]-- > 0)  // append count of each individual digit
                sb.append(i);

        return sb.toString();
    }
}
