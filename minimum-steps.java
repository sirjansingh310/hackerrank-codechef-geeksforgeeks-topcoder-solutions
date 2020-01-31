//https://www.hackerearth.com/practice/algorithms/dynamic-programming/introduction-to-dynamic-programming-1/practice-problems/algorithm/yogi-and-his-steps-65b27a4b/description/

// can be solved in O(nlogn) if we use binary search to find LIS
import java.util.*;
class MainClass{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        for(int i = 0; i < n; i++) arr[i] = sc.nextInt();
        /*
        Find the longest increasing subsquence. Then substract with n. Those are the numbers which 
        have to be moved to be in the LIS. Movement of those won't affect the numbers 
        already in the LIS. Do the same for Longest decreasing subsequence and return the minimum
        */
        
        int lis[] = new int[n];
        Arrays.fill(lis, 1);
        int lisLength = 0;
        for(int i = 1; i < n; i++){
            for(int j = 0 ; j < i ; j++){
                if(arr[i] > arr[j] && lis[i] < lis[j] + 1){
                    lis[i] = lis[j] + 1;
                    lisLength = Math.max(lisLength, lis[i]);
                }
            }
        }
        int lds[] = new int[n];
        int ldsLength = 0;
        Arrays.fill(lds, 1);
        for(int i = n - 2 ; i >=0 ; i--){
            for(int j = n - 1; j >=0; j--){
                if(arr[i] > arr[j] && lds[i] < lds[j] + 1){
                    lds[i] = lds[j] + 1;
                    ldsLength = Math.max(ldsLength, lds[i]);
                }
            }
        }
        System.out.println(Math.min(n - lisLength, n - ldsLength));
    }
}
