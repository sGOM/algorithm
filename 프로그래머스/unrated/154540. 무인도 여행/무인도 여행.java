import java.util.*;

class Solution {
    // 정답을 저장할 Collection
    LinkedList<Integer> answer;
    // 방문 여부
    boolean[][] isVisited;
    // String[]으로 된 지도 정보를 char[][]로 변환해 저장
    char[][] map;
    // 상하좌우
    int[][] dirs = {{ 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 }};
    
    public int[] solution(String[] strMaps) {
        // 맴버 변수 초기화
        answer      = new LinkedList<>();
        isVisited   = new boolean[strMaps.length][strMaps[0].length()];
        map         = new char[strMaps.length][strMaps[0].length()];
        for (int i = 0; i < strMaps.length; i++) {
            map[i] = strMaps[i].toCharArray();
        }
        
        // 'X'의 경우 넘어가고, 식량이 있는 경우 해당 섬의 전체 식량 수를 계산 
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[0].length; col++) {
                if (map[row][col] == 'X') {
                    isVisited[row][col] = true;
                } else if (!isVisited[row][col]) {
                    answer.add(calcFood(row, col));
                }
            }
        }
        
        // answer가 비어있다면 -1 반환
        if (answer.isEmpty()) return new int[] {-1};
        
        // 오름차순으로 정렬하고 int[]로 반환
        return answer.stream()
                    .mapToInt(Integer::intValue)
                    .sorted()
                    .toArray();
    }
    
    // 해당 섬의 총 식량 계산
    public int calcFood(int row, int col) {
        // (지도 내부의 좌표가 아닌 경우) || (이미 방문한 경우)
        if (!isInMap(row, col) || isVisited[row][col]) return 0;
        
        // 방문 표시
        isVisited[row][col] = true;
        // 'X'라면 0을 반환
        if (map[row][col] == 'X') return 0;
        
        // 식량 계산
        int sum = map[row][col] - '0';
        // 상하좌우에 식량 합산
        for (int[] dir : dirs) {
            sum += calcFood(row + dir[0], col + dir[1]);
        }
        
        return sum;
    }
    
    // 지도 내부의 좌표인지 확인
    public boolean isInMap(int row, int col) {
        return 0 <= row && row < map.length && 0 <= col && col < map[0].length;
    }
    
    // 시간 복잡도
    // O(n * m), n = maps.length, m = maps[0].length()
}