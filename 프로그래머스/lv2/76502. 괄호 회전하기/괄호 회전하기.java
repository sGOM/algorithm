import java.util.Stack;

class Solution {
    public int solution(String s) {
        // 괄호 문자열 길이가 홀수면 0 반환
        if (s.length() % 2 == 1) return 0;
        
        // 가능한 경우의 수 (답)
        int                 answer          = 0;
        // brackets가 유효한지 확인할 stack
        Stack<Character>    bracketStack    = new Stack<>();
        
        // 괄호 문자열을 왼쪽으로 i칸만큼 회전시키면서 검사
        for (int i = 0; i < s.length(); i++) {
            // 올바른 괄호 문자열인지 체크할 boolean
            boolean isRight  = true;
            // 왼쪽으로 i칸 회전시킨 괄호 문자열
            String  rotation = s.substring(i) + s.substring(0, i);
            
            // 괄호 문자열 유효성 검사
            for (char b : rotation.toCharArray()) {
                // 여는 괄호의 경우 stack에 추가하고 다음 문자로
                if (b == '(' || b == '[' || b == '{') {
                    bracketStack.push(b);
                    continue;
                }
                // 닫는 괄호의 경우
                // 1. stack이 비어있거나
                // 2. stack에 맨위에 있는 괄호가 같은 종류의 여는 괄호가 아닌 경우
                // 이번 괄호 문자열은 폐기
                if (bracketStack.isEmpty() || !(bracketStack.peek() == (b - 2) || bracketStack.peek() == (b - 1))) {
                    isRight = false;
                    break;
                }
                // 닫는 괄호가 위 검사를 통과한 경우 stack에서 하나 제거
                bracketStack.pop();
            }
            // 올바른 괄호 문자열인 경우
            if (isRight) answer++;
            // 다음 검사를 위해 stack clear
            bracketStack.clear();
        }
        return answer;
    }
}