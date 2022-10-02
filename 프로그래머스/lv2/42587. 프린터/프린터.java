import java.util.Queue;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Arrays;
import java.util.stream.Collectors;

class Solution {
    public int solution(int[] priorities, int location) {
        // 작업 목록 Queue
        Queue<Integer>          taskList    
            = new LinkedList<>(Arrays.stream(priorities).boxed().collect(Collectors.toList()));
        // 다음 출력할 문서의 우선 순위를 저장한 PriorityQueue
        // Comparator를 넘겨주지 않고 pq에 addAll하는 것은 정렬을 보장하지 않음
        // PriorityQueue는 Sorted List가 아닌 Priority Heap으로 구현되어 있음 
        // https://stackoverflow.com/questions/5695017/priorityqueue-not-sorting-on-add
        PriorityQueue<Integer>  pq
                = new PriorityQueue<>(priorities.length, (a, b) -> b - a);  // 내림차순 정렬
        pq.addAll(Arrays.stream(priorities).boxed().collect(Collectors.toList()));
        
        int answer = 0;
        
        while (true) {
            // 출력
            if (pq.peek() == taskList.peek()) {
                pq.poll(); taskList.poll();
                answer++;
                // 출력한게 목표 문서라면 종료
                if (location == 0) break;
            } 
            // 출력 실패
            else {
                // 맨 앞에 문서를 맨 뒤로
                taskList.add(taskList.poll());
                // 목표 문서가 맨 앞에 있었다면 맨뒤로
                if (location == 0) location = taskList.size();
            }
            // 목표 문서 위치 추적
            location--;
        }
        
        return answer;
    }
}