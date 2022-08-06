// https://leetcode.com/problems/min-stack/. Design a stack with push(), pop(), top(), getMin() with all of them to be O(1)
class MinStack {
    private class ListNode {
        int number;
        ListNode next;
        
        private ListNode(int number) {
            this.number = number;
        }
    }
    
    private int size;
    private final Map<Integer, Integer> minimumAtSize;// or we could use an actual stack which stores minimumTillNow as its elements.
    private ListNode topNode;// we do insertion at head for push and pop to be O(1)

    public MinStack() {
        minimumAtSize = new HashMap<>();
    }
    
    public void push(int val) {
        int bestMinimumTillNow = minimumAtSize.getOrDefault(size, Integer.MAX_VALUE);
        size++;
        ListNode node = new ListNode(val);
        node.next = topNode;
        topNode = node;
        minimumAtSize.put(size, Math.min(bestMinimumTillNow, val));
    }
    
    public void pop() {
        size--;
        topNode = topNode.next;
    }
    
    public int top() {
        return topNode.number;
    }
    
    public int getMin() {
        return minimumAtSize.get(size);
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
