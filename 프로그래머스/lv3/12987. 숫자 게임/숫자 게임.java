import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        // A의 승점도 계산하지 않는 이상, 순서는 중요하지 않음
        // A와 B 배열 모두 오름차순으로 정렬
        Arrays.sort(A);
        Arrays.sort(B);
        
        int answer = 0;
        int idx = -1;
        // A를 오름차순으로 순회하면서
        for (int i = 0; i < A.length; i++) {
            // A[i]보다 큰 B 중, 가장 작은 값을 찾음
            while (++idx < B.length && B[idx] <= A[i]) { }
            
            // 만약에 더 이상 A보다 큰 B가 없으면 종료
            if (B.length <= idx) break;
            // 찾았다면 정답 +1
            answer++;
        }
        
        return answer;
    }
}