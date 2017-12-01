import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
/*
Problem :https://www.hackerrank.com/contests/w35/challenges/3d-surface-area/
Author:Sirjan Singh 
*/
public class Solution {

    static int surfaceArea(int[][] a,int h,int w) {
        int sa=2*h*w;
        for(int i=0;i<h;i++){
            sa+=(a[i][0]+a[i][w-1]);
            for(int j=0;j<w-1;j++){
                sa+=Math.abs(a[i][j]-a[i][j+1]);
            }
        }
        for(int j=0;j<w;j++){
            sa+=(a[0][j]+a[h-1][j]);
            for(int i=0;i<h-1;i++){
                sa+=Math.abs(a[i][j]-a[i+1][j]);
            }
        }
       return sa;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int H = in.nextInt();
        int W = in.nextInt();
        int[][] A = new int[H][W];
        for(int A_i = 0; A_i < H; A_i++){
            for(int A_j = 0; A_j < W; A_j++){
                A[A_i][A_j] = in.nextInt();
            }
        }
        int result = surfaceArea(A,H,W);
        System.out.println(result);
        in.close();
    }
}
