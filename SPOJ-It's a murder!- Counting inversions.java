// https://www.spoj.com/problems/DCEPC206/
import java.util.*;

 class GFG { 
public static long totalSum = 0L;
	// Function to count the number of inversions 
	// during the merge process 
	private static int mergeAndCount(int[] arr, int l, int m, int r) 
	{ 

		// Left subarray 
		int[] left = Arrays.copyOfRange(arr, l, m + 1); 

		// Right subarray 
		int[] right = Arrays.copyOfRange(arr, m + 1, r + 1); 

		int i = 0, j = 0, k = l, swaps = 0; 

		while (i < left.length && j < right.length) { 
			if (left[i] <  right[j]) {
				
			
				totalSum += left[i] * (long)(right.length - j);
				arr[k++] = left[i++]; 
			}
			else { 
				arr[k++] = right[j++]; 
				swaps += (m + 1) - (l + i); 
			} 
		} 

		// Fill from the rest of the left subarray 
		while (i < left.length) 
			arr[k++] = left[i++]; 

		// Fill from the rest of the right subarray 
		while (j < right.length) 
			arr[k++] = right[j++]; 

		return swaps; 
	} 

	// Merge sort function 
	private static int mergeSortAndCount(int[] arr, int l, int r) 
	{ 

		// Keeps track of the inversion count at a 
		// particular node of the recursion tree 
		int count = 0; 

		if (l < r) { 
			int m = (l + r) / 2; 

			// Total inversion count = left subarray count 
			// + right subarray count + merge count 

			// Left subarray count 
			count += mergeSortAndCount(arr, l, m); 

			// Right subarray count 
			count += mergeSortAndCount(arr, m + 1, r); 

			// Merge count 
			count += mergeAndCount(arr, l, m, r); 
		} 

		return count; 
	} 

	// Driver code 
	public static void main(String[] args) 
	{ 
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while(t-- > 0){
		    int n = sc.nextInt();
		    int arr[] = new int[n];
		    for(int i = 0 ; i < n ; i++ ) arr[i] = sc.nextInt();
		    mergeSortAndCount(arr, 0, arr.length - 1);
            System.out.println(totalSum);
            totalSum = 0L;
		}

	} 
} 

