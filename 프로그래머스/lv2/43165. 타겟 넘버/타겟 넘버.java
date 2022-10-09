class Solution {
    public int solution(int[] numbers, int target) {
        // 완전 탐색
        return DFS(numbers, target, 0, 0);
    }
    
    public int DFS(int[] numbers, int target, int sum, int idx) {
        // 끝까지 탐색한 경우 target == sum 이면 1을 아니면 0을 반환
        if (idx == numbers.length) return (sum == target) ? 1 : 0;
        
        int cnt = 0;
        // +를 한경우
        cnt += DFS(numbers, target, sum + numbers[idx], idx + 1);
        // -를 한경우
        cnt += DFS(numbers, target, sum - numbers[idx], idx + 1);
        return cnt;
    }
}