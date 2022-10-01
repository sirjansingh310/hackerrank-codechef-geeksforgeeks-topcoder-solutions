// https://leetcode.com/problems/the-skyline-problem/
// https://www.youtube.com/watch?v=GSBLe8cKu0s&ab_channel=TusharRoy-CodingMadeSimple
// We used PQ, but the remove of a number in middle of pq is not logN. We can improve by using TreeMap which has insert, remove, update all in logN time
// We would need to simulate queue operations on the treemap.

class Solution {
    private class BuildingPoint implements Comparable<BuildingPoint> {
        int x;
        int height;
        boolean isStart;
        
        BuildingPoint(int x, int height, boolean isStart) {
            this.x = x;
            this.height = height;
            this.isStart = isStart;
        }
        
        @Override 
        public int compareTo(BuildingPoint bp) {
            if (this.x != bp.x) {
                return this.x - bp.x;
            } else if (this.isStart && bp.isStart) { // higher height should be picked when comparing when 2 start at same point so the other is overshadowed
                return bp.height - this.height; 
            } else if (!this.isStart && !bp.isStart) {// lower height should be picked when comparing when 2 ends at same point so the other is overshadowed
                return this.height - bp.height;
            } else if (this.isStart) { // pick the start building first when one start and other end
                return -1;
            } else {
                return 1;
            }
        }
    }
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<BuildingPoint> buildingPoints = new ArrayList<>();
        for (int[] building : buildings) {
            BuildingPoint p1 = new BuildingPoint(building[0], building[2], true);
            BuildingPoint p2 = new BuildingPoint(building[1], building[2], false);
            
            buildingPoints.add(p1);
            buildingPoints.add(p2);
        }
        
        Collections.sort(buildingPoints);
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); // max heap of heights
        pq.add(0);
        int prevMax = 0;
        
        List<List<Integer>> results = new ArrayList<>();
        
        for (BuildingPoint currentPoint : buildingPoints) {
            if (currentPoint.isStart) {
                pq.add(currentPoint.height);
            } else {
                pq.remove(currentPoint.height);
            }
            
            int currentMax = pq.peek();
            if (currentMax != prevMax) {
                results.add(new ArrayList<>(Arrays.asList(currentPoint.x, currentMax)));
                prevMax = currentMax;
            }
        }
        
        return results;
    }
}
