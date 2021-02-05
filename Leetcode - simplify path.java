// https://leetcode.com/explore/challenge/card/february-leetcoding-challenge-2021/584/week-1-february-1st-february-7th/3629/
class Solution {
    public String simplifyPath(String path) {
        
        while (path.contains("//")) {
            path = path.replace("//", "/");
        }
        
        if (path.length() == 1) {
            return "/";
        }
        
        if (path.charAt(path.length() - 1) == '/') {
            path = path.substring(0, path.length() - 1);
        }
        
        String[] folders = path.substring(1, path.length()).split("/");// ignore root folder /, for all other folders, / is separator betweeen parent & child folder
        
        Stack<String> stack = new Stack<>();
        
        for (String s : folders) {
            if (s.equals(".")) {
                continue;
            } else if (s.equals("..") && stack.size() > 0) {
                stack.pop();
            } else if (!s.equals("..")){
                stack.push(s);
            }
        }
        System.out.println(stack);
        List<String> results = new ArrayList<>(stack);
        String simplePath = "/";
        for (int i = 0; i < results.size(); i++) {
            simplePath += results.get(i);
            if (i != results.size() - 1) {
                simplePath += "/";
            }
        }
        
        return simplePath;
    }
}
