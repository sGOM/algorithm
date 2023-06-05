import java.util.*;

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) throws Exception {
        int[] answer = new int[queries.length];
        // 행렬 선언, 초기화
        int[][] matrix = new int[rows][columns];
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
                matrix[x][y] = x * columns + y + 1;
            }
        }
        
        // queries 순회
        for (int i = 0; i < queries.length; i++) {
            // queries[i]에 맞게 회전하고, 회전한 테두리 중 최솟값을 answer[i]에 대입
            answer[i] = rotateAndMin(matrix, queries[i]);
        }
        
        return answer;
    }
    
    // O(n), n = borderLength
    // query에 맞게 회전하고, 회전한 테두리 중 최솟값을 반환
    public int rotateAndMin(int[][] matrix, int[] query) throws Exception {
        // 회전하는 테두리의 길이
        int borderLength = ((query[2] - query[0]) + (query[3] - query[1])) * 2;
        // 반환할 최솟값 초기화
        int min = Integer.MAX_VALUE;
        
        // 회전 시작점 생성, query의 왼쪽 상단
        Position pos = new Position(query[0] - 1, query[1] - 1);
        // 다음 지점으로 옮길 값
        int num = matrix[pos.x][pos.y];
        // pos 한 칸 회전(이동)
        pos.move(query);
        
        // 테두리 길이만큼 회전
        for (int i = 0; i < borderLength; i++) {
            // 최솟값 갱신
            min = Math.min(min, num);
            
            // 다음 지점으로 옮길 값 임시 저장
            int temp = matrix[pos.x][pos.y];
            // 값 옮긺
            matrix[pos.x][pos.y] = num;
            // 다음 지점으로 옮길 값 갱신
            num = temp;
            // pos 한 칸 회전(이동)
            pos.move(query);
        }
        
        return min;
    }
    
    class Position {
        int x;
        int y;
        
        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        // query로 주어진 직사각형의 테두리 내부에서 시계방향으로 한 칸 회전
        public void move(int[] query) throws Exception {
            int x1 = query[0] - 1, y1 = query[1] - 1;
            int x2 = query[2] - 1, y2 = query[3] - 1;

            if      (this.x == x1 && this.y < y2) this.y++;
            else if (this.y == y2 && this.x < x2) this.x++;
            else if (this.x == x2 && y1 < this.y) this.y--;
            else if (this.y == y1 && x1 < this.x) this.x--;
            else throw new Exception(String.format("Next position error - x: %d, y: %d, x1: %d, y1: %d, x2: %d, y2: %d", this.x, this.y, x1, y1, x2, y2));
        }
    }
    
    // 시간 복잡도
    // O(n), n = (queries의 모든 직사각형의 테두리들의 길이 합)
}