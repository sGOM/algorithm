import java.util.Arrays;

class Solution {
    // 클래스 맴버 변수 선언
    boolean[]   isVisited;  // 방문 여부를 저장하는 배열
    String[]    wordArray;  // 문제에서 주어진 문자 배열
    String      targetWord; // 문제에서 주어진 타겟 문자
    int         min = Integer.MAX_VALUE;        // 가장 짧은 변환 과정의 변환 횟수
    
    public int solution(String begin, String target, String[] words) {
        // 변수들 초기화
        isVisited = new boolean[words.length];
        wordArray = words;
        targetWord = target;
        
        // 결과
        int ret = dfs(begin, 0);
        // 변환할 수 없는 경우 0을 반환하고 아니면 결과 값 반환
        return ret == Integer.MAX_VALUE ? 0 : ret;
    }
    
    public int dfs(String begin, int cnt) {
        // 주어진 문자와 타겟 문자가 동일하면 count 값 반환
        if (begin.equals(targetWord)) return cnt;
        // 이미 현재의 최소 변환 횟수 보다 많으면 탐색을 바로 종료
        if (min <= cnt) return Integer.MAX_VALUE;
        
        // 문자 배열을 순회
        for (int to = 0; to < wordArray.length; to++) {
            // 방문 한적이 없고 && 변환이 가능하다면
            if (!isVisited[to] && isCanConvert(begin, wordArray[to])) {
                isVisited[to] = true;
                min = Math.min(dfs(wordArray[to], cnt + 1), min);
                isVisited[to] = false;
            }
        }
        return min;
    }
    
    // 2개의 문자열 간에 변환 가능 여부를 확인
    public boolean isCanConvert(String from, String to) {
        // 길이는 같아야함
        if (from.length() != to.length()) return false;
        
        int cntDiff = 0;
        for (int i = 0; i < from.length(); i++) {
            if (from.charAt(i) != to.charAt(i)) {
                // 차이가 1개 이상이면 바로 false를 반환
                if (1 < ++cntDiff) return false;
            }
        }
        
        // 차이가 1개인 경우만 true를 반환
        return (cntDiff == 1) ? true : false;
    }
}