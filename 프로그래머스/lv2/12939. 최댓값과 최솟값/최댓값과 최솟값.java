import java.util.Arrays;

class Solution {
    public String solution(String s) {
        int[] nums = Arrays.stream(s.split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int num : nums) {
            if (max < num) max = num;
            if (num < min) min = num;
        }
        StringBuilder answer = new StringBuilder();
        answer.append(min);
        answer.append(" ");
        answer.append(max);
        return answer.toString();
    }
}