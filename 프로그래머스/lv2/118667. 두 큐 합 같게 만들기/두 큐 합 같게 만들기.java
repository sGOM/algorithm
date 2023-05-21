import java.util.*;

class Solution {
    public int solution(int[] que1, int[] que2) {
        // 각 Queue의 합계를 저장할 변수
        long queueSum1 = 0L;
        long queueSum2 = 0L;
        // 배열을 진짜 Queue로 바꾸고 값들 초기화
        // 시간 복잡도 O(que1.length + que2.length)
        Queue<Long> queue1 = new LinkedList<>();
        Queue<Long> queue2 = new LinkedList<>();
        for (long q1 : que1) { queue1.add(q1); queueSum1 += q1; }
        for (long q2 : que2) { queue2.add(q2); queueSum2 += q2; }
        
        // 합계가 홀수면 -1 반환
        if ((queueSum1 + queueSum2) % 2L == 1L) return -1;
        
        // 움직이는 작업의 최댓값 설정, 무조건 해당 값을 넘을 수 없음
        // 이유 :
        // 문제를 2개의 Queue 이어붙여서 하나의 원을 만들고, 해당 원을 [칸막이 2개]로 잘라서 잘려 나간 선들의 합이 같게 만드는 것이라 생각하면
        // [칸막이1]과 [칸막이2]이 각각 (que1.length + que2.length)만큼 이동하면, 아무리 못해도 초기 값으로 돌아오기 때문
        int maxMoves = (que1.length + que2.length) * 2;
        // 움직이는 작업 수를 저장할 변수
        int answer = 0;
        // 2개의 Queue의 수가 다른 동안
        while (queueSum1 != queueSum2) {
            // 각각 큰 쪽에서 원소를 하나 빼서 작은 쪽에 추가함
            // queue1의 원소들의 합이 더 큰 경우
            if (queueSum2 < queueSum1) {
                long num = queue1.poll();
                queue2.add(num);
                queueSum1 -= num;
                queueSum2 += num;
            // queue2의 원소들의 합이 더 큰 경우
            } else {
                long num = queue2.poll();
                queue1.add(num);
                queueSum2 -= num;
                queueSum1 += num;
            }
            // 작업 횟수 + 1
            answer++;
            // 작업 횟수가 최댓값보다 큰 경우 -1 반환
            if (maxMoves < answer) return -1;
        }
        
        return answer;
    }
    // 시간 복잡도
    // O(que1.length + que2.length)
}