// https://leetcode.com/explore/challenge/card/august-leetcoding-challenge-2021/616/week-4-august-22nd-august-28th/3920/

// Given a string representing preorder traversal of a Binary Tree, verify if it is correct 
// example: 9,3,4,#,#,1,#,#,2,#,6,#,#. Here # is null node
// Approach: Stack based solution. Push the node on the stack. If it is non-null, continue. 
// If it is #, check if we have now formed num # #. This means we are at leaf node according to preorder(current, current.left, current.right)
// Pop these 3 and replace with #. Meaning we are destroying a node and replacing it with #. Continue doing this until we have destroyed all num # # patterns
// At last our stack will be of size 1 with only #. This means we have completely destroyed our tree and it is valid as no residue is left. 


// This idea can be extended for other traversals as well. We have to look for (# num #) for inorder and  (# # num) for postorder

class Solution {
    public boolean isValidSerialization(String preorder) {
        try {
            Stack<String> stack = new Stack<>();
            String[] strings = preorder.split(",");
            for (String current : strings) {
                if (!current.equals("#")) {
                    stack.push(current);
                    continue;
                }
                boolean twoHash = !stack.isEmpty() && stack.peek().equals("#");
                stack.push("#");
                
                while (twoHash) {
                    stack.pop();
                    stack.pop();
                    stack.pop();
                    twoHash = !stack.isEmpty() && stack.peek().equals("#");
                    stack.push("#");
                }                
            }
            
            return stack.size() == 1 && stack.peek().equals("#");
        } catch (Exception e) {
            return false;
        }
    }
}
