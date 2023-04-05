import java.util.*;
import java.util.regex.*;

class Solution {
    public String[] solution(String[] files) {
        // 이진 검색 트리를 기반으로 하는 TreeSet 사용
        // 비교 메서드 직접 정의
        TreeSet<String> set = new TreeSet<>((s1, s2) -> {
            // regexp 패턴 정의
            Pattern headPattern = Pattern.compile("\\D+");  // 연속된 숫자가 아닌 것들
            Pattern numPattern  = Pattern.compile("\\d+");  // 연속된 숫자
            
            Matcher matcher;
            
            // s1의 HEAD에 찾기
            matcher = headPattern.matcher(s1);
            matcher.find();
            String head1 = matcher.group();
            // s2의 HEAD에 찾기
            matcher = headPattern.matcher(s2);
            matcher.find();
            String head2 = matcher.group();
            
            // 대소문자를 무시하고 HEAD끼리 순서 비교
            int ret = head1.toLowerCase().compareTo(head2.toLowerCase());
            // HEAD가 동일하다면
            if (ret == 0) { 
                // s1의 NUMBER 찾기
                matcher = numPattern.matcher(s1);
                matcher.find();
                int num1 = Integer.parseInt(matcher.group());
                // s2의 NUMBER 찾기
                matcher = numPattern.matcher(s2);
                matcher.find();
                int num2 = Integer.parseInt(matcher.group());
                
                // NUMBER 비교
                ret = num1 - num2;
                // NUMBER가 동일하다면 기존 순서 유지
                if (ret == 0) ret = 1;
            }
            
            return ret;
        });
        
        // 비교 메서드를 직접 정의한 TreeSet에 files 모두 추가
        set.addAll(Arrays.asList(files));
        
        // TreeSet<String> -> String[] 으로 변경
        return set.toArray(new String[set.size()]);
    }
}

/* 남이 작성한 굉장히 이쁘고 깔끔한 코드
// regexp 정의
// group 1 -> ((a 부터 z 사이) + (공백) + (. 과 -))가 1개 이상 연속
// group 2 -> (0 부터 9 사이)가 1~5개 연속
Pattern p = Pattern.compile("([a-z\\s.-]+)([0-9]{1,5})");

Arrays.sort(files, new Comparator<String>() {
    @Override
    public int compare(String s1, String s2) {
        // 비교 대상 문자열 2개를 대소문자 무시하고 Matcher 생성
        Matcher m1 = p.matcher(s1.toLowerCase());
        Matcher m2 = p.matcher(s2.toLowerCase());
        m1.find();
        m2.find();

        // HEAD 비교(group 1) 했을 때 같지 않다면
        if(!m1.group(1).equals(m2.group(1))) {
            // HEAD 순서 반환
            return m1.group(1).compareTo(m2.group(1));
        } else {
            // NUMBER 순서 반환
            return Integer.parseInt(m1.group(2)) - Integer.parseInt(m2.group(2));
        }
    }
});

return files;
*/
