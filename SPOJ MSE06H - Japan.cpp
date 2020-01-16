// https://www.spoj.com/problems/MSE06H/

#include <bits/stdc++.h>
using namespace std;
class Edge{
    public:
        int from;
        int to;
};
bool sortingCriteria(Edge edge1, Edge edge2){
    if(edge1.from < edge2.from)
        return true;
    else if(edge1.from == edge2.from && edge1.to <= edge2.to)
        return true;
    return false;
}
long long int merge(int left[], int leftSize, int right[], int rightSize, int sorted[]){
        int i = 0, j = 0, k = 0;
        long long int count = 0;
        while(k < leftSize + rightSize){
            if(i == leftSize){
                sorted[k++] = right[j++];
            }
            else if(j == rightSize){
                sorted[k++] = left[i++];
            }
            else if(left[i] <= right[j]){
                sorted[k++] = left[i++];
            }
            else{
                sorted[k++] = right[j++];
                count += (leftSize - i);
            }
        }
        return count;
    }
long long int mergeSort(int original[], int low, int high, int sorted[]){
        if(high - low == 1){
            sorted[0] = original[low];
            return 0;
        }
        long long int count = 0;
        int mid = (low + high) / 2;
        int left[mid - low];
        int right[high - mid];
        count += mergeSort(original, low, mid, left);
        count += mergeSort(original, mid, high, right);
        count += merge(left, mid - low, right, high - mid, sorted);
        return count;
    }
int main() {
	int t;
	cin >> t;
	int tc = 1;
	while(t--){
	    int n, m, k;
	    cin >> n >> m >> k;
	    Edge edge[k];
	    for(int i = 0; i < k; i++){
	        int f,t;
	        cin >> f >> t;
	        edge[i].from = f;
	        edge[i].to = t;
	    }
	    
	    // sort edge array according to sortingCriteria
	    sort(edge, edge+k, sortingCriteria);
	    // count inversions in "to" array
	    int arr[k];
	    for(int i = 0; i < k; i++){
	        arr[i] = edge[i].to;
	    }
	    int sorted[k];
	    cout <<  "Test case " << tc << ": " << mergeSort(arr, 0, k, sorted)<< endl;
	}
	return 0;
}
