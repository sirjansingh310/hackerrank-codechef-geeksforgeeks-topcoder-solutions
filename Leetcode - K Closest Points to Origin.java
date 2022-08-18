// https://leetcode.com/problems/k-closest-points-to-origin/
class Point implements Comparable<Point> {
    int x;
    int y;
    double distance;
    
    public Point(int x, int y, double distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }
    
    @Override
    public int compareTo(Point other) {
        if (this.distance < other.distance) {
            return 1;
        } else if (other.distance < this.distance) {
            return -1;
        }
        return 0;
    }
    
    @Override 
    public String toString() {
        return this.x + " " + this.y;
    }
}

class Solution {
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<Point> pq = new PriorityQueue<>();// max-heap
        
        for (int[] point : points) {
            double distance = Math.sqrt(((long)point[0] * point[0]) + ((long)point[1] * point[1]));
            Point p = new Point(point[0], point[1], distance);
            if (pq.size() < k) {
                pq.add(p);
            } else if (distance < pq.peek().distance) { // if this distance is less than the Kth closest distance, we should consider it to be in our k distances
                pq.poll();
                pq.add(p);
            }
        }
        int[][] result = new int[k][2];
        int i = 0;
        while (!pq.isEmpty()) {
            Point p = pq.poll();
            result[i][0] = p.x;
            result[i][1] = p.y;
            i++;
        }
        
        return result;
    }
}
