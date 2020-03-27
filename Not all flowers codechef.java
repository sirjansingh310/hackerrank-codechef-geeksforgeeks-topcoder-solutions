import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// idea from https://www.youtube.com/watch?v=XFPHg5KjHoo
//https://www.codechef.com/problems/NOTALLFL
public class NotAllFlavors {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while(t-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int arr[] = new int[n];
            st = new StringTokenizer(br.readLine());

            for(int i = 0; i < n ;i++){
                arr[i] = Integer.parseInt(st.nextToken());

            }

            int maxLen = -1;
            int left = 0, right = 0;
            int currentLen = 0;
            int freq[] = new int[k + 1];
            freq[0] = 1;
            // sliding window approach

            while(right < n) {
                freq[arr[right]]++;
                while(left < right && isFreqK(freq, k)){
                    freq[arr[left]]--;
                    left++;
                }
                if(right - left > maxLen && isFreqNotK(freq, k)){
                    maxLen = right - left;
                }
                right++;
            }
            System.out.println(maxLen + 1);
        }
    }

    private static boolean isFreqK(int[] freq, int k) {
        //int count = 0;
        for(int i = 1; i <= k; i++){
            if(freq[i] == 0)
                return false;
        }
        return true;
    }

    private static boolean isFreqNotK(int[] freq, int k) {
        for(int i = 1; i <=k; i++){
            if(freq[i] == 0)
                return true;
        }
        return false;
    }
}
