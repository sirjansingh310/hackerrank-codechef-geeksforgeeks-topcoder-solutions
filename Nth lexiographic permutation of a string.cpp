#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
#include <string.h>
using namespace std;

// https://medium.com/@aiswaryamathur/find-the-n-th-permutation-of-an-ordered-string-using-factorial-number-system-9c81e34ab0c8

int factoradic[13];
void findFactoradic(int n){
   for(int i = 0; i < 13; i++) factoradic[i] = 0;
   int i = 1;
   while(n > 0){
       factoradic[13 - i] = n % i;
       n = n / i;
       i++;
   }
}

string findNthPermutation (string s){
    string output = "";
    for(int i = 0; i < 13; i++){
        int currentPos = factoradic[i];
        output += s[currentPos];
        s.erase(currentPos, 1);
    }
    return output;
}
int main() {
    const string str = "abcdefghijklm";
    int t,n;
    cin >> t;
    while(t--){
        cin >> n;
        findFactoradic(n - 1);
        cout << findNthPermutation(str) << endl;
    }
    
    return 0;
}
