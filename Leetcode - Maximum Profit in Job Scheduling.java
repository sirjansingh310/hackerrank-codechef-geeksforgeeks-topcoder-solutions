// 5 Star Problem!!!! Amazing question 
// https://leetcode.com/explore/challenge/card/august-leetcoding-challenge-2021/616/week-4-august-22nd-august-28th/3950/
// Given jobs with start time, end time and profit, find the maximum possible profit given you can do only one job at a time.

// 2 Solutions

// First Solution. DP (recursion + memoization)
// Sort the Jobs by start time. Now when you are at a position to take the job, you can do it or skip it. If you do the job, calculate the profit considering this job
// or skip this
// Return the max of the above. 


// Second Solutuion. Optimization of the first.
// In the first solution, when we think of considering the current job, we have to also call recursion on future job. The future job is the first job without 
// conflict with our current job(the whole point of the question that we can do only one at a time)
// We were doing it in for loop and immediately breaking upon finding one. 
// This can be done in logN time by doing a quick binary search as our jobs are already sorted by start time.


// DP Solution
class Job implements Comparable<Job> {
    int startTime; 
    int endTime;
    int profit;
    
    public Job(int startTime, int endTime, int profit) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.profit = profit;
    }
    
    @Override
    public int compareTo(Job job) {
        if (this.startTime == job.startTime) {
            return job.profit - this.profit;
        }
        return this.startTime - job.startTime;
    }
    
    @Override 
    public String toString() {
        return String.format("Job starts at %d and ends at %d with profit %d\n", startTime, endTime, profit);
    }
    
    public boolean conflict(Job job) {
        return this.endTime > job.startTime;
    }
}

class Solution {
    private final Map<Integer, Integer> memo = new HashMap<>();
    
    private int findBestProfit(List<Job> jobs, int currentJobIndex) {
        if (currentJobIndex >= jobs.size()) {
            return 0;
        }
        if (memo.containsKey(currentJobIndex)) {
            return memo.get(currentJobIndex);
        }
        Job currentJob = jobs.get(currentJobIndex);
        int bestProfit = currentJob.profit;
        
        // Case 1) We consider doing this job and then our next job will be first job found
        // without conflict
        for (int i = currentJobIndex + 1; i < jobs.size(); i++) {
            if (!currentJob.conflict(jobs.get(i))) {
                bestProfit = Math.max(bestProfit, currentJob.profit + findBestProfit(jobs, i));
                break;
            }
        }
        
        // Case 2) We discard this job and simply move on
        bestProfit = Math.max(bestProfit, findBestProfit(jobs, currentJobIndex + 1));
        
        memo.put(currentJobIndex, bestProfit);
        return bestProfit;
    }
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        List<Job> jobs = new ArrayList<>();
        
        for (int i = 0; i < startTime.length; i++) {
            jobs.add(new Job(startTime[i], endTime[i], profit[i]));
        }
        Collections.sort(jobs);
        return findBestProfit(jobs, 0);
    }
}


// DP + Binary Search

class Job implements Comparable<Job> {
    int startTime; 
    int endTime;
    int profit;
    
    public Job(int startTime, int endTime, int profit) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.profit = profit;
    }
    
    @Override
    public int compareTo(Job job) {
        if (this.startTime == job.startTime) {
            return job.profit - this.profit;
        }
        return this.startTime - job.startTime;
    }
    
    @Override 
    public String toString() {
        return String.format("Job starts at %d and ends at %d with profit %d\n", startTime, endTime, profit);
    }
    
    public boolean conflict(Job job) {
        return this.endTime > job.startTime;
    }
}

class Solution {
    private final Map<Integer, Integer> memo = new HashMap<>();
    
    private int findBestProfit(List<Job> jobs, int currentJobIndex) {
        if (currentJobIndex >= jobs.size()) {
            return 0;
        }
        if (memo.containsKey(currentJobIndex)) {
            return memo.get(currentJobIndex);
        }
        Job currentJob = jobs.get(currentJobIndex);
        int bestProfit = currentJob.profit;
        
        // Case 1) We consider doing this job and then our next job will be first job found
        // without conflict. Now since our jobs are sorted, we can use binary search
        int nextPossibleJob = binarySearch(currentJob, jobs, currentJobIndex);
        if (nextPossibleJob != -1) {
            bestProfit = Math.max(bestProfit, currentJob.profit + findBestProfit(jobs, nextPossibleJob));
        }
        
        // Case 2) We discard this job and simply move on
        bestProfit = Math.max(bestProfit, findBestProfit(jobs, currentJobIndex + 1));
       
        memo.put(currentJobIndex, bestProfit);
        return bestProfit;
    }
    
    private int binarySearch(Job job, List<Job> jobs, int index) {
        int low = index + 1, high = jobs.size() - 1;
        int ans = -1;
        
        while (low <= high) {
            int mid = (low + high) / 2;
            if (!job.conflict(jobs.get(mid))) {
                ans = mid;
                high = mid - 1; // lets try to reduce more to get closer to index
            } else { // meaning conflict for all low to mid anyways
                low = mid + 1;
            }
        }
        
        return ans;
    }
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        List<Job> jobs = new ArrayList<>();
        
        for (int i = 0; i < startTime.length; i++) {
            jobs.add(new Job(startTime[i], endTime[i], profit[i]));
        }
        Collections.sort(jobs);
        return findBestProfit(jobs, 0);
    }
}
