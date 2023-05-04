import java.util.*;

class Solution {
    // numbers 배열을 역방향으로 순회
    public int[] solution(int[] numbers) {
        // 정답 배열, -1로 초기화
        int[] answer = new int[numbers.length];
        Arrays.fill(answer, -1);
        int len = numbers.length;
        
        // 뒷 수들을 저장할 Stack
        Stack<Integer> stack = new Stack<>();
        
        // numbers 배열의 맨 뒤에서 한 칸을 이동한 뒤부터 역방향으로 순회
        for (int i = len - 1; 0 <= i; i--) {
            // 현재 탐색 중인 숫자
            int curNum = numbers[i];
            
            // Stack에 curNum의 뒷 큰수가 있는지 탐색
            while (!stack.empty()) {
                // 뒷 큰수를 찾으면 answer를 갱신하고, 반복문 탈출
                if (curNum < stack.peek()) {
                    answer[i] = stack.peek();
                    break;
                // Stack에 curNum보다 작거나 같은 뒷 수는 삭제 (이제 뒷 큰수가 될 수 없기 때문) 
                } else {
                    stack.pop();
                }
            }
            // curNum을 뒷 수 Stack에 추가
            stack.push(curNum);
        }
        
        return answer;
    }
}