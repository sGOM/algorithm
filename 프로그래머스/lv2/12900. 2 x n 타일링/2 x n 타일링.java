class Solution {
    final int DIVISOR = 1_000_000_007;
    public int solution(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        
        // 마지막에 세로를 하나 세우는 것을 제외하고 모두 처리
        for (int i = 0; i < n - 1; i++) {
            dp[i + 1] = (dp[i + 1] + dp[i]) % DIVISOR; // 세로로 하나 세우는 경우
            dp[i + 2] = (dp[i + 2] + dp[i]) % DIVISOR; // 가로로 두개 눕히는 경우
        }
        // 마지막에 세로를 하나 세우는 것 처리
        dp[n] = (dp[n] + dp[n - 1]) % DIVISOR;
        
        return dp[n];
    }
}