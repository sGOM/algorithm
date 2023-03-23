class Solution {
    int solution(int[][] land) {
        int rowLen = land.length;       // 행 갯수
        int colLen = land[0].length;    // 열 갯수
        int[][] dp = new int[rowLen][colLen];   // 동적 계획법
        
        // 초기값 설정
        for (int col = 0; col < colLen; col++) dp[0][col] = land[0][col];
        
        // 0번째 행은 건너뛰고 1번째 행부터 순회
        for (int row = 1; row < rowLen; row++) {
            // dp의 새로운 행의 값 갱신
            for (int col = 0; col < colLen; col++) {
                for (int prevCol = 0; prevCol < colLen; prevCol++) {
                    // 현재 열과 더하고자하는 이전 값의 열이 같으면 건너 뜀
                    if (prevCol == col) continue;
                    // dp에 저장된 현재 값과 다른 경우의 값 중 최대값 적용
                    dp[row][col] = Math.max(dp[row][col], land[row][col] + dp[row - 1][prevCol]);
                }
            }
        }

        // dp의 마지막 행에서 최댓값 구하기
        int answer = Integer.MIN_VALUE;
        for (int ret : dp[rowLen - 1]) {
            answer = Math.max(ret, answer);
        }
        
        return answer;
    }
}