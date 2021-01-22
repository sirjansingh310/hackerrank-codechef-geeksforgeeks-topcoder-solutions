/// https://leetcode.com/problems/merge-intervals/

class Solution {
    private class Interval {
        int start;
        int end;
        
        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
        
        public String toString() {
            return "[" + start + " " + end + "]";
        }
    }
    private boolean isOverlap(Interval interval, int[] next) {
        return interval.end >= next[0];
    }
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (interval1, interval2) -> interval1[0] - interval2[0]);
        
        List<Interval> list = new ArrayList<>();
        list.add(new Interval(intervals[0][0], intervals[0][1]));
        
        for (int i = 1; i < intervals.length; i++) {
            Interval prev = list.get(list.size() - 1);
            if (isOverlap(prev, intervals[i])) {
                prev.end = Math.max(prev.end, intervals[i][1]);
            } else {
                list.add(new Interval(intervals[i][0], intervals[i][1]));
            }
        }
        
        int[][] result = new int[list.size()][2];
        int i = 0;
        for (Interval interval : list) {
            result[i] = new int[]{interval.start, interval.end};
            i++;
        }
        
        return result;
    }
}
