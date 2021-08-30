// https://leetcode.com/contest/weekly-contest-256/problems/minimum-number-of-work-sessions-to-finish-the-tasks/
// Difficulty level should be hard and not medium
class Solution {
    int res;
    int maxSessionTime;
    int[] tasks;
    int[] sessions;
    public int minSessions(int[] tasks, int sessionTime) {
        this.res = tasks.length;
        this.maxSessionTime = sessionTime;
        this.tasks = tasks;
        this.sessions = new int[tasks.length];
        dfs(0, 0);
        return res;
    }
    
    private void dfs(int taskID, int sessionCount) {
        if (sessionCount > res) return;
        if (taskID == tasks.length) {
            res = Math.min(res, sessionCount);
            return;
        }
        for (int i = 0; i < sessionCount; i++)
            if (sessions[i] + tasks[taskID] <= maxSessionTime) { //put task into old sessions
                sessions[i] += tasks[taskID];
                dfs(taskID + 1, sessionCount);
                sessions[i] -= tasks[taskID];
            }
        sessions[sessionCount] += tasks[taskID]; //put task into new empty session bucket
        dfs(taskID + 1, sessionCount + 1);
        sessions[sessionCount] -= tasks[taskID];
    }
}
