import java.util.*;

class Solution {
    public int solution(int storey) {
        int answer = 0;
        // 각 자릿수를 int[]에 저장, 맨 앞에 0추가
        int[] nums = String.valueOf("0" + String.valueOf(storey))   // O(log n)
                            .chars()                                // O(n)
                            .map(Character::getNumericValue)        // O(n)
                            .toArray();                             // O(n)
        
        // O(n)
        // 맨 뒷 자릿수부터 순회
        for (int i = nums.length - 1; 0 < i; i--) {
            // (6 이상) || ((현재 자릿수 5) && (다음 자릿수 5 이상))
            if (6 <= nums[i] || (5 == nums[i] && 5 <= nums[i - 1])) {
                // 10까지 더함
                answer += 10 - nums[i];
                nums[i - 1]++;
            // (4 이하) || ((현재 자릿수 5) && (다음 자릿수 4 이하))
            } else {
                // 0까지 뺌
                answer += nums[i];
            }
        }
        // 끝까지 순회하고 맨 앞자리 추가
        answer += nums[0];
        
        return answer;
    }
    // 전체 시간복잡도
    // O(n), n = (storey 자릿수)
}
