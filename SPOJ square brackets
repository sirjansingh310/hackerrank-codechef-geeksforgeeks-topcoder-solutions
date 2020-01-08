/*package whatever //do not write package name here */
// brute force way. DP solution reference: https://neppramod.wordpress.com/2017/09/24/square-brackets-spoj/
import java.io.*;
import java.util.*;

class GFG {
    public static int count = 0;
    @SuppressWarnings("unchecked")
    public static boolean isStackEmptyAtTheEnd(Stack<Character> stack, int index, int n, boolean isFixed[]){
        if(index > (2*n - 1)){
        
            if(stack.empty()){
                count++;
            }
            return stack.empty();
        }
        
        if(isFixed[index]){
            stack.push('[');
            return isStackEmptyAtTheEnd(stack, index + 1, n, isFixed);// current index has '['
        }
        
        Stack<Character> stack1 = (Stack<Character>)stack.clone();
        stack1.push('[');
        boolean firstCase = isStackEmptyAtTheEnd(stack1, index + 1, n, isFixed); // current index has '['
        Stack<Character> stack2 = (Stack<Character>)stack.clone();
        boolean secondCase;
        if(!stack2.empty()){
            stack2.pop();
            secondCase = isStackEmptyAtTheEnd(stack2, index + 1, n, isFixed);// current index has ']'
        }
        else{
            secondCase = false;// stack is empty and current index has ']', i.e [ ] ], so this is a false case
        }
        return firstCase || secondCase;
        
    }
    public static int solve(int n, int k, boolean isFixed[]){
        count = 0;
        isStackEmptyAtTheEnd(new Stack<Character>(), 0, n, isFixed);
        return count;
    }
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while(t-- >0){
		    int n = sc.nextInt();
		    int k = sc.nextInt();
		    int arr[] = new int[k];
		    boolean isFixed[] = new boolean[2 * n];
		    
		    for(int i = 0; i < k; i++){ 
		        isFixed[sc.nextInt() - 1] = true;
		    }
		    System.out.println(solve(n, k, isFixed));
		}
	}
}
