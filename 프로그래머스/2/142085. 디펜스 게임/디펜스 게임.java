import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int turn = 0;
        int enemySum = 0;       // 현재까지 적부대의 군 합계
        int enemySumTopK = 0;   // 현재까지 가장 큰 적부대 K개의 군 합계
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>();
        
        for (; turn < enemy.length; turn++) {
            maxHeap.add(enemy[turn]);
            enemySum += enemy[turn];
            enemySumTopK += enemy[turn];
            
            if (k < maxHeap.size()) {           // Heap의 크기가 k보다 크면
                enemySumTopK -= maxHeap.poll(); // 가장 작은 수를 제거
            }
            
            // 상위 K개의 부대를 무적권의로 방어하고 뚫린다면
            if (n < enemySum - enemySumTopK) {  
                return turn;
            }
        }
        
        return turn;
    }
}

/* 시간 복잡도
* a = enemy.length
* O(a log k)
* PriorityQueue의 add와 pull이 O(log n)
*/
