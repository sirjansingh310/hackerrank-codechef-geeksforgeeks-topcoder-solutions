///https://www.hackerearth.com/practice/data-structures/advanced-data-structures/segment-trees/practice-problems/algorithm/equal-subarrays-cf01a26a/description/
#include<bits/stdc++.h>
using namespace std;
int prevMaxIndex = -1;
int currentMax(int arr[], int start, int end, int prevMax){
    if(start - 1 != prevMaxIndex && arr[end] < prevMax){
        return prevMax;
    }
    int maxN = -1;
    for(int i = start; i <= end; i++){
        if(arr[i] >= maxN){
            maxN = arr[i];
            prevMaxIndex = i;
        }
    }
    return maxN;
}
bool isPossible(int arr[], int size, long long int k, int n){
    long long int currentSum = (long long int)0, requiredSum;
    int firstMax = -1, prevMax = -1;
    for(int i = 0; i < size; i++){
        currentSum += (long long int)arr[i];
        if(arr[i] >= firstMax){
            firstMax = arr[i];
            prevMaxIndex = i;
        }
    }
    requiredSum = (long long int)firstMax * size;
    prevMax = firstMax;
    if(requiredSum - currentSum <= k)
        return true;
    for(int i = size; i < n; i++){
        currentSum -= (long long int)arr[i - size];
        currentSum += (long long int)arr[i];
        long long int  cM = (long long int)currentMax(arr, i - size + 1, i, prevMax);
        requiredSum =  cM * size;
        prevMax = cM;
        if(requiredSum - currentSum <= k){
            return true;
        }
    }
    return false;
}
int binarySearch(int n, long long int k, int arr[]){
    int low = 0, high = n;
    int res = 1;
    while(low <= high){
        int mid = (low + high) / 2;
        if(isPossible(arr, mid, k, n)){
            res = mid;
            low = mid + 1;
        }
        else
            high = mid -1;
    }
    return res;
}
int main(){
    int n;
    long long int k;
    cin >> n;
    cin >> k;
    int arr[n];
    for(int i = 0; i < n; i++) cin >> arr[i];
    
    cout << binarySearch(n, k, arr) << endl;
    return 0;
}
