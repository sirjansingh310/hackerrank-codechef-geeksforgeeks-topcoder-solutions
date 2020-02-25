import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
///////https://www.hackerrank.com/challenges/short-palindrome/problem
public class Solution {

    // Complete the shortPalindrome function below.
    static int shortPalindrome(String s) {
        //DP Solution
        int mod = 1000000007;
        int freq[] = new int[26]; // how many times current letter was seen
        int pair[][] = new int[26][26];// how many times i appeared before j
        int triplet[] = new int[26];// how many times j was followed by a pair, for all j in 1 to 26. example triplet[a] = aaa, abb, acc... azz 

        int totalCount = 0;
        for(int ch = 0; ch < s.length(); ch++){
            int index = s.charAt(ch) - 'a';
            totalCount += triplet[index]; // if current letter is a, triplet[a] will 
            // be the total number of short palindromes of type axxa(as triplet[a] tells how many axx were already seen)

            totalCount %= mod;

            for(int i = 0; i < 26; i++){
                triplet[i] += pair[i][index];
                triplet[i] %= mod;
                // for all letters, update triplet of i. 
                // pair[i][index] tells how many pairs of i before index already 
                // occured. now if we append s[index] again, making it iIndexIndex. 
                // It has middle chars same, for short palindrome

                // we haven't updated pair array so how many pairs the current index 
                // is contributing to, doesn't count. Thats why we update triplet first and the the pair array
            }

            for(int i = 0; i < 26; i++){
                pair[i][index] += freq[i];
                pair[i][index] %= mod;
                // how many times i occured before index is simply number of times
                // i has been seen before
            }
            freq[index]++; // finally update freq, mark it as seen 
            freq[index] %= mod;
        }
        return totalCount;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        int result = shortPalindrome(s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
