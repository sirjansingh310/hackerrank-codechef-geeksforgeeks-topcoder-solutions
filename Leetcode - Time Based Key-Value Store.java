// https://leetcode.com/problems/time-based-key-value-store/

class TimeMap {
    public class Pair {
        int timestamp;
        String value;
        
        public Pair(int timestamp, String value) {
            this.timestamp = timestamp;
            this.value = value;
        }
    }
    
    private final Map<String, List<Pair>> map;
    
    public TimeMap() {
        map = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        // our timestamps in input are ordered, so we can be sure binary search will work
        map.computeIfAbsent(key, x -> new ArrayList<>()).add(new Pair(timestamp, value));
    }
    
    public String get(String key, int timestamp) {
        return binarySearch(key, timestamp, map);
    }
    
    private String binarySearch(String key, int timestamp, Map<String, List<Pair>> map) {
        if (!map.containsKey(key)) {
            return "";
        }
        List<Pair> list = map.get(key);
        Pair firstEntry = list.get(0);
        Pair lastEntry = list.get(list.size() - 1);
        if (timestamp < firstEntry.timestamp) {
            return "";
        } else if (timestamp > lastEntry.timestamp) {
            return lastEntry.value;
        }
        
        int low = 0, high = list.size() - 1;
        int matchIndex = -1;
        
        while (low <= high) {
            int mid = (low + high) / 2;
            if (list.get(mid).timestamp < timestamp) {
                matchIndex = mid;// one just before timestamp is also valid
                low = mid + 1;
            } else if (list.get(mid).timestamp > timestamp) {
                high = mid - 1;
            } else {
                matchIndex = mid;
                break;
            }
        }
        
        return matchIndex == -1 ? "" : list.get(matchIndex).value;
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */
