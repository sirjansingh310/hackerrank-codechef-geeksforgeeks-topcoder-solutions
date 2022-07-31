// smart one swap O(N^2) Solution
class Solution {
    public boolean isValidSudoku(char[][] board) {
        Set<String> seenStrings = new HashSet<>();
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                String rowKey = board[i][j] + "row_" + i;
                String colKey = board[i][j] + "col_" + j;
                String squareKey = board[i][j] + "sq_" + (i / 3) + "sq_" + (j / 3); // 9 * 9 divided into boxes of size 3*3
                
                // .add() returns false if already present, true if added now
                if (!(seenStrings.add(rowKey) && seenStrings.add(colKey) && seenStrings.add(squareKey))) {
                    return false;
                }
            }
        }
        
        return true;
    }
}

// simple O(N^2) Brute force
class Solution {
    public boolean isValidSudoku(char[][] board) {
        boolean validRows = validateRows(board);
        validRows &= validateColumns(board);
        validRows &= validateSquares(board);
        return validRows;
    }
    
    private boolean validateRows(char[][] board) {
        for (int i = 0; i < 9; i++) {
            Set<Character> seen = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                if (Character.isDigit(board[i][j]) && seen.contains(board[i][j])) {
                    return false;
                } else if (Character.isDigit(board[i][j])) {
                    seen.add(board[i][j]);
                }
            }
        }
        return true;
    }
    
    private boolean validateColumns(char[][] board) {
        for (int j = 0; j < 9; j++) {
            Set<Character> seen = new HashSet<>();
            for (int i = 0; i < 9; i++) {
                if (Character.isDigit(board[i][j]) && seen.contains(board[i][j])) {
                    return false;
                } else if (Character.isDigit(board[i][j])) {
                    seen.add(board[i][j]);
                }
            }
        }
        return true;
    }
    
    private boolean validateSquares(char[][] board) {
        int row = 0;
        
        while (row < board.length) {
            int col = 0;
            
            // check 3 squares
            for (int i = 0; i < 3; i++) {
                Set<Character> seen = new HashSet<>();
                for (int k = row; k < row + 3; k++) {
                    for (int l = col; l < col + 3; l++) {
                        if (Character.isDigit(board[k][l]) && seen.contains(board[k][l])) {
                            return false;
                        } else if (Character.isDigit(board[k][l])) { 
                            seen.add(board[k][l]);
                        }
                    }
                }
                col += 3;
            }
            row += 3;
        }
        
        return true;
    }
}
                                  
                                                     
