import java.util.*;

class Solution {
    public String solution(String p) {
        return fixParentheses(p);
    }
    
    // 괄호를 고치는 함수
    public String fixParentheses(String p) {
        // 1. 빈 문자열일 경우 빈 문자열 반환
        if (p.length() == 0) return "";
        
        // 2. 더 이상 분리할 수 없는 "균형잡힌 괄호 문자열"을 찾기 위한 변수
        int count = 0, index = 0;
        // 올바른 괄호 문자열인지 판단하는 변수
        boolean isRight = true;
        String u = "";
        
        // 2. 더 이상 분리할 수 없는 "균형잡힌 괄호 문자열", u 찾기
        while (count != 0 || index == 0) {
            char parentheses = p.charAt(index++);
            u += parentheses;
            count += (parentheses == '(' ? 1 : -1);
            if (count < 0) isRight = false;
        }
        
        // v 구하기
        String v = p.substring(index);
        // 3, 4-2. v를 단계부터 재귀적으로 실행
        v = fixParentheses(v);
        
        return !isRight 
            // 4. 문자열 u가 "올바른 괄호 문자열"이 아니라면
            ? "(" + v + ")" + reverseParentheses(u.substring(1, u.length() - 1))
            // 3. 문자열 u가 "올바른 괄호 문자열"이 맞다면
            : u + v;
    }
    
    // O(n), n = p.length()
    // 모든 괄호를 뒤집는 함수
    public String reverseParentheses(String p) {
        String parentheses = "";
        for (char c : p.toCharArray()) parentheses += (c == '(' ? ")" : "(");
        
        return parentheses;
    }
    // 전체 시간 복잡도
    // O(n), n = p.length()
}