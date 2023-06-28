import java.util.*;

class Solution {
    final int[][] combinations = {
        {1, 1},         // *m - *m
        {2, 3}, {1, 2}, // 2m - 3m, 2m - 4m
        {3, 2}, {3, 4}, // 3m - 2m, 3m - 4m
        {4, 3}, {2, 1}  // 4m - 3m, 4m - 2m
    };
    final int DIVIDER_INDEX = 0;
    final int MULTIPLIER_INDEX = 1;
    
    public long solution(int[] weights) {
        HashMap<Integer, Long> matchingWeights = new HashMap<>();
        long answer = 0;
        
        for (int i = 0; i < weights.length; i++) {
            for (int[] comb : combinations) {
                if (weights[i] % comb[DIVIDER_INDEX] == 0) {
                    answer += matchingWeights.getOrDefault((weights[i] / comb[DIVIDER_INDEX]) * comb[MULTIPLIER_INDEX], 0L);
                }
            }
            matchingWeights.compute(weights[i], (k, v) -> v == null ? 1L : v + 1L);
        }
        
        return answer;
    }
    
    // 시간 복잡도
    // O(n), n = weights.length
    // == O(comb * n), comb = (시소에 앉을 수 있는 조합의 갯수) = 7 
}
