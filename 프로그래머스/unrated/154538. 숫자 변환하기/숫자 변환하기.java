import java.util.*;

class Solution {
    // DP를 이용한 최소 값 기록
    public int solution(int x, int y, int n) {
        // DP 초기화
        int[] dp = new int[y + 1];
        Arrays.fill(dp, -1);
        dp[y] = 0;
        
        // y부터 (x + 1)까지 순회 (역방향 순회, 순방향의 경우 int형 변수의 overflow가 발생할 수 있음)
        for (int i = y; x < i; i--) {
            // i까지 도달하는 최소 연산 횟수는 구해진 상황
            // dp[i]가 -1일 경우 해당 수에 도달하는 방법이 없음
            if (dp[i] != -1) {
                // i에 2를 나누는 것이 (i * 2)에 도달하는 최소 연산인지 확인
                if (i % 2 == 0) dp[i / 2] = Math.min(dp[i] + 1, (dp[i / 2] == -1 ? Integer.MAX_VALUE : dp[i / 2]));
                // i에 3을 나누는 것이 (i * 3)에 도달하는 최소 연산인지 확인
                if (i % 3 == 0) dp[i / 3] = Math.min(dp[i] + 1, (dp[i / 3] == -1 ? Integer.MAX_VALUE : dp[i / 3]));
                // i에 n을 빼는   것이 (i + n)에 도달하는 최소 연산인지 확인
                if (i - n >= x) dp[i - n] = Math.min(dp[i] + 1, (dp[i - n] == -1 ? Integer.MAX_VALUE : dp[i - n]));
            }
        }
        
        // dp[x]를 반환
        return dp[x];
    }
}
// 이렇게 보여도 시간 복잡도 O(y - x)임