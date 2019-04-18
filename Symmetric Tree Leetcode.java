// https://leetcode.com/problems/symmetric-tree/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isPalindrome(List<Integer> list){
        for(int i = 0 ;i<=list.size()/2; i++){
            if(list.get(i) != list.get(list.size() -i -1))
                return false;
        }
        return true;
    }
    public boolean isSymmetric(TreeNode root) {
        if(root == null)
            return true;
        // check if it is sym level by level
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        LinkedHashMap<TreeNode,Integer> level = new LinkedHashMap<>();
        level.put(root,1);
        while(queue.size() > 0 ){
            TreeNode current = queue.poll();
            if(current.left != null){
                level.put(current.left,level.get(current) + 1);
                queue.add(current.left);
            }
            else{
                TreeNode nullNode = new TreeNode(-1); // to check for cases like null,2 on left subtree vs null,2 on right subtree. This is not sym. So instead of sending [2,2] to palindrome function(which will return true!), we pass [-1,2,-1,2] to the palindrome fun which will return false.
                level.put(nullNode,level.get(current) + 1);
            }
            if(current.right != null){
                level.put(current.right,level.get(current) + 1);
                queue.add(current.right);
            }
            else{
                TreeNode nullNode = new TreeNode(-1);
                level.put(nullNode,level.get(current) + 1);
            }
        }
        List<Integer> currentLevelList = new ArrayList<>();
        int currentLevel = 1;
        for(TreeNode current: level.keySet()){
            if(currentLevel == level.get(current)){
                currentLevelList.add(current.val);
            }
            else{
                currentLevel++;
                if(!isPalindrome(currentLevelList))
                    return false;
                currentLevelList = new ArrayList<>();
                currentLevelList.add(current.val);
            }
        }
        if(currentLevelList.size() > 0){
            if(!isPalindrome(currentLevelList))
                return false;
        }
        return true;
    }
}

//////////////////////////////
// Editorial's awesome solution:
public boolean isSymmetric(TreeNode root) {
    return isMirror(root, root);
}

public boolean isMirror(TreeNode t1, TreeNode t2) {
    if (t1 == null && t2 == null) return true;
    if (t1 == null || t2 == null) return false;
    return (t1.val == t2.val)
        && isMirror(t1.right, t2.left)
        && isMirror(t1.left, t2.right);
}
