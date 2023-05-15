import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        // Generic으로 Primitive 자료형이 들어올 수 없음, 된다고 해도 컴파일러가 자동으로 변환해주는 것
        Integer[] nums = Arrays.stream(numbers)
                                .boxed()
                                .toArray(Integer[]::new);
        
        // 두 숫자, a와 b를 이용해 만든 숫자 ab, ba 크기를 비교해 정렬
        Arrays.sort(nums, (a, b) -> {
            int ab = Integer.parseInt(String.valueOf(a) + String.valueOf(b));
            int ba = Integer.parseInt(String.valueOf(b) + String.valueOf(a));
                
            return ba - ab;
        });
        
        // 최댓값이 0인 경우 다른 값도 0이므로 "0"을 반환
        if (nums[0] == 0) return "0";
        
        // 정렬된 배열을 순회하며 모든 수를 합침
        StringBuilder answer = new StringBuilder();
        for (int num : nums) answer.append(num);        
        
        return answer.toString();
    }
}