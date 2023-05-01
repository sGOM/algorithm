import java.util.*;

class Solution {
    // 모음들 배열
    char vowels[] = {'A', 'E', 'I', 'O', 'U'};
    // 현재 순번의 문자열을 나타낼 변수
    StringBuilder sb = new StringBuilder();
    // 목표 문자열
    String target = null;
    // 목표를 찾인 경우 탐색을 멈추기 위한 변수
    boolean answerFinded = false;
    // 몇 번째 인지 저장하는 변수
    int answer = -1;
    
    public int solution(String word) {
        target = word;
        
        dfs(0);
        
        return answer;
    }
    
    // dfs, cnt는 빈 공간을 포함하여 현재 문자열의 길이를 저장
    void dfs(int cnt) {
        // 답을 찾았으면 탐색 종료
        if (answerFinded) return;
        // 문자열의 길이(빈공간 포함)가 5면 다음 순서를 탐색
        if (cnt == 5) { 
            answer++;
            // 현재 탐색 대상이 목표 문자열과 동일하다면 탐색 종료
            if (sb.toString().equals(target)) {
                answerFinded = true;
            }
            return;
        }
        
        // 빈 공간을 우선적으로 채우고 답이 아니면 모음을 하나 씩 넣으며 순회
        for (int i = -1; i < vowels.length; i++) {
            if (i == -1) { 
                dfs(5);
            } else {
                // i 번째 모음을 맨 뒤에 추가
                sb.append(vowels[i]);
                dfs(++cnt);
                // 아닌 경우 롤백
                --cnt;
                sb.setLength(sb.length() - 1);
            }
        }
    }
}