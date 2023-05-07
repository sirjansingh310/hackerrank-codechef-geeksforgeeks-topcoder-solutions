// https://leetcode.com/problems/find-the-longest-valid-obstacle-course-at-each-position/

class Solution {
    public int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
        int[] result = new int[obstacles.length];
        List<Integer> lis = new ArrayList<>();
        lis.add(obstacles[0]);
        result[0] = 1;
        
        for (int i = 1; i < obstacles.length; i++) {
            if (obstacles[i] >= lis.get(lis.size() - 1)) {
                lis.add(obstacles[i]);
                result[i] = lis.size();
            } else {
                int toReplaceIndex = binarySearch(lis, obstacles[i]);
                lis.set(toReplaceIndex, obstacles[i]);
                result[i] = toReplaceIndex + 1; // since our LIS is fake LIS, the position it were to be inserted were the list size if we were to be creating our own LIS every time instead of merging it into one 
            }
        }
        
        return result;
    }
    
    private int binarySearch(List<Integer> list, int target) {
        int low = 0, high = list.size() - 1, ans = 0;
        
        while (low <= high) {
            int mid = (low + high) / 2;
            if (list.get(mid) > target) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        
        return ans;
    }
}
