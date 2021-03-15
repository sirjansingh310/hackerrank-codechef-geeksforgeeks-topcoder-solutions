/// read this blog https://krishankantsinghal.medium.com/my-first-blog-on-medium-583159139237
class LRUCache {
    private class Entry {
        int key;
        int value;
        Entry left, right;
        
        public String toString() {
            return "key = "  + key + " " + " value = " + value; 
        }
    }
    private Map<Integer, Entry> map;
    private Entry start, end;
    private int allowedSize;
    
    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.allowedSize = capacity;
    }
    
    public int get(int key) {
        if (map.containsKey(key)) {
            Entry entry = map.get(key);
            removePreviousEntry(entry); // remove previous node in DLL, and put it at head. so size of cache is same, with updated DLL.
            markAsRecentlyUsed(entry);
            return entry.value;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Entry entry = map.get(key);
            entry.value = value;
            removePreviousEntry(entry);
            map.put(key, entry);
            markAsRecentlyUsed(entry);
        } else {
            Entry newEntry = new Entry();
            newEntry.key = key;
            newEntry.value = value;
            if (map.size() >= allowedSize) {
                map.remove(end.key);
                removePreviousEntry(end);
                markAsRecentlyUsed(newEntry);
            } else {
                markAsRecentlyUsed(newEntry);
            }
            map.put(key, newEntry);
        }
    }
    
    
    private void removePreviousEntry(Entry entry) {
        if (entry.left != null) {
            entry.left.right = entry.right;
        } else {
            start = entry.right;
        }
        
        if (entry.right != null) {
            entry.right.left = entry.left;
        } else {
            end = entry.left;
        }
    }
    
    
    private void markAsRecentlyUsed(Entry entry) {
        entry.right = start;
        entry.left = null;
        
        if (start != null) {
            start.left = entry;
        }
        
        start = entry;
        if (end == null) {
            end = start;
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
