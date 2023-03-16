import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        // 귤들의 갯수를 카운팅
        // 어떤 [크기]가 배열의 원소로 들어있을지 모르니 Hash를 사용하는 것이 효율적임
        HashMap<Integer, Integer> tangerineSizeBox = new HashMap<>(k);
        for (int t : tangerine) {
            tangerineSizeBox.put(t, tangerineSizeBox.getOrDefault(t, 0) + 1);
        }
        
        // 집계한 [크기]별 귤들의 갯수를 내림차순으로 정렬
        List<Integer> quantityList = new ArrayList<>(tangerineSizeBox.values());
        Collections.sort(quantityList, Collections.reverseOrder());
        
        int answer = 0;
        
        // 상자에 가장 많은 갯수가 존재하는 [크기]의 귤들부터 넣고 다 채웠으면 종류의 수 반환
        for (int quantity : quantityList) {
            k -= quantity;
            answer++;
            if (k <= 0) return answer;
        }
        
        return answer;
    }
}