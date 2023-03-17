import java.util.*;

// 매번 범위내에 수들을 합하면 그 만큼 합연산의 횟수가 많아짐
// 그래서 맨앞을 빼고 뒤에 새로운 원소만 추가하는 방식을 채택함
class Solution {
    public int solution(int[] elements) {
        // 나올 수 있는 수열의 합의 종류를 저장하는 set
        HashSet<Integer> seriesSumSet = new HashSet<>();
        int sum = 0;
        for (int len = 1; len <= elements.length; len++) {
            // 수열의 길이 새로운 원소 추가
            sum += elements[len - 1];
            for (int idx = 0; idx < elements.length; idx++) {
                // sum 갱신 후 set에 추가
                seriesSumSet.add(sum);
                // 머리(Head)의 원소를 제거하고 꼬리(Tail)의 새 원소 추가
                sum += elements[(idx + len) % elements.length] - elements[idx];
            }
        }
        
        return seriesSumSet.size();
    }
}