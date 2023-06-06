import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) throws Exception {
        // O(k), k = edge.length
        // key - value끼리 edge로 연결되어 있게끔 저장 
        HashMap<Integer, HashSet<Integer>> matrix = new HashMap<>();
        for (int[] e : edge) {
            matrix.computeIfAbsent(e[0], k -> new HashSet<>()).add(e[1]);
            matrix.computeIfAbsent(e[1], k -> new HashSet<>()).add(e[0]);
        }
        
        // 이미 방문한 nodes
        HashSet<Integer> nodeVisited = new HashSet<>();
        // 마지막으로 방문한 nodes
        HashSet<Integer> nodeLastVisited = new HashSet<>();
        
        // 시작 지점 1로 시작
        int answer = 0;
        nodeVisited.add(1);
        nodeLastVisited.add(1);
        
        // 시간 복잡도
        // 삼중 반복문이 제일 비중있게 계산하며, 제일 내부에 있는 HashSet.add가 제일 많이 반복되고 이 이상의 시간을 잡아먹는 연산이 없음
        // O(k), k = edge.length
        // 마지막으로 방문한 node와 이어진 node가 있는 경우
        while (nodeLastVisited.size() != 0) {
            // 마지막으로 방문한 node의 갯수가 가장 멀리 떨어진 노드의 갯수
            answer = nodeLastVisited.size();
            // 이번에 방문할 node들 저장
            int[] curNodes = nodeLastVisited.stream().mapToInt(Integer::intValue).toArray();
            // 마지막으로 방문한 node 초기화
            nodeLastVisited.clear();
            
            // 이번에 방문할 node들을 순회
            for (int node : curNodes) {
                // 이번에 방문한 node를 삭제하고, 해당 노드에서 접근할 수 있는 node들을 순회
                HashSet<Integer> nextNodes = matrix.remove(node);
                Iterator<Integer> iter = nextNodes.iterator();
                while (iter.hasNext()) {
                    // 방문 한적 없는 노드라면, nodeLastVisited에 추가
                    int nextNode = iter.next();
                    if (nodeVisited.add(nextNode)) {
                        nodeLastVisited.add(nextNode);
                    }
                }
            }
        }
        
        return answer;
    }
    
    // 시간 복잡도
    // O(k), k = edge.length
}