import java.util.* ;
import java.io.*; 

public class Solution {
	public static int matrixMultiplication(int[] arr , int N) {
		int[][] dp = new int[arr.length][arr.length];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], -1);
		}
		return recur(arr, 1, N - 1, dp);
	}
	// O(N^3) Time, O(N^2) Space
	private static int recur(int[] arr, int start, int end, int[][] dp) {
		if (start == end) {
			return 0;
		}

		if (dp[start][end] != -1) {
			return dp[start][end];
		}
		int best = Integer.MAX_VALUE;
		// arr = {10, 20, 30} => matrix = [10 * 20], [20 * 30]
		for (int partition = start; partition < end; partition++) {
			// to multiply matrix [i x j] with [j x k] we require i * j * k steps
			int count = arr[start - 1] * arr[partition] * arr[end];
			count += recur(arr, start, partition, dp);
			count += recur(arr, partition + 1, end, dp);
			best = Math.min(best, count);
		}

		dp[start][end] = best;
		return best;
	}
}
