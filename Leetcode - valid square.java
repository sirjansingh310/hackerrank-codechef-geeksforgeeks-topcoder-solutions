// we have 4 points, don't know the order, need to find if it forms a valid square

// O(1) Solution by sorting points, first based on x value, and if x values are equal, sort by y value
class Solution {
    private double dist(int[] p1, int[] p2) {
        return Math.sqrt(Math.pow(p1[0] - p2[0], 2) + Math.pow(p1[1] - p2[1], 2));
    }
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        Set<String> unique = new HashSet<>();
        
        int[][] points = new int[4][2];
        points[0] = p1;
        points[1] = p2;
        points[2] = p3;
        points[3] = p4;
        
        for (int[] point: points) {
            unique.add(point[0] + "_" + point[1]);
        }
        
        if (unique.size() != 4) {
            return false;
        }
        
        
        Arrays.sort(points, (pt1, pt2) -> pt1[0] == pt2[0] ? pt2[1] - pt1[1] : pt2[0] - pt1[0]);
        // after sorting first with x, and if x are equal, sort by y
        
        return dist(points[0], points[1]) != 0 && dist(points[0], points[1]) == dist(points[1], points[3]) && dist(points[1], points[3]) == dist(points[3], points[2]) && dist(points[3], points[2]) == dist(points[2], points[0])   && dist(points[0],points[3])==dist(points[1],points[2]);
    }
}

// O(1) solution, by going over all 4!, i.e 24 permuations. Here we are looping. We can do also using recursion(swap, recur, swap back technique)

class Solution {
    private double getDistance(int[] p1, int[] p2) {
        return Math.sqrt(Math.pow(p1[0] - p2[0], 2) + Math.pow(p1[1] - p2[1], 2));
    }
    
    private boolean formsSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        // p1 is bottom left
        // p2 is bottom right
        // p3 is top right
        // p4 is top left
        double side1 = getDistance(p1, p2);
        double side2 = getDistance(p2, p3);
        double side3 = getDistance(p3, p4);
        double side4 = getDistance(p4, p1);
        
        double diagnol1 = getDistance(p1, p3);
        double diagnol2 = getDistance(p2, p4);
        
        boolean perfectSquare =  side1 == side3 && side2 == side4 && side1 == side2 && diagnol1 == diagnol2;
        
        if (perfectSquare) {
            return side1 > 0;
        }
        return false;
    }
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        Set<String> unique = new HashSet<>();
        
        int[][] points = new int[4][2];
        points[0] = p1;
        points[1] = p2;
        points[2] = p3;
        points[3] = p4;
        
        for (int[] point: points) {
            unique.add(point[0] + "_" + point[1]);
        }
        
        if (unique.size() != 4) {
            return false;
        }
        
        
        int count = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (j != i) {
                    for (int k = 0; k < 4; k++) {
                        if (k != j && k != i) {
                            for (int l = 0; l < 4; l++) {
                                if (l != k && l != j && l != i) {
                                    if (formsSquare(points[i], points[j], points[k], points[l])) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        return false;
    }
}
