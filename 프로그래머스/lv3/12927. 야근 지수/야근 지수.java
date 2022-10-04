import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

class Solution {
    public long solution(int n, int[] works) {
        // 야근 없이 남은 작업을 해치울 수 있는 경우
        if (Arrays.stream(works).sum() - n <= 0) return 0;
        
        // 내림차순 Priority Queue 선언
        PriorityQueue<Integer> pq = new PriorityQueue<>(works.length, (a, b)-> b - a);
        // Priority Queue에 works 배열 추가
        pq.addAll(Arrays.stream(works).boxed().collect(Collectors.toList()));
        
        // 퇴근 전까지
        while(n-- != 0)
            // 최대한 작업량 높은 작업 1시간 씩
            pq.add(pq.poll() - 1);
        
        // int -> long으로 형변환하고 제곱해서 합 리턴
        return pq.stream()
                    .mapToLong(l -> l*l)
                    .sum();
    }
}