import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
///////////// https://www.hackerrank.com/challenges/maximum-palindromes/problem
public class MainClass{
    public static int freq[][];
    public static long fact[] = new long[100001];
    public static int MOD = 1000000007;
    public static HashMap<Long, Long> inverse = new HashMap<>();

    // INVERSE USING FERMAT LITTLE THEOREM
    public static long findInverse(long n){
        if(inverse.get(n) != null)
            return inverse.get(n);
        // BigInteger num = BigInteger.valueOf(n);
        // BigInteger mod = BigInteger.valueOf(MOD);
        // inverse.put(n, num.modInverse(mod).longValue());
        // return inverse.get(n);

        long inv = fastPow(n, MOD - 2);
        inverse.put(n, inv);
        return inv;
        
    }

    public static long fastPow(long a, long b){
        long ans = 1L;

        while(b > 0){
            if(b % 2 == 1)
                ans = (ans * a) % MOD;
            a = (a * a) % MOD;
            b /=  2;
        }
        return ans % MOD;
    }
    public static void generateFact(){
        fact[0] = 1L;
        for(int i = 1; i <= 100000; i++){
            fact[i] = ((fact[i - 1] % MOD) * (i % MOD)) % MOD;
        }
    }
    public static void generateFreq(String s){
        for(int i = 1; i <= s.length(); i++){
            for(int j = 0; j < 26; j++){
                freq[i][j] = freq[i - 1][j];
            }
            freq[i][s.charAt(i - 1) - 'a']++;
        }
    }
    public static int getMaxPalindrome(String s, int l, int r){
        int middlePossibility = 0;
        long ans = 0L;
        int leftLength = 0;
        ArrayList<Integer> toDivide = new ArrayList<>();
        char ch[] = s.toCharArray();
        for(int i = 0; i < 26; i++){
            int freqInRange = freq[r][i] - freq[l - 1][i];
            if(freqInRange == 0)
                continue;
            if(freqInRange % 2 == 0){
                freqInRange /= 2;
                leftLength += freqInRange;
                toDivide.add(freqInRange); 
            }
            else if(freqInRange != 1 && freqInRange % 2 != 0){
                middlePossibility++;
                freqInRange /= 2;
                leftLength += freqInRange;
                toDivide.add(freqInRange);
            }
            else{
                middlePossibility++;
            }
      }

      ans = fact[leftLength];
      for(int i : toDivide){
           //ans /= fact[i];
           ans = ((ans % MOD) * (findInverse(fact[i]) % MOD)) % MOD;
      }
      if(middlePossibility > 0)
        ans = ((ans % MOD)* (middlePossibility % MOD)) % MOD;
      return (int)ans;
    }
  
       public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();


        int q = Integer.parseInt(bufferedReader.readLine().trim());
        freq = new int[s.length() + 1][26];
        generateFreq(s);
        generateFact();
        IntStream.range(0, q).forEach(qItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int l = Integer.parseInt(firstMultipleInput[0]);

                int r = Integer.parseInt(firstMultipleInput[1]);

                int result = getMaxPalindrome(s, l, r);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
    
}
