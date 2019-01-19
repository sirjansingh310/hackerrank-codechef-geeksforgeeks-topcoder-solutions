// https://www.hackerrank.com/challenges/detect-html-links/problem

import java.util.*;
import java.util.regex.*;

public class solution{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String num = sc.nextLine();
        String s = "";
        while(sc.hasNext()){
            s += sc.nextLine();
        }
        Pattern pattern = Pattern.compile("<a href=\"([^\"]+)\"[^>]*>(<[^/][^>]*>)*([^<]*)?(</\\w>)*</a>");
        Matcher matcher = pattern.matcher(s);
        while(matcher.find()){
            String output = "";
            if(matcher.group(1) != null)
                output += matcher.group(1).trim();
            output += ",";
            if(matcher.group(3) != null)
                output += matcher.group(3).trim();
            System.out.println(output);
        }
       
      }
}

