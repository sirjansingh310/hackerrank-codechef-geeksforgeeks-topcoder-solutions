// https://www.hackerrank.com/challenges/special-palindrome-again/problem

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Letter{
    char character;
    int count;
    Letter(char character, int count){
        this.character = character;
        this.count = count;
    }
    public String toString(){
        return character + " " + Integer.toString(count);
    }
}
public class Solution {
    // Complete the substrCount function below.
    static long substrCount(int n, String s) {
        char currentChar = s.charAt(0);
        char arr[] = s.toCharArray();
        int count = 0;
        ArrayList<Letter> list = new ArrayList<>();
        for(char c: arr){
            if(c == currentChar){
                count++;
            }
            else{
                list.add(new Letter(currentChar,count));
                currentChar = c;
                count = 1;
            }
        }
        list.add(new Letter(currentChar,count));
        System.out.println(list);
        long ans = 0;
        for(Letter l: list){
            ans += (l.count * (l.count + 1))/2;
        } /// all combinations of continues characters. eg aaa - > 3*4/2 = 6 substrings 
         
        // handle case in which middle char is diff and all others are same
        // if list contains [aaa,b,aaaaa], then aaabaaa, aabaa, aba .. are possible strings.
        // just increase the count with min(list[i-1],list[i+1]) if list[i] == 1

        for(int i = 1; i < list.size() - 1; i++){
            if(list.get(i).count == 1){
                if(list.get(i-1).character == list.get(i+1).character){
                    ans += Math.min(list.get(i-1).count, list.get(i+1).count);
                }
            }
        }
        return ans;

 

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String s = scanner.nextLine();

        long result = substrCount(n, s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
