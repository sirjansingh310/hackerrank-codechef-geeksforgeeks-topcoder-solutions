class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        for (int i = 0; i < matrix.length; i++) {
           if (binarySearch(matrix[i], target)) {
               return true;
           }
        }
        return false;
    }
    
    private boolean binarySearch(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == target) {
                return true;
            } else if (target > arr[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return false;
    }
}

// More efficient Solution O(M + N). My solution is O(NLOG(M))
// Explanation: https://leetcode.com/explore/challenge/card/february-leetcoding-challenge-2021/587/week-4-february-22nd-february-28th/3650/discuss/1079514/C++-or-Binary-Search-or-O(m+n)-or-Explanation-(with-example)-+-Diagram
// brilliant. Taking advantage of the fact that all rows and columns are sorted
// class Solution {
// public:
//     bool searchMatrix(vector<vector<int>>& matrix, int target) {
//         int n=matrix.size();
//         int m=matrix[0].size();
        
//         // start from the [first row, last column] element
//         int i=0;
//         int j=m-1;
//         while(i<n && j>=0){
//             if(matrix[i][j]==target) // target found
//                 return true;
//             else if(matrix[i][j]>target) //target is smaller, go leftwards
//                 --j; // decrease the column index
//             else // target is larger, go downwards
//                 ++i; // increase the row index
//         }
//         return false; // target not found
//     }
// };
