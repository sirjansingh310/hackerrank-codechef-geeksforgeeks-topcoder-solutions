#include <bits/stdc++.h>
using namespace std;

bool valid(int n, map<char, int> freq){
    // the idea is to check if the remaining string( string - substring) has "lesser" chars
    // and substring has "extra" chars
    // we do freq[right]-- meaning we are increasing upper bound for our substring
    // we do freq[left]++, throwing that char back to remaining string, increasing lowerbound for left
    if(freq['A'] <= n / 4 && freq['C'] <= n/4 && freq['G'] <= n/4 && freq['T'] <= n / 4)
        return true;
    return false; 
}
int minimumSubstringLength(int n, string s){
    map<char, int> freq;
    for(int i = 0; i < n; i++){
        freq[s[i]]++;
    }
    int right = 0, left = 0, out = 999999;
    while(right < n - 1){
        freq[s[right]]--; // marking this as part of substring. right is upper bound
        right++;
        while(valid(n, freq)){ // when we find a upper bound right for valid substring,
                           // update lower bound left.
        out = min(out, right - left);
        freq[s[left]]++; // putting in rest of string
        left++;
        }
        // now left is set till valid freq. now update right, and once right is valid, left can be updated to find best right - left
    }

    return out;
}
int main(){
    int n;
    string gene;
    cin >> n >> gene;
    cout << minimumSubstringLength( n, gene);
    return 0;
}
