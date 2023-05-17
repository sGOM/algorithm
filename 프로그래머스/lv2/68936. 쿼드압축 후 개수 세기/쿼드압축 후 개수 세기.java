class Solution {
    // 정답을 저장할 배열
    int[] answer = new int[2];
    // arr를 맴버 변수로 저장해서 재귀에서 사용하기 편하게 
    int[][] data;
    
    public int[] solution(int[][] arr) {
        data = arr;
       
        // 0과 1 갯수 초기화
        for (int row = 0; row < data.length; row++) {
            for (int col = 0; col < data[0].length; col++) {
                if (data[row][col] == 0) answer[0]++;
                else                     answer[1]++;
            }
        }
        
        // 재귀
        quadTree(new Position(0, 0), data.length);
        
        return answer;
    }
    
    // (검사 시작 지점, 길이)를 넘겨 받아 해당 영역의 데이터가 모두 같으면 압축, 아니면 다시 영역을 나눠 quadTree를 실행함
    public void quadTree(Position startPos, int length) {
        // 길이가 1이면, 압축할게 없으니 종료
        if (length == 1) return;
        
        // 시작 지점과 기준 데이터를 변수로 저장
        int startRow = startPos.getRow();
        int startCol = startPos.getCol();
        int crit = data[startRow][startCol];
        
        // 해당 영역 순회
        for (int row = startRow; row < startRow + length; row++) {
            for (int col = startCol; col < startCol + length; col++) {
                // 영역에서 기준 데이터와 다른 데이터를 발견했다면
                if (data[row][col] != crit) {
                    // 영역을 4개로 나눠서 다시 검사 (재귀)
                    int nextLength = length / 2;
                    for (int dr = 0; dr < 2; dr++) {
                        for (int dc = 0; dc < 2; dc++) {
                            quadTree(new Position(startRow + dr * nextLength, startCol + dc * nextLength), nextLength);
                        }
                    }
                    return;
                }
            }
        }
        // 검사 영역이 모두 동일 한 데이터였다면 압축
        answer[crit] -= length * length - 1;
    }
    
    // 위치를 저장할 class
    class Position {
        int row;
        int col;
        
        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
        
        public int getRow() { return row; }
        public int getCol() { return col; }
    }
}