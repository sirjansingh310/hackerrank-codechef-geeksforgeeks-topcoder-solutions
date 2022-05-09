class MyStack {
    private LinkedList<Integer> pushQueue;
    private LinkedList<Integer> popQueue;
     
    public MyStack() {
        pushQueue = new LinkedList<>();
        popQueue = new LinkedList<>();
    }
    
    public void push(int x) {
        pushQueue.add(x);
    }
    
    public int pop() {
        if (pushQueue.isEmpty()) {
            return -1;
        }
        int ret = -1;
        while (pushQueue.size() != 1) {
            popQueue.add(pushQueue.poll());
        }
        ret = pushQueue.poll();
        
        // brilliant move. since order remains same after doing
        // popQueue.add(pushQueue.poll()), we found are ret and just swap the queues
        // for future operations.
        LinkedList<Integer> temp = pushQueue;
        pushQueue = popQueue;
        popQueue = temp;
        
        return ret;
    }
    
    public int top() {
        if (!pushQueue.isEmpty()) {
            return pushQueue.get(pushQueue.size() - 1);
        }
        return -1;
    }
    
    public boolean empty() {
        return pushQueue.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
