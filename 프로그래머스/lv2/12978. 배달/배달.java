import java.util.*;

class Solution {
    public int solution(int N, int[][] road, int deliveryDistanceLimit) {
        // 해당 HashMap은 조건으로 주어지는 int[][] road에 대한 정보만 저장하며, 2차원 배열로 그래프를 연결하는 것보다 적게 반복하고 싶어서 작성함
        // K : (출발 마을 번호), V : (K : (목적 마을 번호), V : (출발 마을에서 목적 마을까지의 도로 길이))
        HashMap<Integer, HashMap<Integer, Integer>> roadMap = new HashMap<>();
        HashMap<Integer, Integer> shortestDistanceMap       = new HashMap<>();  // K : (목적 마을 번호), V : (1번 마을에서 K번 마을까지의 최단거리)
        Queue<Integer> townQueue    = new LinkedList<>();                       // bfs에서 사용할 Queue
        
        // 초기화
        initRoadMap(roadMap, road);
        // 배달 시작 마을 추가
        townQueue.add(1); shortestDistanceMap.put(1, 0);
        
        // bfs를 이용해 각 마을에 도착하는 최단 거리를 계산
        while (!townQueue.isEmpty()) {
            // 다음 마을 탐색
            int startTown = townQueue.poll();
            // 해당 마을과 연결된 도로들을 모두 탐색
            for (Map.Entry<Integer, Integer> r : roadMap.get(startTown).entrySet()) {
                int destination = r.getKey();                                       // 목적지
                int distance = r.getValue() + shortestDistanceMap.get(startTown);   // startTown을 거쳐 destination까지 가는 최단거리
                
                // 거리가 (배달 가능 최대 거리)보다 클 경우 건너뜀 
                if (deliveryDistanceLimit < distance) continue;
                // (현재까지 저장된 destination까지의 길이 없음) || (distance가 저장된 최단거리 보다 짧음)
                if (!shortestDistanceMap.containsKey(destination) || distance < shortestDistanceMap.get(destination)) {
                    // 최단 거리 갱신하며, 최단 거리가 갱신된 마을은 다시 Queue에 추가됨
                    shortestDistanceMap.put(destination, distance);
                    townQueue.add(destination);
                }
            }
        }
        
        return shortestDistanceMap.size();
    }
    
    // O(n), n = road.length
    public void initRoadMap(HashMap<Integer, HashMap<Integer, Integer>> roadMap, int[][] road) {
        for (int[] r : road) {
            roadMap.computeIfAbsent(r[0], (k) -> new HashMap<>());
            roadMap.computeIfAbsent(r[1], (k) -> new HashMap<>());
            roadMap.get(r[0]).compute(r[1], (k, v) -> v == null ? r[2] : Math.min(v, r[2]));
            roadMap.get(r[1]).compute(r[0], (k, v) -> v == null ? r[2] : Math.min(v, r[2]));
        }
    }
    
    // 시간복잡도 (정확하지 않음)
    // O(V + E), V = (마을 갯수), E = (마을 갯수)
    // 모든 마을과 도로를 최소 한 번씩 탐색하기 때문
    
    // 정확하지 않다고 생각하는 이유
    // 위에 풀이법은 백트래킹을 추가한 Node 1과 Node n (2 <= n <= N)의 최단거리를 모두 구하는 알고리즘
    // 간단하게, (가중치있는 양방향 그래프의 최단거리 구하기 + 백트래킹) * N 정도로 말할 수 있음
    // 그렇다면 최악에 최악의 경우 O((V + E) * V)가 될 수도 있다고 생각함 (물론 그럴 가능성은 굉장히 희박)
}
