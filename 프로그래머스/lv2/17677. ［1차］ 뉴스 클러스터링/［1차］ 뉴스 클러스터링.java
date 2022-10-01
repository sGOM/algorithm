import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        // 원소들의 개수를 카운팅할 Map
        HashMap<String, Integer>    str1Map = new HashMap<>();
        HashMap<String, Integer>    str2Map = new HashMap<>();
        // 원소들의 종류를 저장할 Set
        HashSet<String>             strSet  = new HashSet<>();
        // 모두 소문자로 변환
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        
        // str1의 원소 카운팅, 공통 Set을 저장
        for (int i = 0; i <= str1.length() - 2; i++) {
            String el = str1.substring(i, i + 2);
            // a-z 이외의 것을 포함한다면 아무것도 하지 않음
            if (el.matches(".*[^a-z].*")) continue;
            // 이미 카운팅 해본 원소라면 + 1
            if (str1Map.containsKey(el))    str1Map.put(el, str1Map.get(el) + 1);
            // 그렇지 않다면 1로 초기화하고 추가
            else                            { str1Map.put(el, 1); strSet.add(el); }
        }
        // str2의 원소 카운팅, 공통 Set을 저장
        for (int i = 0; i <= str2.length() - 2; i++) {
            String el = str2.substring(i, i + 2);
            // a-z 이외의 것을 포함한다면 아무것도 하지 않음
            if (el.matches(".*[^a-z].*")) continue;
            // 이미 카운팅 해본 원소라면 + 1
            if (str2Map.containsKey(el))    str2Map.put(el, str2Map.get(el) + 1);
            // 그렇지 않다면 1로 초기화하고 추가
            else                            { str2Map.put(el, 1); strSet.add(el); }
        }
        
        // 교집합, 합집합 원소개수 카운팅 변수 선언
        int union           = 0;
        int intersection    = 0;
        // 원소 종류 set을 순회
        for (String el : strSet) {
            // null 비교하기 위해 Integer 이용
            // str1, str2에서 해당 원소를 몇개 가진지 얻어옴
            Integer num1 = str1Map.get(el);
            Integer num2 = str2Map.get(el);
            // 겹치는게 없으면 합집합에만 추가
            if      (num1 == null)
                union += num2;
            else if (num2 == null)
                union += num1;
            // 겹치는게 있으면 둘 중 최소값은 교집합에 최대값은 합집합에 추가
            else {
                intersection    += Math.min(num1, num2);
                union           += Math.max(num1, num2);
            }
        }
        
        // 문제에서 주워진 상수
        final double MULTIPLE_VAL = 65536.0;
        // 합집합이 공집하이면 유사도를 1로 처리하고 아니면 계산해서 리턴
        return (int)((union == 0) ? MULTIPLE_VAL : (intersection / (double)union) * MULTIPLE_VAL);
    }
}