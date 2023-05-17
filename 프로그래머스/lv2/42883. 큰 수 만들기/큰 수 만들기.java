class Solution {
    public String solution(String number, int k) {
        StringBuilder answer = new StringBuilder(number);
        
        // 로직 : 뒷 수 보다 작은 앞 수 제거
        int index = 0;
        while (0 < k) {
            // 제거하다가, index가 -로 가면 다시 0으로 초기화
            if (index < 0) index = 0;
            // 모든 수가 내림차 순인데, 아직 덜 제거되었다면 뒤에서 부터 제거
            if (answer.length() - 1 == index) { 
                answer.delete(answer.length() - k, answer.length());
                break;
            }
            // 뒷 수 보다 작은 앞 수 제거
            if (answer.charAt(index) < answer.charAt(index + 1)) {
                answer.deleteCharAt(index);
                // answer[index - 1]과 비교하던 answer[index]가 삭제되었으니, answer[index - 1]을 다시 체크
                index--;
                k--;
            // 위 제거 조건에 부합하지 않으면, 다음을 체크
            } else {
                index++;
            }
        }
        
        return answer.toString();
    }
    // 시간 복잡도
    // O(number.length() + k * number.length())
    // 최악의 상황에는 모든 숫자를 체크하며 k번 삭제함
    // 이때 삭제하는 answer.deleteCharAt(int index)의 시간 복잡도는 이후의 문자열을 모두 옮겨야하므로 O(number.length)
}

/* 다른 사람이 작성한 더 좋은 코드
// 로직 자체는 동일하나, 시간복잡도 O(number.length())이고 가독성도 훨씬 좋음
import java.util.Stack;
class Solution {
    public String solution(String number, int k) {
        char[] result = new char[number.length() - k];
        Stack<Character> stack = new Stack<>();

        for (int i=0; i<number.length(); i++) {
            char c = number.charAt(i);
            while (!stack.isEmpty() && stack.peek() < c && k-- > 0) {
                stack.pop();
            }
            stack.push(c);
        }
        for (int i=0; i<result.length; i++) {
            result[i] = stack.get(i);
        }
        return new String(result);
    }
}
*/
