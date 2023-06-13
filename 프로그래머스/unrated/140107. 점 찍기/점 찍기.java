class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        
        // x축을 중심으로 (0 ~ A*k)까지 (A*k <= d < (A+1)*k)
        for (long x = 0; x <= d; x += k) {
            // d와 x를 이용해 해당 x 좌표에서의 최대 y 값을 구함
            // y^2 = d^2 - x^2
            long y = (long)Math.floor(Math.sqrt((long)d * d - x * x));
            // b의 최댓값을 구함
            answer += (y / k) + 1L;
        }
        
        return answer;
    }
    
    // 시간 복잡도
    // O(n), n = (d / k)
}