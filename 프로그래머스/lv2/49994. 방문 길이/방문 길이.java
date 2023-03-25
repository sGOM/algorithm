import java.util.*;
import java.util.stream.*;

class Solution {
    // 좌표 갯수
    final int nCoord = 11;
    // 방향 배열
    final char[] dirArray = {'U', 'R', 'D', 'L'};
    public int solution(String dirs) {
        // 각 좌표에서 [상, 하, 좌, 우]로 움직이는 모든 경우의 수
        boolean[][][] isVisited = new boolean[nCoord][nCoord][dirArray.length];
        // 그냥 인덱스 꺼내기 쉬우라고 만들어둔 사전
        HashMap<Character, Integer> dirIdxDict = 
            IntStream.range(0, dirArray.length)
                .boxed()
                .collect(HashMap::new, (m, i) -> m.put(dirArray[i], i), HashMap::putAll);
        
        int answer = 0;
        
        // 초기 시작 지점 (5, 5)
        int curRow = 5, curCol = 5;
        int nextRow, nextCol;
        
        // 주어진 명령을 순회
        for (char dir : dirs.toCharArray()) {
            nextRow = curRow;
            nextCol = curCol;
            switch (dir) {
                case 'U': nextRow--; break;
                case 'R': nextCol++; break;
                case 'D': nextRow++; break;
                case 'L': nextCol--; break;
            }
            // 움직일 좌표가 좌표평명 밖이면 무시하고 다음 명령으로
            if (!verify(nextRow, nextCol)) continue;
            
            // 움직인 방향의 index를 얻음
            int dirIdx = dirIdxDict.get(dir);
            // 해당 길을 가본적이 없다면
            if (!isVisited[curRow][curCol][dirIdx]) {
                // 해당 길을 지난 것으로 체크하고, 정답 + 1
                isVisited[curRow][curCol][dirIdx] = true;
                isVisited[nextRow][nextCol][(dirIdx + 2) % 4] = true;
                answer++;
            }
            // 이동
            curRow = nextRow;
            curCol = nextCol;
        }
        
        return answer;
    }
    
    // 좌표평면 내부인지 검사
    public boolean verify(int row, int col) {
        return 0 <= row && row < nCoord && 0 <= col && col < nCoord;
    }
}