class Solution {
    
    // store word length
    int l;
    
    // store board rows
    int m;
    
    // store board cols
    int n;
    
    // 4 possible directions
    int[][] directions = {{0,1},{0,-1},{1,0},{-1,0}};
    
    // DFS function to check if word can be formed from (i, j)
    public boolean find(char[][] board, int i, int j, String word, int idx) {
        
        // if all characters matched
        if (idx >= l)
            return true;

        // invalid cell or character mismatch
        if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] != word.charAt(idx))
            return false;

        // save current character
        char temp = board[i][j];
        
        // mark cell as visited
        board[i][j] = '$';

        // try all 4 directions
        for (int[] dir : directions) {
            int i_ = i + dir[0];
            int j_ = j + dir[1];

            // if word found from next cell
            if (find(board, i_, j_, word, idx + 1))
                return true;
        }

        // restore original character
        board[i][j] = temp;
        
        // word not found from this path
        return false;
    }

    public boolean exist(char[][] board, String word) {
        
        // number of rows
        m = board.length;
        
        // number of columns
        n = board[0].length;
        
        // word length
        l = word.length();

        // edge case
        if (m * n < 1)
            return false;

        // try starting from every cell
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                
                // start DFS if first character matches
                if (board[i][j] == word.charAt(0) && find(board, i, j, word, 0)) {
                    return true;
                }
            }
        }

        // word not found anywhere
        return false;
    }
}






I’ll solve this using DFS with backtracking. I’ll start from every cell of the board, 
and whenever the current cell matches the first character of the word, 
I’ll try to build the word from there. From one cell, I can move only in 4 directions — up, down, left, and right. 
While exploring a path, I’ll mark the current cell as visited so that 
I don’t use the same cell again in the same word. If I’m able to match all characters of the word, 
I return true. If a path fails, I backtrack by restoring the cell and try another direction. 
If none of the starting points work, then the word does not exist in the board.