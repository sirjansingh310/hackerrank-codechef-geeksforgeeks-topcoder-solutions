// Problem Link: https://www.codechef.com/APRIL20B/problems/REBIT
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

class ReadyBitwise {
    public static HashMap<Character, HashMap<String, Long>> memo = new HashMap<>();
    public static final long mod = 998244353L;
    public static long fastPow(long base, long power){
        long result = 1L;
        while(power > 0){
            if(power % 2 != 0){
                result = ((result % mod) * (base % mod)) % mod;
            }
            base = ((base % mod) * (base % mod)) % mod;
            power /= 2;
        }
        return result % mod;
    }
    // when m is prime, we can find mod inv using this according to fermat's little theorem 
    public static long modInv(long n){
        return fastPow(n, mod - 2);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        memo.put('a', new HashMap<String, Long>());
        memo.put('A', new HashMap<String, Long>());
        memo.put('0', new HashMap<String, Long>());
        memo.put('1', new HashMap<String, Long>());

        memo.get('a').put("(#&#)", 3L);
        memo.get('A').put("(#&#)", 3L);
        memo.get('0').put("(#&#)", 9L);
        memo.get('1').put("(#&#)", 1L);
        memo.get('a').put("(#|#)", 3L);
        memo.get('A').put("(#|#)", 3L);
        memo.get('0').put("(#|#)", 1L);
        memo.get('1').put("(#|#)", 9L);
        memo.get('a').put("(#^#)", 4L);
        memo.get('A').put("(#^#)", 4L);
        memo.get('0').put("(#^#)", 4L);
        memo.get('1').put("(#^#)", 4L);
        for(Map.Entry<Character, HashMap<String, Long>> map: memo.entrySet()){
            HashMap<String, Long> temp = map.getValue();
            for(Map.Entry<String, Long> entry: temp.entrySet()){
                long newValue = ((entry.getValue() % mod) * modInv(16)) % mod;
                temp.put(entry.getKey(), newValue);
            }
        }
        while(t-- > 0){
            String input = br.readLine();
            System.out.print(evaluateExp(input, '0') + " ");
            System.out.print(evaluateExp(input, '1') + " ");
            System.out.print(evaluateExp(input, 'a') + " ");
            System.out.println(evaluateExp(input, 'A'));
        }
    }

