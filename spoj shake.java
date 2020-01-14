import java.util.*;
// https://www.spoj.com/problems/MAIN8_C/
/*
Here is the approach:

1.Sort the array in ascending order.

2.Run the binary search and Let say mid element is x.

3.Now make a check whether that mid element fulfills your demand according to a number of children.

4.If condition satisfies, then move b/w mid and high else move b/w low and mid.

*/
public class Shake{
    public static boolean possible(int mid, int arr[], int n, long k){
        for(int i = n - 1; i >=0; i--){
            k -= (arr[i]/mid);
        }
        if(k <= 0)
            return true;
        return false;
    }
    public static int solve(int n, long k, int arr[]){
        Arrays.sort(arr);
        int low = 1, high = arr[n - 1] + 1, res = 0;
        while(low <= high){
            int mid = (low + high) / 2;
            if(possible(mid, arr, n, k)){ // possible to give every kid with mid number of candies
                res = mid;
                low = mid + 1;
            }
            else{
                high = mid - 1;
            }
            
        }
        return res;

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
            long k = sc.nextInt();
            int arr[] = new int[n];
            for(int i = 0; i < n; i++){
                arr[i] = sc.nextInt();
            }
            System.out.println(solve(n, k, arr));
        }
    }
}
