import java.util.*;

class Solution {
    final int FIRST_PILLAR = 1;
    final int SECOND_PILLAR = 2;
    final int THIRD_PILLAR = 3;
    
    public int[][] solution(int n) {
        LinkedList<int[]> answer = new LinkedList<>();
        
        hanoi(answer, n, FIRST_PILLAR, SECOND_PILLAR, THIRD_PILLAR);
        
        return answer.toArray(new int[answer.size()][]);
    }
    
    // O(2^n), n = height
    // 하노이 재귀 함수
    // height 크기 만큼의 원판을 via를 사용해 from에서 to로 옮김
    public void hanoi(LinkedList<int[]> moves, int height, int from, int via, int to) {
        if (height == 0) return;
        // (height - 1)만큼 from에서 via로 옮김
        hanoi(moves, height - 1, from, to, via);
        // 가장 큰 원판을 from에서 to로 옮김
        moves.add(new int[]{from, to});
        // via에 옮겨둔 원판들을 다시 to로 옮김
        hanoi(moves, height - 1, via, from, to);
    }
    
    // 시간 복잡도
    // O(2^n), n =(옮겨야하는 원판의 갯수)
    // 증명
    // a(k) = (옮겨야하는 원판의 갯수가 k개인 하노이 탑), a(1) = 1
    // k개를 옮기기 위해서 via에 (k-1)개를 옮긴다음 1개를 옮기고 다시 via에 (k-1)를 to로 옮겨야하므로 
    // a(k) 
    // = a(k-1) + 1 + a(k-1) = 2 * a(k-1) + 1 
    // = Σ(i = 1 to k) { 2^(i - 1) }
    // = 2^k - 1
}
