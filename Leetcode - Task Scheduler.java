// https://leetcode.com/problems/task-scheduler/
// My solution with just one PQ which is handling time and freq both, needed to rebalance queue 
// See solution below which is much cleaner and faster by de-coupling time and freq issues into 2 queues.
class Task implements Comparable<Task> {
    char taskId;
    int lastExecutionTimeStamp;
    int freq;
    static int time;
    static int n;
    
    public Task(char taskId, int lastExecutionTimeStamp, int freq) {
        this.taskId = taskId;
        this.lastExecutionTimeStamp = lastExecutionTimeStamp;
        this.freq = freq;
    }
    
    @Override
    public int compareTo(Task other) {
        boolean hasZeroWaitTime = (this.lastExecutionTimeStamp == -1) || n <= (time - this.lastExecutionTimeStamp);
        boolean otherHasZeroWaitTime = (other.lastExecutionTimeStamp == -1 ) || n <= (time - other.lastExecutionTimeStamp);
        if (hasZeroWaitTime && otherHasZeroWaitTime) {
            if (other.freq == this.freq) {
                return this.lastExecutionTimeStamp - other.lastExecutionTimeStamp;
             }
            return other.freq - this.freq;
        } else if (hasZeroWaitTime) {
            return -1;
        } else if (otherHasZeroWaitTime) {
            return 1;
        } else {
            if (other.freq == this.freq) {
                return this.lastExecutionTimeStamp - other.lastExecutionTimeStamp;
             }
            return other.freq - this.freq;
        }
    }
    
    @Override
    public String toString() {
        return "" + this.taskId;
    }
}


class Solution {
    public int leastInterval(char[] tasks, int n) {
        if (n == 0) {
            return tasks.length;
        }
        Task.time = 0;
        Task.n = n;
        // PQ. Pick task which is has less wait time. If both have 0 wait time, pick by freq
        PriorityQueue<Task> pq = new PriorityQueue<>();
        Map<Character, Integer> freq = new HashMap<>();
        
        for (char c : tasks) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        
        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
            pq.add(new Task(entry.getKey(), -1, entry.getValue()));
        }
        while (!pq.isEmpty()) {
            // re balance queue
            List<Task> rebalance = new ArrayList<>();
            while (!pq.isEmpty()) {
                rebalance.add(pq.poll());
            }
            for (Task t : rebalance) {
                pq.add(t);
            }
            Task current = pq.poll();
            // see if this task needs to wait
            if (current.lastExecutionTimeStamp != -1 && Task.time - current.lastExecutionTimeStamp < n) {
                int waitTime = (n - (Task.time - current.lastExecutionTimeStamp));
                Task.time += waitTime;
            }
            current.freq--;
            Task.time++;
            current.lastExecutionTimeStamp = Task.time;
            if (current.freq > 0) {
                pq.add(current);
            }
        }
        return Task.time;
    }
}


// https://www.youtube.com/watch?v=s8p8ukTyA2I
class Task implements Comparable<Task> {
    char taskId;
    int freq;
    int canBeAddedBackInMaxHeapTime;
    
    public Task(char taskId, int freq) {
        this.taskId = taskId;
        this.freq = freq;
    }
    
    @Override
    public int compareTo(Task other) {
        return other.freq - this.freq;
    }
    
    @Override
    public String toString() {
        return "" + this.taskId;
    }
}


class Solution {
    public int leastInterval(char[] tasks, int n) {
        if (n == 0) {
            return tasks.length;
        }
        int time = 0;
        PriorityQueue<Task> pq = new PriorityQueue<>();// will only contain elements which have zero waiting time
        Queue<Task> canAddToHeapQueue = new LinkedList<>();
        
        Map<Character, Integer> map = new HashMap<>();
        for (char c : tasks) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        // at first all can be picked
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            pq.add(new Task(entry.getKey(), entry.getValue()));
        }
        
        while (!pq.isEmpty() || !canAddToHeapQueue.isEmpty()) {
            time++;// will include exection or else wait if both below if condition fail
            if (!pq.isEmpty()) {
                Task polled = pq.poll();
                polled.freq--;
                if (polled.freq > 0) {
                    polled.canBeAddedBackInMaxHeapTime = time + n;
                    canAddToHeapQueue.add(polled);
                }
            }
            
            if (!canAddToHeapQueue.isEmpty() && canAddToHeapQueue.peek().canBeAddedBackInMaxHeapTime == time) {
                pq.add(canAddToHeapQueue.poll());
            }
        }
        return time;
    }
}



