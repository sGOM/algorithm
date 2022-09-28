class Solution {
    public int[] solution(int n, int s) {
        // s를 n으로 나눈 몫
        int quotient    = s / n;
        // s를 n으로 나눈 나머지
        int remainder   = s % n;
        // 몫이 0이라면 s > n 이므로 n개의 자연수로 합이 s인 집합을 만들 수 없음
        if (quotient == 0) return new int[] {-1};
        
        // 크기가 n인 배열(집합) 생성
        int[] answer = new int[n];
        // 배열을 순회하며 오름차순으로 값 할당
        for (int i = 0; i < answer.length; i++) {
            // 각 원소의 합이 s고 각 원소의 곱이 최대가 되는 자연수 집합은
            // 각 원소들이 원소 값들이 평균에 가까운 집합
            // 앞 부분에는 몫만 나눠줌
            if (i < answer.length - remainder)  answer[i] = quotient;
            // 뒷 부분에는 나머지를 (몫 + 1)씩 나눠줌
            else                                answer[i] = quotient + 1;
        }
        
        return answer;
    }
}