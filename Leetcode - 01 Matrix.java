// https://leetcode.com/explore/challenge/card/july-leetcoding-challenge-2021/612/week-5-july-29th-july-31st/3831/
// 5 Star problem!! Awesome

// Given a matrix of 0's and 1's, return matrix of distances to nearest 0's. We can traverse in top, bottom, left, right directions.
// Analysis: For mat[i][j] == 0, no change, for mat[i][j] == 1, update.

// BFS (Topo sort): Time: O(M * N). Space: O(M * N). Queue up all zeros. now do bfs. If we reach node with value 1, it is guarenteed that this update
// is fastest as the source was 0 for sure. Once updated, there will be no need to check again as the whole graph is unweighted and this is where BFS shines.

class Solution {
    public int[][] updateMatrix(int[][] mat) {
        // topo sort from all 0 cells first, bfs approach
        
        Queue<Integer> queue = new LinkedList<>();
        int[][] result = new int[mat.length][mat[0].length];
        
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 0) {
                    queue.add(i);
                    queue.add(j);
                    result[i][j] = 0;
                } else {
                    result[i][j] = -1;
                }
            }
        }
        int[] xDir = {1, -1, 0, 0};
        int[] yDir = {0, 0, 1, -1};
        
        while (!queue.isEmpty()) {
            int currentX = queue.poll();
            int currentY = queue.poll();
            
            for (int i = 0; i < 4; i++) {
                int childX = currentX + xDir[i], childY = currentY + yDir[i];
                if (childX >= 0 && childX < mat.length && childY >=0 && childY < mat[0].length &&
                   result[childX][childY] == -1) {
                    result[childX][childY] = result[currentX][currentY] + 1;
                    queue.add(childX);
                    queue.add(childY);
                }
            }
        }
        
        return result;
        
    }
}


// Dynamic Programming. TC O(M * N), Space: O(1). We will modify original matrix, as no need of queue here.

// Analysis: We can reach a node in 4 directions. Top, down, left, right. If dp is precomputed, we need to assign 1 + min of all four to current mat[i][j] if 
// current mat[i][j] != 0.

// Problem: Infinite loop!! We ask right, right will ask left. We ask top, top will need computation of bottom.

// Smart DP: We will break it into 2 problems. We start at 0, 0 and we can go only right or bottom. Update mat
// We now start at mat.length - 1, mat[0].length - 1, we can go top and left! What an approach


class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int UNPROCESSED = 999999;
        int rowCount = mat.length, colCount = mat[0].length;
        // dp approach. Given we can come to a point in 4 directions, 
        // left right bottom top. if dp for these 4 is already calculated, we can easily
        // compute the current dp
        // The problem is that, it will be infinite!!. you go to find right, it will find left..
        
        // So we break it into 2 problems. DP if we can go down and right, traversing from left to right top to bottom
        // we can go up and left, traversing from bottom
        
        // we can go to right and down.
        
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                if (mat[i][j] == 1) {
                    mat[i][j] = UNPROCESSED;
                }
            }
        }
      
        // init row zero can only be reached from left
        for (int i = 1; i < colCount; i++) {
            if (mat[0][i] != 0) {
                mat[0][i] = 1 + mat[0][i - 1];
            }
        }
        // init
        for (int i = 1; i < rowCount; i++) {
            if (mat[i][0] != 0) {
                mat[i][0] = 1 + mat[i - 1][0];
            }
        }
        
        for (int i = 1; i < rowCount; i++) {
            for (int j = 1; j < colCount; j++) {
                if (mat[i][j] == 0) {
                    continue;
                }
                int newDist = 1 + Math.min(mat[i - 1][j], mat[i][j - 1]);
                
                if (mat[i][j] == UNPROCESSED) {
                    mat[i][j] = newDist;
                } else {
                    mat[i][j] = Math.min(mat[i][j], newDist);
                }
            }
        }
        
        
        // we can go left and top
        
        // init, a node can be processed in above steps, take into consideration.
        for (int i = colCount - 2; i >= 0; i--) {
            if (mat[rowCount - 1][i] == 0) {
                continue;
            }
            int newDist = 1 + mat[mat.length - 1][i + 1];
            if (mat[rowCount - 1][i] == UNPROCESSED) {
                mat[rowCount - 1][i] = newDist;
            } else {
                mat[rowCount - 1][i] = Math.min(mat[rowCount - 1][i], newDist);
            }
        }
        
        // init, a node can be processed in above steps, take into consideration.
        for (int i = rowCount - 2; i >= 0; i--) {
            if (mat[i][colCount - 1] == 0) {
                continue;
            }
            int newDist = 1 + mat[i + 1][colCount - 1];
            if (mat[i][colCount - 1] == UNPROCESSED) {
                mat[i][colCount - 1] = newDist;
            } else {
                mat[i][colCount - 1] = Math.min(mat[i][colCount - 1], newDist);
            }
        }
        
        for (int i = rowCount - 2; i >= 0; i--) {
            for (int j = colCount - 2; j >=0; j--) {
                if (mat[i][j] == 0) {
                    continue;
                }
                int newDist = 1 + Math.min(mat[i + 1][j], mat[i][j + 1]);
                
                if (mat[i][j] == UNPROCESSED) {
                    mat[i][j] = newDist;
                } else {
                    mat[i][j] = Math.min(mat[i][j], newDist);
                }
            }
        }
        
        return mat;
    }
}
