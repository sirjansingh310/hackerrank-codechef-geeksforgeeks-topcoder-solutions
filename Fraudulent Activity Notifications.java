import java.util.*;
/// https://www.hackerrank.com/challenges/fraudulent-activity-notifications/problem
public class MainClass{
    public static int freq[] = new int[201];
    public static double findMedian(List<Integer> list, int d){
        // counting sort
        int tempFreq[] = new int[201];
        tempFreq[0] = freq[0];
        for(int i = 1; i < 201; i++){
            tempFreq[i] = freq[i];
            tempFreq[i] += tempFreq[i - 1];
        }
        // int sortedList[] = new int[d];
        
        // for(int i : list){
        //     sortedList[tempFreq[i] - 1] = i;
        //     tempFreq[i]--;
        // }
        // if(d % 2 == 1){
        //     return sortedList[d / 2];
        // }
        // return (double)(sortedList[(d / 2) - 1] + sortedList[d / 2]) / 2;

        // using countsort, temp freq has indexes in for list to be put in sortedList
        
    // the median will have index d / 2, return this directly from the tempfreq array
        double ans = 0;
        if(d % 2 == 1){
            for(int i = 0; i < 201; i++){
                if(tempFreq[i] >= d / 2 + 1 ){// coz tempFreq will be one more than index. tempFreq = 1, index = 0. And >= because we may not find exact d / 2 + 1, but a number greater than that will go in position d / 2 only.
                    ans = i;
                    break;
                }
            }
        }
        else{
            int first = 0, second = 0;
            int i = 0;
            for(; i < 201; i++){
                if(tempFreq[i] >= d / 2){
                    second = i;
                    break;
                }
            }
            for(; i < 201; i++){
                if(tempFreq[i] >= d / 2 + 1){
                    first = i;
                    break;
                }
            }
            ans = (double)(first + second) / 2.0;
        }
        return ans;
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int d = sc.nextInt();
        int arr[] = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
            
        }
        int prevIndex = 0;
        LinkedList<Integer> queue = new LinkedList<>();
        for(int i = 0; i < d; i++){
            queue.add(arr[i]);
            freq[arr[i]]++;
        }
        int fraudulent = 0;
        for(int i = d; i < n; i++){
            if(arr[i] >= (int)(2 * findMedian(queue, d))){
                fraudulent++;
               
            }
             freq[arr[prevIndex]]--;
             freq[arr[i]]++;
             queue.poll();
             queue.add(arr[i]);
             prevIndex++;
        }
        
        System.out.println(fraudulent);

    }
}
