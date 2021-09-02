// https://leetcode.com/explore/challenge/card/september-leetcoding-challenge-2021/636/week-1-september-1st-september-7th/3961/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

/*
 Permutations 2 3 1 and 2 1 3 compute same BST tree
 
        2
       / \
      1   3
    Though our TreeNodes returned are unique objects, but the overall tree is equal, so only one 
    of them should be considered. 
    We need to implement hashCode and equals for it to work with Set and HashMap (used by Set here internally)
    hashCode -> get bucket id. equals for retrieval during map.get(key)
    
    We should write a good hashCode function, apply hash correctly and both of these trees will point to same bucket. If they 
    don't have same hashCode value, both will be added to Set even though we have a good equals function.
    
    Right now, hardcoded it to return 0, so all trees are in same bucket(hash value are same, hashmap collisions!!!)
    but then equals will guarantee uniqueness
    
    General Rule of thumb in general is classes used as keys should be immutable(So hashcode and equals won't have side effects)
    If immutability can't be possible, we should have equals and hashcode based on some immutable id. (How we do in prod for many things, primary key considered,
    Or just have Set and HashMap keys as these primary key ids and point to the obj itself in value of map).
*/

class CustomTreeNode extends TreeNode {
    public CustomTreeNode(int val) {
        super(val);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CustomTreeNode)) {
            return false;
        }
        CustomTreeNode node = (CustomTreeNode)obj;
        boolean eq = node.val == this.val;
        
        if ((this.left == null && node.left != null) || (this.right == null && node.right != null)) {
            return false;
        }
        
        if (this.left != null) {
            eq &= this.left.equals(node.left);
        }
        
        if (this.right != null) {
            eq &= this.right.equals(node.right);
        }
        
        return eq;
    }
    
    
    @Override
    public int hashCode() {
        // all trees have same hash and will be put in same bucket and collide in Set's internal HashMap, so equals will be computed for
        // uniqueness.
        return 0;
    }
}


class Solution {
    private int n;
    private final List<List<Integer>> permutations = new ArrayList<>();
    private void getPermutations(List<Integer> current, boolean[] inserted) {
        if (current.size() == n) {
            permutations.add(current);
            return;
        }
        
        for (int i = 0; i < n; i++) {
            if (!inserted[i]) {
                inserted[i] = true;
                List<Integer> copy = new ArrayList<>(current);
                copy.add(i + 1);
                getPermutations(copy, inserted);
                inserted[i] = false; // backtrack
            }
        }
        
    }
    
    private TreeNode generateTree(List<Integer> insertOrder) {
        if (insertOrder == null || insertOrder.isEmpty()) {
            return null;
        }
        
        TreeNode root = new CustomTreeNode(insertOrder.get(0));
        
        for (int i = 1; i < insertOrder.size(); i++) {
            insert(root, insertOrder.get(i));
        }
        return root;
    }
    
    private void insert(TreeNode root, int val) {
        if (val < root.val) {
            if (root.left == null) {
                root.left = new CustomTreeNode(val);
            } else {
                insert(root.left, val);
            }
        } else {
            if (root.right == null) {
                root.right = new CustomTreeNode(val);
            } else {
                insert(root.right, val);
            }
        }
    }
    
    public List<TreeNode> generateTrees(int n) {
        this.n = n;
        getPermutations(new ArrayList<>(), new boolean[n]);
        Set<TreeNode> trees =  permutations.stream()
            .map(p -> generateTree(p))
            .collect(Collectors.toSet());
        
        return new ArrayList<TreeNode>(trees);
    }
}
