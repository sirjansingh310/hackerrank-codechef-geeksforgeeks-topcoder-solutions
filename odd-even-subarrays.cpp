//https://www.hackerearth.com/practice/algorithms/dynamic-programming/introduction-to-dynamic-programming-1/practice-problems/algorithm/odd-even-subarrays-72ad69db/
#include<bits/stdc++.h>
using namespace std;
int main(){
    int n;
    cin >> n;
    int arr[n];
    for(int i = 0; i < n; i++) cin >> arr[i];
    int difference = 0; // diff between number of odd and even numbers
    int freq_neg[n + 1];// freq of number of times the exact difference was seen in the loop for dif > 0
    int freq_pos[n + 1]; // for dif < 0. size n + 1 because all n numbers can be odd and we need to store freq[n]
    // the idea is, whenever the difference is seen again, say at index 2 the difference was -1 and at index 7 the difference was again 
    // -1, that means from index 2 to index 7, the number of odds and even is same, i.e it is a good subarray
    
    // loop once and keep adding sum with freq[difference], be it neg or pos. if difference isn't seen, default value of zero will be added
    for(int i = 0; i < n + 1; i++){
        freq_pos[i] = 0;
        freq_neg[i] = 0;
    }
    long long int ans = 0L;
    freq_pos[0] = 1; //since the difference is initially 0, it is seen once so the freq is incremented
    for(int i = 0; i < n; i++){
        if(arr[i] % 2 == 1)
            difference++;
        else
            difference--;
        if(difference < 0){
            ans += freq_neg[-1 * difference];
            freq_neg[-1 * difference]++;
        }
        else{
            ans += freq_pos[difference];
            freq_pos[difference]++;
        }
    }
    cout << ans << endl;
    return 0;
}
