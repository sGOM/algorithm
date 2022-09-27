import java.util.Arrays;

class Solution {
    public int solution(int[] citations) {
        int h = 0;
        // 배열 정렬, 내림차순으로하면 좋은데 Integer로 변환해야해서 까다로움
        Arrays.sort(citations);
        // 정렬한 배열의 [큰 수 -> 작은 수] 순으로 가르키면서
        // 가르킨 수가 h보다 작으면 반복을 멈춤
        // 아니면 h++
        for (int len = citations.length - 1; (0 <= (len - h)) && (h < citations[len - h]); h++) {}
        
        return h;
    }
}