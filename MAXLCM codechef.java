import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
/// https://www.youtube.com/watch?v=yAHlg6mdsks
///https://www.codechef.com/problems/MXMLCM
class MAXLCM{
    public static boolean isPrime[] = new boolean[10001];
    public static ArrayList<HashMap<Integer, Integer>> primeFactorsList = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        preComputePrimes();
        preComputePrimeFactors();
        while(t-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int arr[] = new int[n];
            boolean isPresent[] = new boolean[m + 1];

            for(int i = 0; i < n; i++){
                arr[i] = Integer.parseInt(st.nextToken());
                isPresent[arr[i]] = true;
            }
            HashMap<Integer, Integer> bestPowers = new HashMap<>();
            // for a list of numbers, the lcm is product of 2*mostocc2 * 3*mostocc3... for all primes in limit m
            // mostofcc is highest number of occurrences of a prime number after prime factorization of all numbers in the list

            for(int i = 2; i <= m; i++){
                if(isPrime[i]){
                    bestPowers.put(i, 0);
                }
            }

            for(int i = 0; i < n; i++){
                HashMap<Integer, Integer> pf = primeFactorsList.get(arr[i] - 1);
                for(Map.Entry<Integer, Integer> entry : pf.entrySet()){
                    if(entry.getValue() > bestPowers.get(entry.getKey())){
                        bestPowers.put(entry.getKey(), entry.getValue());
                    }
                }
            }
            // product of all elements in bestPowers map is the current lcm

            int max = -1, ans = -1;

            for(int i = 1; i <=m; i++){
                HashMap<Integer, Integer> pf = primeFactorsList.get(i - 1);
                int pro = 1; // factor by which lcm will increase after adding i
                for(Map.Entry<Integer, Integer> entry: pf.entrySet()){
                    if(entry.getValue() > bestPowers.get(entry.getKey())){
                        pro *= fastPow(entry.getKey(), entry.getValue() - bestPowers.get(entry.getKey()));
                    }
                }
                if(pro > max){
                    max = pro;
                    ans = i;
                }

            }

            System.out.println(ans);
        }
    }

    private static int fastPow(int base, int power) {
        int result = 1;
        while(power > 0){
            if(power % 2 == 1){
               result = result * base;
            }
            base = base * base;
            power /= 2;
        }
        return result;
    }

    private static void preComputePrimeFactors() {
        for(int i = 1; i <= 10000; i++){
            primeFactorsList.add(getPrimeFactors(i));
        }
    }

    private static HashMap<Integer, Integer> getPrimeFactors(int n) {
        HashMap<Integer, Integer> pf = new HashMap<>();
        int count = 0;
        while(n % 2 == 0){
            count++;
            n /= 2;
        }
        if(count > 0)
            pf.put(2, count);


        for(int i = 3; i <= Math.sqrt(n); i += 2){
            count = 0;
            while(n % i == 0){
                count++;
                n /= i;
            }
            if(count > 0)
                pf.put(i, count);
        }

        if(n > 2){ // n is a prime number itself
            pf.put(n, 1);
        }
        return pf;
    }

    private static void preComputePrimes() {
        for(int i = 0; i < isPrime.length; i++){
            isPrime[i] = true;
        }
        int n = 10000;
        for(int p = 2; p * p <= n; p++){
            if(isPrime[p]){
                for(int i = p * p; i <= n; i += p){
                    isPrime[i] = false;
                }
            }
        }
    }
}
