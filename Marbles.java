
import java.util.*;
import java.lang.*;
import java.io.*;

// Problem : https://www.codechef.com/problems/MARBLES

/* Name of the class has to be "Main" only if the class is public. */
class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
		Scanner sc = new Scanner(System.in);
		int tests = sc.nextInt();
		while(tests-- >0){
		    int n = sc.nextInt() - 1;
		    int r = sc.nextInt() - 1;
		    // The solution of this problem is calculating c(n-1,r-1).
		    //https://www.geeksforgeeks.org/different-ways-to-represent-n-as-sum-of-k-non-zero-integers/
		    long ncr = 1;
		    if(r > n-r){
		        r = n-r; // c(n,r) = c(n,(n-r))
		    }
		    for(int i=0;i<r;i++){
		        ncr = ncr * (n-i);
		        ncr = ncr / (i+1);
		    }
		    System.out.println(ncr);
		}
	}
}
