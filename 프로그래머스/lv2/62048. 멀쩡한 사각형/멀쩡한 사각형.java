class Solution {
    public long solution(long w, long h) {
        // (전체 정사각형) - (선이 격자점을 지나지 않는 경우 잘린 정사각형)
        long answer = (w * h) - (w + h - 1);
        // 최대 공약수
        long gcd = getGcd(w, h);
        
        // 나누어 떨어지지 않는다면, 선이 격자점을 지나지 않음
        // 나누어 떨어진다면, 선이 격자점을 (최대공약수 - 1)번 통과하여 그만큼의 정사각형이 잘리지 않음
        return gcd == 0 ? answer : answer + gcd - 1;
    }
    
    // O(log n), n = min(a, b)
    // 유클리드 호제법을 이용한 최대 공약수
    public long getGcd(long a, long b) {
        if (b == 0) return a;
        return getGcd(b, a % b);
    }
    
    // 시간 복잡도
    // O(log n), n = min(w, h)
}