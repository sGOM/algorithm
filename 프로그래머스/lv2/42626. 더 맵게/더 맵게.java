import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

class Solution {
    public int solution(int[] scoville, int K) {
        // 우선 순위 큐 선언, 오름차순
        PriorityQueue<Integer> pq = new PriorityQueue<>(scoville.length, (a, b) -> a - b);
        // int 배열을 Integer List로 변환하고 우선 순위 큐에 추가
        pq.addAll(Arrays.stream(scoville).boxed().collect(Collectors.toList()));
        
        int answer = 0;
        // 스코빌 지수가 가장 낮은 음식의 스코빌 지수가 K보다 작고 && 음식의 개수가 2개 이상일 동안
        while (pq.peek() < K && 1 < pq.size()) {
            // 가장 덜 매운 2개의 음식을 섞어 새로운 음식 생성
            // 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)
            pq.add(pq.poll() + pq.poll()*2);
            answer++;
        }
        
        // 모든 음식을 K 이상으로 만들 수 없다면 -1
        // 아니라면 음식을 섞은 횟수 반환
        return (pq.peek() < K) ? -1 : answer;
    }
}