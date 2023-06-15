class Solution {
    final int DISTANCE = 2;     // 거리두기로 준수해야하는 거리 (맨해튼 거리 기준)
    final char PARTITION = 'X'; // 파티션
    final char PERSON = 'P';    // 사람
    int[][] dir = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};   // 방향
    
    public int[] solution(String[][] strPlaces) {
        // places[i][r][c] -> i번째 대기실에 r행, c열
        char[][][] places = new char[strPlaces.length][strPlaces[0].length][];
        int[] answer = new int[strPlaces.length];
        initPlace(places, strPlaces);
        
        // 대기실을 순회하며
        for (int i = 0; i < strPlaces.length; i++) {
            // 거리두기 규칙을 준수했는지 확인
            answer[i] = (isObserveRule(places[i]) ? 1 : 0);
        }
        
        return answer;
    }
    
    // O(n * a), n = 대기실 갯수, a = 대기실 넓이
    // 대기실 변수 초기화
    public void initPlace(char[][][] places, String[][] strPlaces) {
        for (int i = 0; i < strPlaces.length; i++) {
            for (int row = 0; row < strPlaces[i].length; row++) {
                places[i][row] = strPlaces[i][row].toCharArray();
            }
        }
    }
    
    // O(a + p * (dir - 1)^dis), a = 대기실 넓이, p = 사람 수, dir = 해당 칸과 인접한 칸 수, dis = 거리두기 최대 거리
    // 거리두기 규칙 준수 여부를 확인
    public boolean isObserveRule(char[][] place) {
        int rowLen = place.length;
        int colLen = place[0].length;
        
        // 대기실을 순회
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                // 사람이 존재하면, DISTANCE칸 내에 사람이 존재하는지 확인
                if (place[row][col] == PERSON && isAnotherPersonNear(place, row, col, -dir.length, 1)) 
                    return false;
            }
        }
        
        return true;
    }
    
    // O((dir - 1)^dis), dir = 해당 칸과 인접한 칸 수, dis = 거리두기 최대 거리
    public boolean isAnotherPersonNear(char[][] place, int row, int col, int prevDir, int distance) {
        // 모든 방향을 순회
        for (int d = 0; d < dir.length; d++) {
            // 되돌아가는 방향은 탐색 X
            if (d == (prevDir + dir.length / 2) % dir.length) continue;
            
            int nextRow = row + dir[d][0];
            int nextCol = col + dir[d][1];
            if (isInRoom(place, nextRow, nextCol)) {
                // 해당칸에 사람이 존재하는 경우 
                if (place[nextRow][nextCol] == PERSON) return true;
                // 파티션으로 막혀있거나, 검사 거리를 벗어나지 않는 경우 재귀적으로 탐색하고, 사람이 존재하는 경우 
                if (place[nextRow][nextCol] != PARTITION 
                    && distance < DISTANCE 
                    && isAnotherPersonNear(place, nextRow, nextCol, d, distance + 1)) 
                    return true;
            }
        }
        
        return false;
    }
    
    // 해당 좌표가 대기실 내부의 좌표인지 검사
    public boolean isInRoom(char[][] place, int row, int col) {
        int rowLen = place.length;
        int colLen = place[0].length;
        return 0 <= row && row < rowLen && 0 <= col && col < colLen;
    }
    
    // 시간 복잡도
    // O(a + p * (dir - 1)^dis), a = 대기실 넓이, p = 사람 수, dir = 해당 칸과 인접한 칸 수, dis = 거리두기 최대 거리
    // 0 <= p <= a이나 p와 dis의 값이 커질 수록 isAnotherPersonNear함수가 일찍 종료될 가능성이 높아짐
}