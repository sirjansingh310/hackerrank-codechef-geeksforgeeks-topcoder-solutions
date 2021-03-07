// https://leetcode.com/explore/challenge/card/march-leetcoding-challenge-2021/588/week-1-march-1st-march-7th/3663/
class MyHashMap {
    private String[][] buckets;
    /** Initialize your data structure here. */
    public MyHashMap() {
        // according to the question, there will be a maximum of 10000 operations, so 10000 inserts 
        // at max, so a 100 * 100 2d array should suffice including cases of collisions
        buckets = new String[101][101];
    }
    
    /** value will always be non-negative. */
    public void put(int key, int value) {
        int oldValue = get(key);
        int bucketIndex = getBucketIndex(key);
        if (oldValue == -1) {
            int firstFreeIndex = getFreeIndex(buckets[bucketIndex]);
            buckets[bucketIndex][firstFreeIndex] = encode(key, value);
            return;
        } else {
            for (int i = 0; i < buckets[bucketIndex].length; i++) {
                if (encode(key, oldValue).equals(buckets[bucketIndex][i])) {
                    buckets[bucketIndex][i] = encode(key, value);
                    return;
                }
             }
        }
    }
    
    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int bucketIndex = getBucketIndex(key);
        for (int i = 0; i < buckets[bucketIndex].length; i++) {
            if (buckets[bucketIndex][i] == null) {
                continue;
            }
            String[] keyValue = buckets[bucketIndex][i].split("_");
            if (keyValue[0].equals("" + key)) {
                return Integer.parseInt(keyValue[1]);
            }
        }
        return -1;
    }
    
    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int bucketIndex = getBucketIndex(key);
        for (int i = 0; i < buckets[bucketIndex].length; i++) {
            if (buckets[bucketIndex][i] == null) {
                continue;
            }
            String[] keyValue = buckets[bucketIndex][i].split("_");
            if (keyValue[0].equals("" + key)) {
                buckets[bucketIndex][i] = null;
                return;
            }
        }
    }
    
    private int getBucketIndex(int key) {
        return key % 101; // own sort of hashcode implementation :)
    }
    
    private int getFreeIndex(String[] bucket) {
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] == null) {
                return i;
            }
        }
        return 100;
    }
    
    private String encode(int key, int value) {
        return key + "_" + value;
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
