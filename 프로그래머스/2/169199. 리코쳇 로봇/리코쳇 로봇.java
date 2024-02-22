import java.util.*;

class Solution {
    final char DISABLE = 'D';
    
    public int solution(String[] strBoard) {
        final int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};            // 움직이는 방향
        char[][] board = new char[strBoard.length][strBoard[0].length()];   // String[] -> char[][]
            
        Queue<Position> queue = new LinkedList<>(); // BFS
        HashSet<Position> set = new HashSet<>();    // 이전에 도달한 Position을 저장
        
        Position goal = null;
        
        // String[] -> char[][] 변환
        // 시작점, 도착점 초기화
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                board[y][x] = strBoard[y].charAt(x);
                if (board[y][x] == 'R') {
                    Position startPos = new Position(y, x, 0);
                    queue.add(startPos);
                    set.add(startPos);
                }
                if (board[y][x] == 'G') {
                    goal = new Position(y, x, 0);
                }
            }
        }
        
        // BFS
        while (!queue.isEmpty()) {
            Position curPos = queue.poll();
            
            for (int[] dir : dirs) {
                Position nextPos = move(dir, board, curPos);
                // BFS이기 때문에, set.contains(nextPos) -> (set에 있는 Position.numMove) <= (nextPos.numMove)
                if (!set.contains(nextPos)) {
                    set.add(nextPos);
                    queue.add(nextPos);
                    
                    // Goal과 nextPos이 같은 위치라면 
                    if (goal.equals(nextPos)) {
                        return nextPos.getNumMove();
                    }
                }
            }
        }
        
        
        return -1;
    }
    
    public Position move(int[] dir, char[][] board, Position curPos) {
        int y = curPos.getY();
        int x = curPos.getX();
        
        while (isInBoard(board, y, x) && board[y][x] != DISABLE) {
            y += dir[0];
            x += dir[1];
        }
        
        y -= dir[0];
        x -= dir[1];
        
        return new Position(y, x, curPos.getNumMove() + 1);
    }
    
    public boolean isInBoard(char[][] board, int y, int x) {
        final int maxY = board.length;
        final int maxX = board[0].length;
        
        return 0 <= y && y < maxY && 0 <= x && x < maxX;
    }
    
    class Position {
        int y;
        int x;
        int numMove;
        
        int getY() { return y; }
        int getX() { return x; }
        int getNumMove() { return numMove; }
        
        Position(int y, int x, int numMove) {
            this.y = y;
            this.x = x;
            this.numMove = numMove;
        }
        
        // 움직인 횟수와 상관없이, 좌표가 동일하면 동일한 인스턴스로 취급
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Position)) return false;
            
            return this.y == ((Position)obj).getY() && this.x == ((Position)obj).getX();
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(this.y, this.x);
        }
    }
}

/* 
* 시간 복잡도
* 각 장소를 최대 1번 방문하기 때문에 O(board의 넓이)
*/
