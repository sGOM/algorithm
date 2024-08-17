import java.util.*;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        Map<Integer, Set<Integer>> roadMap = new HashMap<>();
        for (int[] road : roads) {
            int a = road[0];
            int b = road[1];
            
            roadMap.compute(a, (k, v) -> {
                if (v == null) v = new HashSet<>();
                v.add(b);
                return v;
            });
            roadMap.compute(b, (k, v) -> {
                if (v == null) v = new HashSet<>();
                v.add(a);
                return v;
            });
        }
        
        return dijkstra(n, roadMap, sources, destination);
    }
    
    public int[] dijkstra(int totalRegionNum, Map<Integer, Set<Integer>> roadMap, int[] sources, int destination) {
        Queue<Integer> queue = new ArrayDeque<>();
        int[] answer = new int[sources.length];
        int[] shortestDistance = new int[totalRegionNum + 1];
        for (int i = 0; i < shortestDistance.length; i++) {
            shortestDistance[i] = Integer.MAX_VALUE;
        }
        shortestDistance[destination] = 0;
        queue.add(destination);
        
        while (!queue.isEmpty()) {
            int node = queue.poll();
            
            for (int next : roadMap.get(node)) {
                int prevDistance = shortestDistance[next];
                int distance = shortestDistance[node] + 1;
                if (prevDistance > distance) {
                    queue.add(next);
                    shortestDistance[next] = distance;
                }
            }
        }
        
        for (int idx = 0; idx < sources.length; idx++) {
            int d = shortestDistance[sources[idx]];
            answer[idx] = (d == Integer.MAX_VALUE ? -1 : d);
        }
        
        return answer;
    }
}