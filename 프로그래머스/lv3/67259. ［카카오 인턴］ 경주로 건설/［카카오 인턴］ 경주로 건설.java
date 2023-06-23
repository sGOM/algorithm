import java.util.*;

class Solution {
    final int[][] MOVE = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};    // 가능한 움직임
    final int ROAD_COST = 100;
    final int CORNER_COST = 500;
    final int HORZ = 0; // 가로
    final int VERT = 1; // 세로
    final int[] DIR = {HORZ, VERT}; // 가능한 도로의 방향
    
    public int solution(int[][] board) {
        int rowLen = board.length, colLen = board[0].length;
        int[][][] dp = new int[DIR.length][rowLen][colLen];
        initDp(dp);
        Queue<Road> roadQueue = new LinkedList<>(List.of(new Road(HORZ, 0, 0), new Road(VERT, 0, 0)));
        
        // O(N^2), N = (경주로 부지 한변의 길이)
        // 새로 지을 수 있는 도로가 없을 때까지
        while (!roadQueue.isEmpty()) {
            // 지은 도로를 Queue에서 꺼내옴
            Road road = roadQueue.poll();
            
            // 움직일 수 있는 모든 방향을 순회
            for (int m = 0; m < MOVE.length; m++) {
                int nextRow = road.row + MOVE[m][0], nextCol = road.col + MOVE[m][1];   // 다음 도로 위치
                int curDir  = m % DIR.length;                                           // 다음 도로 방향
                // 다음 도로를 지었을 때, 그 도로까지의 총 비용
                int cost    = dp[road.prevDir][road.row][road.col] + (road.prevDir == curDir ? ROAD_COST : ROAD_COST + CORNER_COST);
                
                // (다음 도로가 트래 내부에 존재) && (다음 도로의 위치가 비어있음) && (해당 방향으로 이미 지어진 도로보다 비용이 쌈)
                if (isInTrack(nextRow, nextCol, rowLen, colLen) && board[nextRow][nextCol] == 0 && cost < dp[curDir][nextRow][nextCol]) {
                    // curDir 방향의 비용 갱신
                    dp[curDir][nextRow][nextCol] = cost;
                    // 비용이 갱신된 경우, Queue에 추가
                    roadQueue.add(new Road(curDir, nextRow, nextCol));
                }
            }
        }
        
        // 총 건설비용이 가장 싼 경주로를 반환
        return Math.min(dp[HORZ][rowLen - 1][colLen - 1], dp[VERT][rowLen - 1][colLen - 1]);
    }
    
    // O(N^2), N = (경주로 부지 한변의 길이)
    // DP 초기화
    public void initDp(int[][][] dp) {
        for (int dir : DIR) {
            for (int row = 0; row < dp[dir].length; row++) {
                Arrays.fill(dp[dir][row], Integer.MAX_VALUE);
            }
            // 시작지점은 비용 0으로 초기화
            dp[dir][0][0] = 0;
        }
    }
    
    // 트랙 내부에 존재하는지 확인
    public boolean isInTrack(int row, int col, int rowLen, int colLen) {
        return 0 <= row && row < rowLen && 0 <= col && col < colLen;
    }
    
    // 현재 위치와 도로가 이어진 방향을 저장
    class Road {
        int prevDir;
        int row;
        int col;
        
        Road(int prevDir, int row, int col) {
            this.prevDir = prevDir;
            this.row = row;
            this.col = col;
        }
    }
    
    // O(N^2), N = (경주로 부지 한변의 길이)
    // 경주로 탐색에서 모든 경우의 수를 탐색하는 것이 아닌, 현재 최적값으로 건설된 도로에서 다음 도로를 건설하는 것이기 때문
}
