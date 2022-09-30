import java.util.Queue;
import java.util.LinkedList;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        // 정답을 저장할 Queue 생성
        Queue<Integer> answer = new LinkedList<>();
        
        // 서비스 반영 가능 진도를 상수로 선언
        final int PROGESS_100 = 100;
        // 현재 개발일수, 0번째 기능이 개발되었을 때로 초기화
        int curDay = (int)Math.ceil((PROGESS_100 - progresses[0]) / (double)speeds[0]);
        // 한 번에 배포된 기능 수
        int cnt = 1;
        
        // progesses 배열을 순회
        for (int idx = 1; idx < progresses.length; idx++) {
            // idx번째 기능이 필요로하는 개발일수
            int takeDays = (int)Math.ceil((PROGESS_100 - progresses[idx]) / (double)speeds[idx]);
            // (현재 개발일수 < idx번째 기능 개발일수)
            // -> 정답에 카운팅한 기능 수 추가하고 1로 변경
            // -> 현재 개발일수 = idx번째 기능 개발일수
            if (curDay < takeDays)  { answer.add(cnt); cnt = 1; curDay = takeDays; }
            // 아니면 한 번에 배포될 기능 수 ++
            else                    cnt++;
        }
        // 마지막 배포될 기능 수 추가
        answer.add(cnt);
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}