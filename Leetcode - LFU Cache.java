class ListNode {
    final int key;
    int value;
    int freq;
    ListNode next;
    ListNode prev;

    public ListNode(int key, int value) {
        this.key = key;
        this.value = value;
        this.freq = 1;
    }
}

class DLL {
    ListNode dummyHead;
    ListNode dummyTail;
    int size;

    public DLL() {
        this.dummyHead = new ListNode(-1, -1);
        this.dummyTail = new ListNode(-1, -1);
        this.dummyHead.next = this.dummyTail;
        this.dummyTail.prev = this.dummyHead;
        this.size = 0;
    }

    public void add(ListNode node) {
        ListNode currentHead = dummyHead.next;
        dummyHead.next = node;
        node.prev = dummyHead;
        node.next = currentHead;
        currentHead.prev = node;
        this.size++;
    }

    public void remove(ListNode node) {
        ListNode nodeNext = node.next;
        ListNode nodePrev = node.prev;
        nodePrev.next = nodeNext;
        nodeNext.prev = nodePrev;
        node.next = null;
        node.prev = null;
        this.size--;
    }

    public ListNode evict() {
        ListNode toEvict = dummyTail.prev;
        this.remove(toEvict);
        return toEvict;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }
}

class LFUCache {
    private final Map<Integer, DLL> freqToLRUCache;
    private final Map<Integer, ListNode> nodeMap;
    private final int capacity;
    private int leastFreq;
    private int currentSize;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.freqToLRUCache = new HashMap<>();
        this.nodeMap = new HashMap<>();
        this.leastFreq = 1;
    }
    
    public int get(int key) {
        ListNode node = nodeMap.get(key);
        if (node == null) {
            return -1;
        }
        int value = node.value;
        shiftNode(node);
        return value;
    }
    
    public void put(int key, int value) {
        ListNode node = nodeMap.get(key);
        if (node == null) {
            node = new ListNode(key, value);
            if (currentSize == capacity) {
                evict();
                insertNew(node);
            } else {
                insertNew(node);
            }
        } else {
            node.value = value;
            shiftNode(node);
        }
    }

    private void insertNew(ListNode node) {
        if (!freqToLRUCache.containsKey(1)) {
            freqToLRUCache.put(1, new DLL());
        }
        freqToLRUCache.get(1).add(node);
        this.leastFreq = 1;
        nodeMap.put(node.key, node);
        this.currentSize++;
    }

    private void shiftNode(ListNode node) {
        int currentNodeFreq = node.freq;
        DLL dll = freqToLRUCache.get(currentNodeFreq);
        dll.remove(node);
        // edge case, our least freq DLL is now empty, increment leastFreq
        if (this.leastFreq == currentNodeFreq && dll.isEmpty()) {
            this.leastFreq++;
        }
        node.freq++;
        dll = freqToLRUCache.get(node.freq);
        if (dll == null) {
            dll = new DLL();
            freqToLRUCache.put(node.freq, dll);
        }
        dll.add(node);
    }

    private void evict() {
        DLL dll = freqToLRUCache.get(this.leastFreq);
        ListNode evicted = dll.evict();
        if (dll.isEmpty()) {
            this.leastFreq++;
        }
        nodeMap.remove(evicted.key);
        this.currentSize--;
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
