import java.util.*;

class Solution {
    // 빈 블록
    final char BLANK = ' ';
    // 검사해야할 블록 범위
    final int BLOCK_RANGE = 1;
    
    public int solution(int m, int n, String[] sBoard) {
        int answer = 0;
        boolean isChanged = true;
        
        char[][] board = new char[m][];
        
        for (int i = 0; i < m; i++) {
            board[i] = sBoard[i].toCharArray();
        }
        
        // 저번 탐색에서 블럭이 삭제되었으면 반복
        while (isChanged) {
            // 삭제될 블럭의 위치를 저장할 Queue
            Queue<Position> deleting = new LinkedList<>();
            // 이번 탐색에서 블럭 삭제 여부를 저장
            isChanged = false;
            
            // 삭제할 블럭의 위치 탐색
            for (int row = 0; row < m - BLOCK_RANGE; row++) {
                for (int col = 0; col < n - BLOCK_RANGE; col++) {
                    if (isCanDelete(row, col, board)) {
                        deleting.add(new Position(row, col));
                    }
                }
            }
            
            // 삭제할 블럭이 존재한다면
            if (!deleting.isEmpty()) {
                isChanged = true;
            
                // 블럭 삭제
                while (!deleting.isEmpty()) {
                    Position p = deleting.poll();
                    for (int dr = 0; dr <= BLOCK_RANGE; dr++) {
                        for (int dc = 0; dc <= BLOCK_RANGE; dc++) {
                            if (board[p.getRow() + dr][p.getCol() + dc] != BLANK) {
                                board[p.getRow() + dr][p.getCol() + dc] = BLANK;
                                answer++;
                            }
                        }
                    }
                }

                // 블럭 위치 갱신
                for (int col = 0; col < n; col++) {
                    int blankCnt = 0;
                    for (int row = m - 1; 0 <= row; row--) {
                        if (board[row][col] == BLANK) { 
                            blankCnt++;
                        } else if (0 < blankCnt) {
                            board[row + blankCnt][col] = board[row][col];
                            board[row][col] = BLANK;
                        }
                    }
                }
            }
        }
        
        return answer;
    }
    
    // 해당 블럭이 삭제 가능한지 확인
    boolean isCanDelete(int row, int col, char[][] board) {
        if (board[row][col] == BLANK) return false;
        for (int dr = 0; dr <= BLOCK_RANGE; dr++) {
            for (int dc = 0; dc <= BLOCK_RANGE; dc++) {
                if (board[row][col] != board[row + dr][col + dc]) return false;
            }
        }
        
        return true;
    }
    
    class Position {
        int row;
        int col;
        
        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
        
        int getRow() { return row; }
        int getCol() { return col; }
    }
}