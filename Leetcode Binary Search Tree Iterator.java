// Brute force, store inorder values pre computed in list. Making worst case O(1) Time for next() and hasNext() but O(N) space
// Better approach is iterative inorder traversal, it takes O(h) space, where h is the height of the binary tree(we are only storing one branch from
// a given root at a time)
// It averages O(1) for next() and hasNext() has O(1) worst case
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
class BSTIterator {
    private Stack<TreeNode> stack;
    
    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        pushAll(root);
    }
    
    private void pushAll(TreeNode root) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }
    
    public int next() {
        TreeNode currentItr = stack.pop();
        pushAll(currentItr.right); // if it was leaf, nothing would happen, if it was a parent with left and right, left is already printed, printing current,
        // and push right subtree as it is next to be printed inorder 
        // In most cases it is going to be no run, since leaf. When it has to run, it will run for at most the right branch height. Avg is O(1) Time
        return currentItr.val;
    }
    
    public boolean hasNext() {
        return !stack.isEmpty();
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
