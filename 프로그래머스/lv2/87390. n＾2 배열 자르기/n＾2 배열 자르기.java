class Solution {
    public int[] solution(int n, long left, long right) {
        // 정답 배열 할당
        int[]   answer      = new int[(int)(right - left + 1L)];
        int     leftRow     = (int)(left / n);  // 시작 row     // row : 0 ~ n-1
        int     leftCol     = (int)(left % n);  // 시작 col     // col : 0 ~ n-1
        int     rightRow    = (int)(right / n); // 종료 row
        int     rightCol    = (int)(right % n); // 종료 col
        
        // 시작 row부터 종료 row까지 순회
        for (int row = leftRow, idx = 0; row <= rightRow; row++) 
            // 시작 row면 col을 [leftCol, n-1     ] 범위로
            // 종료 row면 col을 [0      , rightCol] 범위로 순회
            for (int col = ((leftRow == row) ? leftCol : 0); col <= ((row == rightRow) ? rightCol : n-1); col++)
                // 정답 배열의 index를 1씩 옮겨가며 col <= row면 (row + 1), 아니면 (col + 1)
                // (row + 1), (col + 1)에서 1을 더하는 이유는 범위를 [0, n-1] -> [1, n]으로 바꾸기 위함
                answer[idx++] = (col <= row) ? row + 1 : col + 1;   
            
        return answer;
    }
}