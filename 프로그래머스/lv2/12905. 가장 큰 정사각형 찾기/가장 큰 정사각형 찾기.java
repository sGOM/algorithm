import java.util.*;

class Solution {
    public int solution(int[][] board) {
        // 가장 큰 정사각형에 한변의 길이
        int max = 0;
        // 우측과 상단의 변은 순회하지 않음
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                // 0이 아닐 때
                if (board[row][col] != 0) {
                    if (row != 0 && col != 0) {
                        // 각 좌표에 좌상단 방향으로 길이가 board[row][col]인 정사각형을 가짐
                        // board[row][col] = n이려면
                        // (board[row - 1][col - 1], board[row][col - 1], board[row - 1][col]) 중 최소 하나는 n - 1, 나머지는 n - 1 이상이어야함
                        board[row][col] = min(board[row][col - 1], board[row - 1][col], board[row - 1][col - 1]) + 1;
                    }
                    max = Math.max(max, board[row][col]);
                }
            }
        }

        return max * max;
    }
    
    public int min(int i1, int i2, int i3) {
        return Math.min(i1, Math.min(i2, i3));
    }
    // 시간복잡도
    // O(n * k), n = row의 크기, k = col의 크기
}