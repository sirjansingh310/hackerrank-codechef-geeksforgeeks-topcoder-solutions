//https://leetcode.com/explore/challenge/card/may-leetcoding-challenge-2021/598/week-1-may-1st-may-7th/3729/
class Solution {
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, (c1, c2) -> c1[1] - c2[1]); // sort courses by deadline, so we can "consider" as many courses as possible.
        PriorityQueue<Integer> takenCoursesDurationPq = new PriorityQueue<>((c1, c2) -> c2 - c1);// for the "considered" course, if it is not possible to take it,
        // i.e totalTime + course time > course deadline, we can remove already added course with maximum course time. 
        // Think about doing this, no loss in count, we remove one to add one. But our total time spend reduces, so it will be better in long run(i.e for upcoming courses)
        long totalTime = 0L;
        for (int i = 0; i < courses.length; i++) {
            if (totalTime + courses[i][0] <= courses[i][1]) {
                totalTime += courses[i][0];
                takenCoursesDurationPq.add(courses[i][0]);
            } else if (!takenCoursesDurationPq.isEmpty() && takenCoursesDurationPq.peek() > courses[i][0]) {
                totalTime -= takenCoursesDurationPq.poll();
                totalTime += courses[i][0];
                takenCoursesDurationPq.add(courses[i][0]);
            }
        }
        System.out.println(takenCoursesDurationPq);
        return takenCoursesDurationPq.size();
    }
}
