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

/* 남이 작성한 더 나아 보이는 풀이 
class Solution {
    public int solution(String word) {
        int answer = 0, per = 3905; // per 은 모든 경우의 수 가짓 수
        // 타겟 문자열을 기반으로 해당 문자열이 몇 번째인지 계산 (수학적 접근)
        for(String s : word.split("")) answer += "AEIOU".indexOf(s) * (per /= 5) + 1;
        return answer;
    }
}

// 해당 풀이는 최대 5번의 반복문만 실행됨
// 문제가 변경되면 per을 매번 변경해야하나, 문자의 갯수만 달라진다면 per은 충분히 계산할 수 있음
// dfs개 좋은 성능 내는 이유는 모든 경우의 수가 3905번 뿐이기 때문
*/