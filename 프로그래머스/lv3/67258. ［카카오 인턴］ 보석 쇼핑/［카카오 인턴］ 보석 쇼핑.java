import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer    = { 0, Integer.MAX_VALUE };
        // 현재 확인하고 있는 범위
        int[] current   = { 0, -1 };
        
        // 현재 확인하고 있는 범위의 보석 갯수를 카운트할 Map
        HashMap<String, Integer> gemCount = new HashMap<>();
        // 모든 종류의 보석을 0으로 초기화
        // 시간 복잡도 O(gems.length)
        for (String gem : gems) gemCount.put(gem, 0);
        
        // 현재 확인하고 있는 범위에 존재하는 보석 종류
        int kindOfGem = 0;
        
        // 끝 진열대 번호가 gems 배열의 끝이 아닌 동안
        // 시간 복잡도 O(2 * gems.length)
        while (current[1] != gems.length - 1) {
            // 끝 진열대 번호를 1 늘렸을 때, 이전 범위에서 없던 보석이라면 kindOfGem++
            if (1 == gemCount.compute(gems[++current[1]], (k, v) -> v + 1)) kindOfGem++;
            
            // 확인 범위의 보석 종류가 진열대의 모든 보석의 종류를 포함하는 경우 && 확인 범위의 끝 진열대 번호가 시작 진열대 번호 보다 크거나 같은 경우
            while (kindOfGem == gemCount.size() && current[0] <= current[1]) {
                // 가장 짧은 구간이라면 정답 갱신
                if (current[1] - current[0] < answer[1] - answer[0]) {
                    answer[0] = current[0];
                    answer[1] = current[1];
                }
                
                // 시작 진열대 번호를 1 늘렸을 때, 보석의 종류가 줄어들었다면, kindOfGem--
                if (0 == gemCount.compute(gems[current[0]++], (k, v) -> v - 1)) kindOfGem--;
            }
        }
        
        // 진열대 번호가 1부터 시작하므로, 시작과 끝에 1씩 추가
        answer[0]++;
        answer[1]++;
        
        return answer;
    }
    // 시간 복잡도
    // O(gems.length)
    // 최대 gems.length * 3 만큼 반복함
}