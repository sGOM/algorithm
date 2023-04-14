import java.util.*;

// 전형적인 DFS를 이용한 길찾기 문제
class Solution {
    public int solution(int[][] maps) {
        // 변수 초기화
        int[][]     dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};   // 한 번에 움직일 수 있는 방법 수
        int         rowLen = maps.length;                       // 최대 row
        int         colLen = maps[0].length;                    // 최대 col
        int         answer = 0;                                 // 답
        boolean[][] isVisited = new boolean[rowLen][colLen];    // 해당 위치의 방문 여부
        Queue<Position> queue = new LinkedList<>();             // dfs를 위한 Queue
        
        // 출발 지점은 고정되어있고 밟고 시작
        isVisited[0][0] = true;
        answer++;
        queue.add(new Position(0, 0));
        
        // bfs
        while(!queue.isEmpty()) {
            // 시작점으로부터 `answer + 1` 칸 만큼 움직인 위치들을 저장해둘 List
            List<Position> nextPositions = new ArrayList<>();
            
            // 시작점으로부터 `answer` 칸 만큼 움직인 위치들에 대한 처리
            while(!queue.isEmpty()) {
                // 검사할 위치
                Position curPos = queue.poll();
                // 현재 위치가 목적지와 같다면 현재 움직인 칸수를 return
                if (curPos.row == rowLen - 1 && curPos.col == colLen - 1) return answer;
                
                // 주어진 모든 가능한 움직임에 대해 실행
                for(int[] d : dir) {
                    Position nextPos = curPos.move(d[0], d[1]);
                    // 다음 진행 위치가 맵 안쪽이고, 지나갈 수 있는 칸이고, 방문하지 않았다면
                    if (isInMap(nextPos, rowLen, colLen) && maps[nextPos.row][nextPos.col] == 1 && !isVisited[nextPos.row][nextPos.col]) {
                        // 지나가면서 방문 표시
                        isVisited[nextPos.row][nextPos.col] = true;
                        nextPositions.add(nextPos);
                    }
                }
            }
            
            // 시작점으로부터 `answer` 칸 만큼 움직인 위치들은 목적지에 도달하지 못했으므로 
            // `answer + 1`칸 만큼 움직인 위치들을 다시 Queue에 추가하고 answer = answer + 1
            queue.addAll(nextPositions);
            answer++;
        }
        
        
        // 목적지에 도착하지 못했다면 -1, 아니라면 그 중 최솟값을 반환
        return -1;
    }
    
    // 맵 내부에 잇는지 판단
    public boolean isInMap(Position pos, int rowLen, int colLen) {
        return 0 <= pos.row && pos.row < rowLen && 0 <= pos.col && pos.col < colLen;
    }
    
    class Position {
        int row;
        int col;
        
        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
        
        public Position move(int rowMove, int colMove) {
            return new Position(this.row + rowMove, this.col + colMove);
        }
    }
}
    
/* 처음에는 DFS로 시도하려 했으나 효율성 검사에서 모두 실패함
* 아래는 그 이유에 대한 고찰
* 
* 먼저, 해당 문제는 [최단 거리]를 구하는 문제이다.
* DFS는 해당 문제에서 무조건 BFS보다 더 많은 동작과 시간을 필요하게 된다.
* 그 이유는 2가지 존재한다
*
* 1. DFS는 이미 방문한 칸을 재방문한다.
* isVisited를 사용하니 재방문 할 일이 없다고 생각할 수도 있지만, 그건 해당 분기점에서의 얘기이다
*  -----
* |1 1 0| 왼쪽 같은 경우가 있다면 (아래 -> 오른쪽 -> 위)와 (오른쪽 -> 아래 -> 왼쪽) 이 2 경우를 모두 탐색할 것이다
* |1 1 0| 하지만 이 2 경우에서 무려 (0, 0)을 제외한 3개의 위치를 동일하게 탐색한다
* |0 0 0|
*  -----
*
* 2. BFS는 목적지에 도달하는 순간 종료된다 (목적지에 도달하는 순간이 최단거리)
* BFS는 모든 경우의 수에 대해 동일하게 뻗어나가므로 그 중 하나라도 목적지에 도달하는 순간이 최단거리이다
* 반면 DFS는 언제가 최단거리인지 알 수 있는 방법이 없기 때문에 앞서 설명한 1번 문제점이 아니더라도 무조건 더 많은 동작을 할 수 밖에 없다
*/

//     // dfs, 길찾기
//     public void dfs(int curRow, int curCol, int cnt) {
//         // 현재 진행 중인 길의 길이가 현존하는 최솟값보다 클 경우 그만둠
//         if (min <= cnt) return;
//         // 적진에 도착했다면 최솟값 갱신
//         if (curRow == rowLen - 1 && curCol == colLen - 1) min = cnt;
        
//         // 모든 방향을 순회
//         for(int[] d : dir) {
//             int nextRow = curRow + d[0];
//             int nextCol = curCol + d[1];
            
//             // 다음 진행방향이 맵 안쪽이고, 지나갈 수 있는 칸이고, 방문하지 않았다면
//             if (isInMap(nextRow, nextCol) && map[nextRow][nextCol] == 1 && !isVisited[nextRow][nextCol]) {
//                 // 지나가면서 방문 표시
//                 isVisited[nextRow][nextCol] = true;
//                 dfs(nextRow, nextCol, cnt + 1);
//                 // 돌아오면서 방문 표시 삭제
//                 isVisited[nextRow][nextCol] = false;
//             }
//         }
//     }
