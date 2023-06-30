import java.util.*;

class Solution {
    final int OPENED_BOX = -1;
    
    public int solution(int[] cards) {
        PriorityQueue<Integer> groupSizeQueue = new PriorityQueue<>((a, b) -> b - a);
        
        // 모든 상자를 순회
        for (int boxNum = 0; boxNum < cards.length; boxNum++) {
            int curBoxNum = boxNum;
            int groupSize = 0;
            
            // 확인할 상자가 열려있지 않는 동안
            while (cards[curBoxNum] != OPENED_BOX) {
                int nextBoxNum = cards[curBoxNum] - 1;
                cards[curBoxNum] = OPENED_BOX;
                curBoxNum = nextBoxNum;
                groupSize++;
            }
            
            if (0 < groupSize) groupSizeQueue.add(groupSize);
        }
        
        if (groupSizeQueue.size() < 2) return 0;
        
        // 크기가 가장 큰 2개의 그룹 사이즈를 곱함
        return groupSizeQueue.poll() * groupSizeQueue.poll();
    }
    
    // 시간 복잡도
    // O(n), n = cards.length
    // 이중 반복문이기는 하지만, 이미 열려있는 상자에 대해서는 진행하지 않기 때문
    // 최대 같은 상자를 2번씩 확인할 수 있음
}
