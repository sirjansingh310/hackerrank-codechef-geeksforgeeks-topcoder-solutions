//https://leetcode.com/explore/featured/card/january-leetcoding-challenge-2021/582/week-4-january-22nd-january-28th/3614/
// Simple Brute force implementation

class Solution {
    private int m, n;
    private int[][] mat;
    private boolean[][] isPartOfDiagnol;
    
    private void populateDiagnol(List<Integer> diagnol, int startRow, int startCol) {
        while (startRow < m && startCol < n) {
            diagnol.add(mat[startRow][startCol]);
            isPartOfDiagnol[startRow][startCol] = true;
            startRow++;
            startCol++;
        }
        
    }
    
    private void fillDiagnol(int[][] result, List<Integer> diagnol, int startRow, int startCol) {
        int index = 0;
        while (startRow < m && startCol < n) {
            result[startRow++][startCol++] = diagnol.get(index++);
        }
    }
    
    public int[][] diagonalSort(int[][] mat) {
        this.m = mat.length;
        this.n = mat[0].length;
        this.mat = mat;
        this.isPartOfDiagnol = new boolean[m][n];
        
        int result[][] = new int[m][n];
        List<List<Integer>> matrixDiagnols = new ArrayList<>();
                
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!isPartOfDiagnol[i][j]) {
                    List<Integer> diagnol = new ArrayList<>();
                    populateDiagnol(diagnol, i, j);
                    matrixDiagnols.add(diagnol);
                }
            }
        }
        
        matrixDiagnols.forEach(diagnol -> Collections.sort(diagnol));
        int currentDiagnolIndex = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (result[i][j] == 0) {
                    fillDiagnol(result, matrixDiagnols.get(currentDiagnolIndex++), i, j);
                }
            }
        }
        return result;
        
    }
}
