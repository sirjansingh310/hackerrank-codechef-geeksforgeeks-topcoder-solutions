import java.util.*;
//https://www.hackerrank.com/challenges/abbr/problem
public class MainClass{
    public static HashMap<String, Boolean> mem;
    public static boolean solve(String a, String b, int aPtr, int bPtr){
        

        a = a.substring(0, aPtr);
        b = b.substring(0, bPtr);
        String currentKey = aPtr + "_" + bPtr; // key for memo
        if(mem.get(currentKey) != null)
            return mem.get(currentKey);
       
        if(b.equals("") && !a.equals("")){ // if b is empty and a contains only small letters, return true else return false
            boolean isALower = true;
            for(int i = 0; i < a.length(); i++){
                if(a.charAt(i) >= 'A' && a.charAt(i) <= 'Z'){
                    isALower = false;
                    break;
                }
            }
            mem.put(currentKey, isALower);
            return isALower;
        }
        else if(a.equals("") && !b.equals("")){ /// if a is empty and b is not empty, return false
            mem.put(currentKey, false);
            return false;
        }
        else if(a.equals("") && b.equals("")){
            mem.put(currentKey, true); // if both are empty, return true
            return true;
        }
        else if(a.length() == 1 && b.length() == 1){
             mem.put(currentKey, (a.charAt(0) + "").toUpperCase().equals(b.charAt(0) + "")); // if length is 1, check if they are same characters or not
            return mem.get(currentKey);
        }

        // general cases: 
        if(a.charAt(aPtr - 1) == b.charAt(bPtr - 1)){ // if both are upper cases and are equal to each other
            mem.put(currentKey, solve(a, b, aPtr - 1, bPtr - 1));
        }
        else if(a.charAt(aPtr - 1) >= 'A' && a.charAt(aPtr - 1) <= 'Z'){// if both are upper cases and not equal to each other
            mem.put(currentKey, false);
            return false;
        }
        else { 
            if(a.charAt(aPtr - 1) - 32 == b.charAt(bPtr - 1))
                mem.put(currentKey ,solve(a, b, aPtr - 1, bPtr - 1) || solve(a, b, aPtr - 1, bPtr)); // if a[aPtr - 1] is small but equal to b[bPtr - 1], we can either capitalize it or leave it
            else // small but not equal, should be anyways discarded
                mem.put(currentKey, solve(a, b, aPtr - 1, bPtr));
        }

        return mem.get(currentKey);
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        sc.nextLine();
        while(t-- > 0){
            String a = sc.next();
            String b = sc.next();
            mem = new HashMap<>();
            System.out.println(solve(a, b, a.length(), b.length()) ? "YES": "NO");
       
        }
    }
}
