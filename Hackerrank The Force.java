// https://www.hackerrank.com/contests/convergence-2023-coding-contest-round-2/challenges/the-force-1/problem
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        
        while (t-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }
            List<Integer> diff = new ArrayList<>();
            
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    diff.add(arr[j] - arr[i]);
                }
            }
            
            Collections.sort(diff);
            System.out.println(binarySearchFromPossibilities(diff, arr, m));
        }
    }
    
    private static int binarySearchFromPossibilities(List<Integer> possibleAnsList, int[] arr, int targetSize) {
        int low = 0, high = possibleAnsList.size() - 1;
        int result = possibleAnsList.get(0);
        
        while (low <= high) {
            int mid = (low + high) / 2;
            if (canCreateSubseq(arr, targetSize, possibleAnsList.get(mid))) {
                result = possibleAnsList.get(mid);
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        
        return result;
    }
    
    
    private static boolean canCreateSubseq(int[] arr, int m, int minDiff) {
        m--;
        int last = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - last >= minDiff) {
                m--;
                last = arr[i];
            }
        }
        
        
        return m <= 0;
    }
}
