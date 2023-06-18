import java.util.*;

class Solution {
    final int NO_EDGE = -1;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        // 간선의 가중치(요금)을 2차원 배열에 저장
        int[][] edges = new int[n][n];
        initEdges(edges, fares);
        
        // s, a, b에서 출발해 각 지점으로 이동할 때 지불해야하는 최소값(요금)을 저장
        int[] sDijkstra = new int[n];
        int[] aDijkstra = new int[n];
        int[] bDijkstra = new int[n];
        dijkstra(sDijkstra, edges, --s);
        dijkstra(aDijkstra, edges, --a);
        dijkstra(bDijkstra, edges, --b);
        
        // 택시 요금의 최솟값 계산
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (sDijkstra[i] != Integer.MAX_VALUE)  // 가는 길이 없는 경우는 제외
                answer = Math.min(answer, sDijkstra[i] + aDijkstra[i] + bDijkstra[i]);
        }
        
        return answer;
    }
    
    // O(V^2), V = (정점 수)
    public void initEdges(int[][] edges, int[][] fares) {
        for (int i = 0; i < edges.length; i++) Arrays.fill(edges[i], -1);
        for (int[] fare : fares) {
            int v1 = fare[0] - 1, v2 = fare[1] - 1;
            edges[v1][v2] = fare[2];
            edges[v2][v1] = fare[2];
        }
    }
    
    // O((V + E)logV), V = (정점 수), E = (간선 수)
    // start에서 각 지점으로 이동할 때 드는 비용의 최솟값을 구하는 함수 
    public void dijkstra(int[] distances, int[][] edges, int start) {
        Queue<Integer> vertices = new LinkedList<>(List.of(start));
        
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[start] = 0;   // 자기 자신으로 가는 비용은 0
        
        while (!vertices.isEmpty()) {
            int from = vertices.poll();
            for (int to = 0; to < edges[from].length; to++) {
                // from <-> to 사이에 간선이 존재하는 경우
                if (edges[from][to] != NO_EDGE) {
                    // start -> to 비용과 start -> from -> to의 비용을 비교하고 최솟값 갱신
                    int newDistance = distances[from] + edges[from][to];
                    if (newDistance < distances[to]) {
                        distances[to] = newDistance;
                        vertices.add(to);
                    }
                }
            }
        }
    }
    
    // 시간 복잡도
    // O((V + E)logV), V = (정점 수), E = (간선 수)
    // dijkstra 알고리즘이 제일 시간을 잡아먹음
}