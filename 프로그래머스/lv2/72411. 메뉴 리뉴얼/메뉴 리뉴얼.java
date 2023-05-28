import java.util.*;
import java.util.stream.*;

class Solution {
    // 각 코스 길이별 최대 등장 횟수
    HashMap<Integer, Integer> courseMax = new HashMap<>();
    // 각 코스 별 등장 횟수
    HashMap<String, Integer> courseCandidates = new HashMap<>();
    // course 배열 중 최대 값, 찾아야하는 조합의 최대 길이
    int courseMaxLen = 0;
    
    public String[] solution(String[] orders, int[] course) {
        
        // courseMax와 courseMaxLen 초기화
        for (int c : course) {
            courseMax.put(c, 0);
            if (courseMaxLen < c) courseMaxLen = c;
        }
        
        // orders를 순회
        for (String o : orders) {
            // o를 알파벳 순으로 정렬
            o = Stream.of(o.split(""))
                      .sorted()
                      .collect(Collectors.joining());
            // o가 생성가능한 조합을 카운팅하여 courseCandidates에 적용
            makeCombination(o, "", 0);
        }
        
        // courseCandidates와 courseMax를 이용해 각 코스 길이에 해당하는 후보를 반환
        String[] answer = courseCandidates.entrySet().stream()
                            // 해당 코스길이의 최대 출현 횟수인 코스 && 그 횟수가 2 이상
                            .filter(ent -> courseMax.get(ent.getKey().length()) == ent.getValue() && 2 <= ent.getValue())
                            .map(Map.Entry::getKey)
                            // 알파벳 순으로 정렬
                            .sorted()
                            .toArray(String[]::new);
        
        return answer;
    }
    
    // orders에 담겨 있는 문자열이 각각 알파벳 순으로 정렬되었다고 가정하고 작성함
    public void makeCombination(String order, String comb, int idx) {
        // 만들어진 조합이 원하는 코스 길이에 해당하는 경우
        if (courseMax.containsKey(comb.length())) {
            // courseCnadidates의 정보를 갱신
            int count = courseCandidates.compute(comb, (k, v) -> v == null ? 1 : v + 1);
            // 갱신한 정보가 해당 코스 길이의 최대 등장 횟수인지 확인하고 갱신
            courseMax.compute(comb.length(), (k, v) -> v < count ? count : v);
        }
        // 찾아야하는 조합의 최대 길이라면 더 이상 반복하지 않음
        if (comb.length() == courseMaxLen) return;
        
        // 새로운 조합 생성
        for (int i = idx; i < order.length(); i++) {
            makeCombination(order, comb + order.charAt(i), i + 1);
        }
    }
}