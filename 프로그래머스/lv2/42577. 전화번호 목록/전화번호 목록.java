import java.util.Arrays;
import java.util.HashSet;

class Solution {
    public boolean solution(String[] phone_book) {
        // 전화번호 최대 길이
        final int   MAX_CALLNUM_LEN = 20;
        // 해당 길이의 전화번호가 나왔는지 체크
        boolean     len[]           = new boolean[MAX_CALLNUM_LEN + 1];
        
        // 전화번호를 저장할 Set
        HashSet<String> phoneSet = new HashSet<>();
        
        // 길이(오름차순)순으로 정렬
        Arrays.sort(phone_book, (a, b) -> a.length() - b.length());
        
        // 모든 전화번호를 순회
        for (String phoneNum : phone_book) {
            // 현재 가르키는 전화번호보다 짧은 전화번호에 대해 검사
            for (int i = 1; i <= phoneNum.length(); i++) {
                // 길이 i의 전화번호가 존재하면
                // 현재 가르키는 전화번호의 접두어가 Set에 존재하는지 검사
                if (len[i] && phoneSet.contains(phoneNum.substring(0, i)))
                    // 있다면 false 반환
                    return false;
            }
            // 다른 번호가 이 번호의 접두어가 아니면 Set에 새로 추가
            phoneSet.add(phoneNum);
            // 해당 길이의 번호가 있는걸로 변경
            len[phoneNum.length()] = true;
        }
        
        // 모든 검사를 통과하면 true 반환
        return true;
    }
}