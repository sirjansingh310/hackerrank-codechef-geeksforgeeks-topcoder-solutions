/* package codechef; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

// Problem: https://www.codechef.com/problems/COMPILER
class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while(t-- >0){
		    String s = sc.next();
		    LinkedList<Character> stack = new LinkedList<>();
		    int count = 0;
		    int res = 0;
		    for(int i = 0;i<s.length();i++){
		        if(s.charAt(i) == '<'){
		            stack.add('<');
		        }
		        else{
		            if(stack.size() != 0){
		                stack.pop();
		                count++;
		                if(stack.size() == 0){
		                    res = count;
		                }
		            }
		            else{
		                break;
		            }
		        }
		    }
		    System.out.println(res * 2);
		}
	}
}
