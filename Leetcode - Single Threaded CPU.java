// https://leetcode.com/problems/single-threaded-cpu/
class Task {
    final int enqueueTime;
    final int processingTime;
    final int index;

    public Task(int enqueueTime, int processingTime, int index) {
        this.enqueueTime = enqueueTime;
        this.processingTime = processingTime;
        this.index = index;
    }

    @Override
    public String toString() {
        return "Task{" +
                "index=" + index +
                '}';
    }
}

class Solution {
    public int[] getOrder(int[][] tasks) {
        List<Task> taskList = new ArrayList<>();
        for (int i = 0; i < tasks.length; i++) {
            taskList.add(new Task(tasks[i][0], tasks[i][1], i));
        }
        Collections.sort(taskList, (t1, t2) -> t1.enqueueTime - t2.enqueueTime);
        Queue<Task> taskQueue = new LinkedList<>();
        taskQueue.addAll(taskList);

        PriorityQueue<Task> cpuSchedulerQueue = new PriorityQueue<>((t1, t2) -> {
            if (t1.processingTime == t2.processingTime) {
                return t1.index - t2.index;
            }
            return t1.processingTime - t2.processingTime;
        });

        int[] result = new int[tasks.length];
        int resultIdx = 0;

        int currentTimeStamp = taskQueue.peek().enqueueTime;

        while (resultIdx < result.length) {
            // add tasks to our cpu scheduler only the ones which are seen based on currentTimestamp
            while (!taskQueue.isEmpty() && taskQueue.peek().enqueueTime <= currentTimeStamp) {
                cpuSchedulerQueue.add(taskQueue.poll());
            }
            Task currentRunning = cpuSchedulerQueue.poll();
            if (currentRunning != null) {
                result[resultIdx++] = currentRunning.index;
                currentTimeStamp += currentRunning.processingTime;
            } else { // case where cpu sits idle for long, just skip to next timestamp of available task
                currentTimeStamp = taskQueue.peek().enqueueTime;
            }
        }

        return result;
    }
}
