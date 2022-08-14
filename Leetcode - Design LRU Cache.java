// Doubly Linked List LRU Cache
class LRUCache {
    
    private class ListNode {
        int number;
        ListNode prev;
        ListNode next;
        final int key;
        
        private ListNode(int key, int number) {
            this.key = key;
            this.number = number;
        }
    }
    
    private int capacity;
    private Map<Integer, ListNode> cache;
    private ListNode head;
    private ListNode tail;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
    }
    
    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        ListNode node = cache.get(key);
        removeAsLeastRecentlyUsed(node);
        markAsRecentlyUsed(node);
        return node.number;
    }
    
    public void put(int key, int value) {
        ListNode node = cache.get(key);
        if (node == null) {
            if (cache.size() >= capacity) {
                removeAsLeastRecentlyUsed(tail);
            }
            node = new ListNode(key, value);
            markAsRecentlyUsed(node);
        } else {
            node.number = value;
            removeAsLeastRecentlyUsed(node);
            markAsRecentlyUsed(node);
        }
    }
    
    private void removeAsLeastRecentlyUsed(ListNode node) {
        if (node == null) {
            return;
        }
        cache.remove(node.key);
        if (head == node) {
            head = head.next;
        } else if (tail == node) {
            tail = tail.prev;
        } else {
            ListNode prev = node.prev;
            ListNode next = node.next;
            prev.next = next;
            next.prev = prev;
        }
    }
    
    private void markAsRecentlyUsed(ListNode node) {
        if (node == null) {
            return;
        }
        cache.put(node.key, node);
        if (head == null) {
            head = node;
            tail = node;
        } else if (head == node) {
            return;
        } else if (tail == node) {
            tail = tail.prev;
        }
        node.next = head;
        head.prev = node;
        head = node;
        
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
