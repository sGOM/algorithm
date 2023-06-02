import java.util.*;

// 2번째 풀이
class Solution {
    public int solution(int[] stones, int k) {
        // Window Sliding을 위한 Deque
        Deque<Integer> windowSlide = new LinkedList<>();
        
        // 정답 초기화
        int answer = Integer.MAX_VALUE;
        // stones 순회
        for (int i = 0; i < stones.length; i++) {
            // (i번째 디딤돌)보다 작은 (windowSlide 마지막 디딤돌)을 모두 삭제
            while (!windowSlide.isEmpty() && stones[windowSlide.peekLast()] < stones[i]) {
                windowSlide.pollLast();
            }
            // (i번째 디딤돌) 추가
            windowSlide.addLast(i);
            
            // (windowSlide 첫부분)이 원하는 범위 밖인 경우, (windowSlide 첫부분) 제거
            if (k <= i - windowSlide.peekFirst()) {
                windowSlide.pollFirst();
            }
            
            // (windowSlide의 첫부분(최댓값))이 answer보다 작은 경우
            if ((k - 1 <= i) && stones[windowSlide.peekFirst()] < answer) {
                answer = stones[windowSlide.peekFirst()];
            }
        }
    
        return answer;
    }
    
    // 시간 복잡도
    // O(n), n = stones.length
}

/* 1번째 풀이
class Solution {
    public int solution(int[] stones, int k) {
        // TreeMap을 이용해 k개의 연속된 디딤돌의 최댓값을 얻을 수 있음
        // 숫자가 stones[i]인 디딤돌 갯수 카운팅
        TreeMap<Integer, Integer> stoneTree = new TreeMap<>();
        
        // 초기 k - 1개 추가
        for (int i = 0; i < k - 1; i++) {
            stoneTree.compute(stones[i], (key, value) -> value == null ? 1 : value + 1);
        }
        
        int answer = Integer.MAX_VALUE;
        // 초기 값으로 설정하지 않은 나머지 디딤돌을 순회
        for (int i = k - 1; i < stones.length; i++) {
            // i번째 디딤돌 추가
            stoneTree.compute(stones[i], (key, value) -> value == null ? 1 : value + 1);
            
            // stoneTree에 들어있는 연속된 k개의 디딤돌 중, 최대 값
            int maxOfStoneTree = stoneTree.lastKey();
            // 최댓 값이 answer 보다 작다면
            if (maxOfStoneTree < answer) {
                // answer 갱신
                answer = maxOfStoneTree;
            }
            
            // (i - (k - 1))번째 디딤돌 제거
            if (stoneTree.compute(stones[i - (k - 1)], (key, value) -> value - 1) == 0) {
                stoneTree.remove(stones[i - (k - 1)]);
            }
        }
    
        return answer;
    }
    
    // 시간 복잡도
    // O(n log k), n = stones.length, k = (한 번에 건너뛸 수 있는 디딤돌의 최대 칸수)
    // -> TreeMap.remove, TreeMap.compute 모두 시간 복잡도 O(log k)
    // -> O(log k)를 stones.length 만큼 반복
}
*/
