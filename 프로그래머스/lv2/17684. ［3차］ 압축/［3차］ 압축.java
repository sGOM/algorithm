import java.util.HashMap;
import java.util.ArrayList;

class Solution {
    public int[] solution(String msg) {
        HashMap<String, Integer>    dict    = new HashMap<>();      // 사전
        ArrayList<Integer>          answer  = new ArrayList();      // 정답을 저장할 ArrayList
        StringBuilder               sb      = new StringBuilder();  // w와 c를 저장할 StringBuilder

        // 사전 index
        int dictIdx = 1;
        // ("A", 1) ~ ("Z", 26)를 사전에 등록
        for (; dictIdx <= (int)('Z' - 'A') + 1; dictIdx++)
            dict.put(Character.toString((char)('A' + dictIdx - 1)), dictIdx);


        // 압축시킬 문자열의 마지막까지 순회
        for (int idx = 0; idx < msg.length(); idx++) {
            // 기본 w
            sb.append(msg.charAt(idx));
            // w + c가 이미 존재하고 c가 마지막을 가르키는게 아니면
            // w + c -> w
            while (dict.containsKey(sb.toString()) && (idx != msg.length() - 1)) sb.append(msg.charAt(++idx));
            // w가 마지막까지 포함 && 사전에 존재함 -> 통째로 출력
            if ((idx == msg.length() - 1) && dict.containsKey(sb.toString()))
                answer.add(dict.get(sb.toString()));
            // 사전에 존재하지 않음
            else if (!dict.containsKey(sb.toString())) {
                // 사전에 등록
                dict.put(sb.toString(), dictIdx++);
                // StringBuilder에서 c를 제거하고 정답 배열(answer)에 추가
                --idx;
                answer.add(dict.get(sb.substring(0, sb.length()-1)));
            }

            // StringBuilder 초기화
            sb.setLength(0);
        }


        return answer.stream()
                        .mapToInt(Integer::intValue)
                        .toArray();
    }
}