import java.util.*;

//////// https://www.hackerrank.com/challenges/stone-division-2/
public class Solution{
    public static HashMap<Long, Long> map = new HashMap<>(); // memoization

    public static long maximumDivision(long size, long a[]){
        if(map.get(size) != null){
            return map.get(size);
        }
        boolean possible = false;
        int possibleFrom = 0;
        for(int i=possibleFrom;i<a.length;i++){
            if(size <= a[i]) 
                continue;
            else
                {
                    possibleFrom = i;
                    possible = true;
                    break;
                }
        }
        if(!possible)  
            {
                map.put(size,0L);
                return 0;
            }

        long max =  -1,current = 0;
        for(int i=0;i<a.length;i++){
            if(size > a[i] && size%a[i] == 0){
                current = maximumDivision(a[i],a); // a[i] in this question is the final size of pile. So for size = 64 and a[i] = 2, we can't say 64/2 = 32, 2 piles of 32, it means 32 piles of size 2!!
                current *= size/a[i];
                if(current > max)
                    max = current;
            }
        }
        
        map.put(size,(max == -1 ? 0 : 1 + max));
        return map.get(size);
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();
        while(q-- >0){
            long p = sc.nextLong();
            int m = sc.nextInt();
            long a[] = new long[m];
            for(int i = 0;i < m; i++){
                a[i] = sc.nextLong();
            }
            Arrays.sort(a);
            System.out.println(maximumDivision(p,a)); 
            map = new HashMap<>();// reset the hashmap after the query is processed.
        }
    }
}
