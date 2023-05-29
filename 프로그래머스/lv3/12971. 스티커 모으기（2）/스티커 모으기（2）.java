import java.util.*;

class Solution {
    public int solution(int sticker[]) {
        int answer = 0;
        int[] dp = new int[sticker.length];
        
        // 아무데나 연속된 3개의 스티커를 확인했을 때, 뜯어낸 스티커의 갯수는 반드시 1개 이상, 2개 이하임
        // 따라서 0 ~ 2번째 스티커가 반드시 뜯어지는 경우를 상정해서 각각 dp를 이용한 최대값을 찾음
        for (int s = 0; s < 3 && s < sticker.length; s++) {
            // 3가지 경우 각각 초기화
            dp[s] = sticker[s];
            answer = Math.max(answer, dp[s]);
            // O(n), n = sticker.length, 1번의 반복마다 4번의 대입과 9번의 비교, 그러나 상수 배
            // 다음 뜯어낼 스티커는 반드시 2 ~ 3 칸 뒤에 존재
            for (int i = s; i + 2 < sticker.length; i++) {
                // 0번째 스티커부터 뜯어낸 경우, 마지막 스티커는 뜯을 수 없음
                if (i + 2 < sticker.length - (s == 0 ? 1 : 0)) {
                    dp[i + 2] = Math.max(dp[i + 2], dp[i] + sticker[i + 2]);
                    answer = Math.max(answer, dp[i + 2]);
                }
                if (i + 3 < sticker.length - (s == 0 ? 1 : 0)) {
                    dp[i + 3] = Math.max(dp[i + 3], dp[i] + sticker[i + 3]);
                    answer = Math.max(answer, dp[i + 3]);
                }
            }
            // O(n), n = sticker.length
            // 각 경우의 dp 초기화
            Arrays.fill(dp, 0);
        }

        return answer;
    }
    // 시간 복잡도 
    // O(n), n = sticker.length
}