//https://leetcode.com/problems/design-front-middle-back-queue/
class FrontMiddleBackQueue {
    private LinkedList<Integer> dequeue;
    public FrontMiddleBackQueue() {
        dequeue = new LinkedList<>();
    }
    
    public void pushFront(int val) {
        dequeue.addFirst(val);
    }
    
    public void pushMiddle(int val) {
        dequeue.add(dequeue.size() / 2, val);
    }
    
    public void pushBack(int val) {
        dequeue.addLast(val);
    }
    
    public int popFront() {
        return dequeue.isEmpty() ? -1 : dequeue.removeFirst();
    }
    
    public int popMiddle() {
        return dequeue.isEmpty() ? -1 : dequeue.remove((dequeue.size() - 1) / 2);
    }
    
    public int popBack() {
        return dequeue.isEmpty() ? -1 : dequeue.removeLast();
    }
}

/**
 * Your FrontMiddleBackQueue object will be instantiated and called as such:
 * FrontMiddleBackQueue obj = new FrontMiddleBackQueue();
 * obj.pushFront(val);
 * obj.pushMiddle(val);
 * obj.pushBack(val);
 * int param_4 = obj.popFront();
 * int param_5 = obj.popMiddle();
 * int param_6 = obj.popBack();
 */




// My first unaccepted solution using Deque DS and Stack as a buffer 
class FrontMiddleBackQueue {
    
    private Deque<Integer> dequeue;
    private Stack<Integer> bufferStack;

    public FrontMiddleBackQueue() {
        dequeue = new LinkedList<>();
        bufferStack = new Stack<>();
    }
    
    public void pushFront(int val) {
        dequeue.addFirst(val);
    }
    
    public void pushMiddle(int val) {
        if (dequeue.isEmpty()) {
            dequeue.addFirst(val);
            return;
        }
        fillBuffer(dequeue.size() / 2);
        dequeue.addFirst(val);
        emptyBuffer();
    }
    
    public void pushBack(int val) {
        dequeue.addLast(val); 
    }
    
    public int popFront() {
        if (dequeue.isEmpty()) {
            return -1;
        }
        return dequeue.removeFirst();
    }
    
    public int popMiddle() {
        if (dequeue.isEmpty()) {
            return -1;
        } else if (dequeue.size() == 1) {
            return dequeue.removeFirst();
        }
        fillBuffer((dequeue.size() - 1) / 2);
        int removedElement = dequeue.removeFirst();
        emptyBuffer();
        return removedElement;
    }
    
    public int popBack() {
        if (dequeue.isEmpty()) {
            return -1;
        }
        return dequeue.removeLast();
    }
    
    private void fillBuffer(int index) {
        while (index-- >= 0) {
            System.out.println(dequeue);
            bufferStack.push(dequeue.removeFirst());
        }
    }
    
    private void emptyBuffer() {  
        while (!bufferStack.isEmpty()) {
            dequeue.addFirst(bufferStack.pop());
        }
    }
}

/**
 * Your FrontMiddleBackQueue object will be instantiated and called as such:
 * FrontMiddleBackQueue obj = new FrontMiddleBackQueue();
 * obj.pushFront(val);
 * obj.pushMiddle(val);
 * obj.pushBack(val);
 * int param_4 = obj.popFront();
 * int param_5 = obj.popMiddle();
 * int param_6 = obj.popBack();
 */
