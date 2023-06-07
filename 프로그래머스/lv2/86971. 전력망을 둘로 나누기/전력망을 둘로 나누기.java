import java.util.*;

class Solution {
    // boolean[i][j]는 노드 i와 노드 j의 연결 여부를 나타냄, 만약 boolean[i][j] == true라면 boolean[j][i]도 true
    boolean[][] matrix;
    // 모든 노드들의 간선(부모 -> 자식)에 연결된 자손들의 수 가중치로 해서 저장
    HashSet<Integer> weights = new HashSet<>();
    
    public int solution(int n, int[][] wires) {
        // 맴버 변수 초기화
        matrix = new boolean[n][n];
        for (int[] wire : wires) {
            matrix[wire[0] - 1][wire[1] - 1] = true;
            matrix[wire[1] - 1][wire[0] - 1] = true;
        }
        
        // 가중치 계산, 0을 root로 잡음
        calcWeight(0);
        
        // (n - (가중치 * 2)) 중 최소 값을 정답으로 반환
        // 해당 값이 전선들 중 하나를 끊어서 송전탑 개수가 가능한 비슷하도록 두 전력망으로 나누었을 때, 두 전력망이 가지고 있는 송전탑 개수의 차이(절대값)
        return weights.stream().mapToInt(w -> Math.abs(n - w * 2)).min().getAsInt();
    }
    
    // O(n), n = (송전탑 갯수)
    public int calcWeight(int parent) {
        // 현재 parent 노드 자기 자신
        int weight = 1;
        
        // child 노드들의 가중치를 모두 합
        for (int i = 0; i < matrix[parent].length; i++) {
            if (matrix[parent][i]) {
                // (자식 -> 부모)로 연결되는 간선은 끊음
                matrix[i][parent] = false;
                weight += calcWeight(i);
            }
        }
        
        // 계산된 가중치를 set에 추가
        weights.add(weight);
        
        return weight;
    }
    
    // 시간 복잡도
    // O(n), n = (송전탑 갯수)
}