    private static long evaluateExp(String input, char resultExpected) {
        if(input.equals("#"))
            return 748683265L;// 1 * 4^-1, probability is 25 % when expression is #
        if(memo.get(resultExpected).containsKey(input))
            return memo.get(resultExpected).get(input);
        HashMap<String, Long> tempMemo = memo.get(resultExpected);// memo for that char
        int bracketCount = 1;
        for(int i = 1; i < input.length(); i++){
            if(input.charAt(i) == '(')
                bracketCount++;
            else if(input.charAt(i) == ')')
                bracketCount--;
            if(bracketCount == 1){
                //evaluateExp(input.substring(1, i + 1), 'a');
                //evaluateExp(input.substring(i + 2, input.length() - 1),'a');
                char operator = input.charAt(i + 1);
                String leftExp = input.substring(1, i + 1);
                String rightExp = input.substring(i + 2, input.length() - 1);
                if(operator == '&'){
                    if(resultExpected == 'a'){
                        long first = ((evaluateExp(leftExp, '1') % mod) * (evaluateExp(rightExp, 'a') % mod) % mod);
                        long second = ((evaluateExp(leftExp, 'a') % mod) * (evaluateExp(rightExp, '1') % mod) % mod);
                        long third = ((evaluateExp(leftExp, 'a') % mod) * (evaluateExp(rightExp, 'a') % mod) % mod);
                        tempMemo.put(input, ((first % mod) + (second % mod) + (third % mod)) % mod);
                        break;
                    }
                    else if(resultExpected == 'A'){
                        long first = ((evaluateExp(leftExp, '1') % mod) * (evaluateExp(rightExp, 'A') % mod) % mod);
                        long second = ((evaluateExp(leftExp, 'A') % mod) * (evaluateExp(rightExp, '1') % mod) % mod);
                        long third = ((evaluateExp(leftExp, 'A') % mod) * (evaluateExp(rightExp, 'A') % mod) % mod);
                        tempMemo.put(input, ((first % mod) + (second % mod) + (third % mod)) % mod);
                        break;
                    }
                    else if(resultExpected == '0'){
                        long sum = 0L;
                        sum = ((sum % mod)+ ((evaluateExp(leftExp, '1') % mod) * (evaluateExp(rightExp, '0') % mod) % mod)) % mod;
                        sum = ((sum % mod)+ ((evaluateExp(leftExp, '0') % mod) * (evaluateExp(rightExp, '1') % mod) % mod)) % mod;
                        sum = ((sum % mod)+ ((evaluateExp(leftExp, '0') % mod) * (evaluateExp(rightExp, '0') % mod) % mod)) % mod;
                        sum = ((sum % mod)+ ((evaluateExp(leftExp, '0') % mod) * (evaluateExp(rightExp, 'a') % mod) % mod)) % mod;
                        sum = ((sum % mod)+ ((evaluateExp(leftExp, 'a') % mod) * (evaluateExp(rightExp, '0') % mod) % mod)) % mod;
                        sum = ((sum % mod)+ ((evaluateExp(leftExp, '0') % mod) * (evaluateExp(rightExp, 'A') % mod) % mod)) % mod;
                        sum = ((sum % mod)+ ((evaluateExp(leftExp, 'A') % mod) * (evaluateExp(rightExp, '0') % mod) % mod)) % mod;
                        sum = ((sum % mod)+ ((evaluateExp(leftExp, 'a') % mod) * (evaluateExp(rightExp, 'A') % mod) % mod)) % mod;
                        sum = ((sum % mod)+ ((evaluateExp(leftExp, 'A') % mod) * (evaluateExp(rightExp, 'a') % mod) % mod)) % mod;
                        tempMemo.put(input, sum % mod);
                        break;
                    }
                    else if(resultExpected == '1'){
                        tempMemo.put(input, ((evaluateExp(leftExp , '1') % mod) * (evaluateExp(rightExp, '1') % mod)) % mod);
                        break;
                    }
                }
                else if(operator == '|'){
                    if(resultExpected == 'a'){
                        long first = ((evaluateExp(leftExp, '0') % mod) * (evaluateExp(rightExp, 'a') % mod) % mod);
                        long second = ((evaluateExp(leftExp, 'a') % mod) * (evaluateExp(rightExp, '0') % mod) % mod);
                        long third = ((evaluateExp(leftExp, 'a') % mod) * (evaluateExp(rightExp, 'a') % mod) % mod);
                        tempMemo.put(input, ((first % mod) +( second % mod) + (third % mod)) % mod);
                        break;
                    }
                    else if(resultExpected == 'A'){
                        long first = ((evaluateExp(leftExp, '0') % mod) * (evaluateExp(rightExp, 'A') % mod) % mod);
                        long second = ((evaluateExp(leftExp, 'A') % mod) * (evaluateExp(rightExp, '0') % mod) % mod);
                        long third = ((evaluateExp(leftExp, 'A') % mod) * (evaluateExp(rightExp, 'A') % mod) % mod);
                        tempMemo.put(input, ((first % mod) + (second % mod) + (third % mod)) % mod);
                        break;
                    }
                    else if(resultExpected == '0'){
                        tempMemo.put(input, ((evaluateExp(leftExp, '0') % mod) * (evaluateExp(rightExp, '0') % mod)) % mod);
                        break;
                    }
                    else if(resultExpected == '1'){
                        long sum = 0L;
                        sum = ((sum % mod)+ ((evaluateExp(leftExp, '1') % mod) * (evaluateExp(rightExp, '0') % mod) % mod)) % mod;
                        sum = ((sum % mod)+ ((evaluateExp(leftExp, '0') % mod) * (evaluateExp(rightExp, '1') % mod) % mod)) % mod;
                        sum = ((sum % mod)+ ((evaluateExp(leftExp, '1') % mod) * (evaluateExp(rightExp, '1') % mod) % mod)) % mod;
                        sum = ((sum % mod)+ ((evaluateExp(leftExp, '1') % mod) * (evaluateExp(rightExp, 'a') % mod) % mod)) % mod;
                        sum = ((sum % mod)+ ((evaluateExp(leftExp, 'a') % mod) * (evaluateExp(rightExp, '1') % mod) % mod)) % mod;
                        sum = ((sum % mod)+ ((evaluateExp(leftExp, '1') % mod) * (evaluateExp(rightExp, 'A') % mod) % mod)) % mod;
                        sum = ((sum % mod)+ ((evaluateExp(leftExp, 'A') % mod) * (evaluateExp(rightExp, '1') % mod) % mod)) % mod;
                        sum = ((sum % mod)+ ((evaluateExp(leftExp, 'a') % mod) * (evaluateExp(rightExp, 'A') % mod) % mod)) % mod;
                        sum = ((sum % mod)+ ((evaluateExp(leftExp, 'A') % mod) * (evaluateExp(rightExp, 'a') % mod) % mod)) % mod;
                        tempMemo.put(input, sum % mod);
                        break;
                    }
                }
                else if(operator == '^'){
                    if(resultExpected == 'a'){
                        long sum = 0L;
                        sum = ((sum % mod) + ((evaluateExp(leftExp, '0') % mod) * (evaluateExp(rightExp, 'a') % mod) % mod) % mod);
                        sum = ((sum % mod) + ((evaluateExp(leftExp, 'a') % mod) * (evaluateExp(rightExp, '0') % mod) % mod) % mod);
                        sum = ((sum % mod) + ((evaluateExp(leftExp, '1') % mod) * (evaluateExp(rightExp, 'A') % mod) % mod) % mod);
                        sum = ((sum % mod) + ((evaluateExp(leftExp, 'A') % mod) * (evaluateExp(rightExp, '1') % mod) % mod) % mod);
                        tempMemo.put(input, sum % mod);
                        break;
                    }
                    else if(resultExpected == 'A'){
                        long sum = 0L;
                        sum = ((sum % mod) + ((evaluateExp(leftExp, '1') % mod) * (evaluateExp(rightExp, 'a') % mod) % mod) % mod);
                        sum = ((sum % mod) + ((evaluateExp(leftExp, 'a') % mod) * (evaluateExp(rightExp, '1') % mod) % mod) % mod);
                        sum = ((sum % mod) + ((evaluateExp(leftExp, '0') % mod) * (evaluateExp(rightExp, 'A') % mod) % mod) % mod);
                        sum = ((sum % mod) + ((evaluateExp(leftExp, 'A') % mod) * (evaluateExp(rightExp, '0') % mod) % mod) % mod);
                        tempMemo.put(input, sum % mod);
                        break;
                    }
                    else if(resultExpected == '1'){
                        long sum = 0L;
                        sum = ((sum % mod) + ((evaluateExp(leftExp, '1') % mod) * (evaluateExp(rightExp, '0') % mod) % mod) % mod);
                        sum = ((sum % mod) + ((evaluateExp(leftExp, '0') % mod) * (evaluateExp(rightExp, '1') % mod) % mod) % mod);
                        sum = ((sum % mod) + ((evaluateExp(leftExp, 'A') % mod) * (evaluateExp(rightExp, 'a') % mod) % mod) % mod);
                        sum = ((sum % mod) + ((evaluateExp(leftExp, 'a') % mod) * (evaluateExp(rightExp, 'A') % mod) % mod) % mod);
                        tempMemo.put(input, sum % mod);
                        break;
                    }
                    else if(resultExpected == '0'){
                        long sum = 0L;
                        sum = ((sum % mod) + ((evaluateExp(leftExp, '0') % mod) * (evaluateExp(rightExp, '0') % mod) % mod) % mod);
                        sum = ((sum % mod) + ((evaluateExp(leftExp, '1') % mod) * (evaluateExp(rightExp, '1') % mod) % mod) % mod);
                        sum = ((sum % mod) + ((evaluateExp(leftExp, 'a') % mod) * (evaluateExp(rightExp, 'a') % mod) % mod) % mod);
                        sum = ((sum % mod) + ((evaluateExp(leftExp, 'A') % mod) * (evaluateExp(rightExp, 'A') % mod) % mod) % mod);
                        tempMemo.put(input, sum % mod);
                        break;
                    }
                }

            }
        }
        return tempMemo.get(input);
    }
}
