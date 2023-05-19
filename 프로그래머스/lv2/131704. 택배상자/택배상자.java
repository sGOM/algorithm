import java.util.*;

class Solution {
    public int solution(int[] order) {
        Stack<Integer> subContainer = new Stack<>();
        Queue<Integer> mainContainer = new LinkedList<>();
        for (int i = 1; i <= order.length; i++) mainContainer.offer(i);

        int answer = 0;
        while (true) {
            // 보조 컨테이너가 비어있지않음 && 맨 앞부분이 다음 실을 상자라면
            if (!subContainer.empty() && subContainer.peek() == order[answer]) {
                subContainer.pop();
                answer++;
            // 보조 컨테이너에서 꺼내지 못함 && 메인 컨테이너가 비어있지 않음
            } else if (!mainContainer.isEmpty()) {
                int nextNum = mainContainer.poll();
                // 메인 컨테이너
                // 트럭에 실을 수 없으면, 보조 컨테이너로 옮김
                if (nextNum != order[answer])   subContainer.push(nextNum);
                // 트럭에 실을 수 있므녀, 실음
                else                            answer++;
            // 메인 컨테이너가 비었고, 보조 컨테이너의 다음 상자가 순서에 맞지 않을 때
            } else {
                break;
            }
        }
        
        // 트럭에 실은 상자 갯수 반환
        return answer;
    }
    // 시간 복잡도
    // O(order.length)
    // 비교 연산을 order.length의 3 ~ 4배인데 상수배니까...
}