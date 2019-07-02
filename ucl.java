/* package codechef; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Codechef
{
    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm, HashMap<String, Integer> hm2) 
    { 
        // Create a list from elements of HashMap 
        List<Map.Entry<String, Integer> > list = 
               new LinkedList<Map.Entry<String, Integer> >(hm.entrySet()); 
  
        // Sort the list 
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() { 
            public int compare(Map.Entry<String, Integer> o1,  
                               Map.Entry<String, Integer> o2) 
            { 
                if(o2.getValue() == o1.getValue()){ // if same points, sort by goal diff.
                    return hm2.get(o2.getKey()) - hm2.get(o1.getKey());
                }
                return (o2.getValue()).compareTo(o1.getValue()); // dec order 
            } 
        }); 
          
        // put data from sorted list to hashmap  
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>(); 
        for (Map.Entry<String, Integer> aa : list) { 
            temp.put(aa.getKey(), aa.getValue()); 
        } 
        return temp; 
    } 
    
    public static void printResult(HashMap<String, Integer> points, HashMap<String, Integer> goalDifference){
        points = sortByValue(points,goalDifference);
        int count = 0;
        for(Map.Entry<String,Integer> entry: points.entrySet()){
            if(count == 2)
                break;
            System.out.print(entry.getKey() + " ");
            count++;
        }
    }
    
	public static void main (String[] args) throws java.lang.Exception
	{
	    Scanner sc = new Scanner(System.in);
	    int t = sc.nextInt();
	    sc.nextLine();
		 while(t-- > 0){
    		String matchResults[] = new String[12];
    	    for(int i = 0; i < 12; i++){
    	        matchResults[i] = sc.nextLine();
    	    }
    	    HashMap<String, Integer> points = new HashMap<>();
    	    HashMap<String, Integer> goalDifference = new HashMap<>();
    	    for(int i = 0; i < 12; i++){
    	        String line[] = matchResults[i].split(" ");
    	        String firstTeam = line[0];
    	        String secondTeam = line[4];
    	        if(Integer.parseInt(line[1]) > Integer.parseInt(line[3])){
    	            if(points.containsKey(firstTeam))
    	                points.put(firstTeam, points.get(firstTeam) + 3);
    	            else
    	                points.put(firstTeam, 3);
    	        }
    	        
    	        else if(Integer.parseInt(line[3]) > Integer.parseInt(line[1])){
    	            if(points.containsKey(secondTeam))
    	                points.put(secondTeam, points.get(secondTeam) + 3);
    	            else
    	                points.put(secondTeam, 3);
    	        }
    	        else{
    	            if(points.containsKey(firstTeam))
    	                points.put(firstTeam, points.get(firstTeam) + 1);
    	            else 
    	                points.put(firstTeam, 1);
    	            if(points.containsKey(secondTeam))
    	                points.put(secondTeam, points.get(secondTeam) + 1);
    	            else 
    	                points.put(secondTeam, 1);
    	           
    	        }
    	        if(goalDifference.containsKey(firstTeam)){
    	            goalDifference.put(firstTeam, goalDifference.get(firstTeam) + Integer.parseInt(line[1]) - Integer.parseInt(line[3]));
    	        }
    	        else
    	         goalDifference.put(firstTeam, Integer.parseInt(line[1]) - Integer.parseInt(line[3]));
    	        
    	         if(goalDifference.containsKey(secondTeam)){
    	            goalDifference.put(secondTeam, goalDifference.get(secondTeam) + Integer.parseInt(line[3]) - Integer.parseInt(line[1]));
    	        }
    	        else
    	         goalDifference.put(secondTeam, Integer.parseInt(line[3]) - Integer.parseInt(line[1]));
    	         
    	    }
    	    printResult(points, goalDifference);
    	    System.out.println();
		}
	}
}
