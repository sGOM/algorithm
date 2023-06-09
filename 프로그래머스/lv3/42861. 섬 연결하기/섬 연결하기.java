import java.util.*;

class Solution {
    public int solution(int n, int[][] costs) {
        HashSet<Integer> bridgedIslands = new HashSet<>();
        LinkedList<HashSet<Integer>> groups = new LinkedList<>();
        int answer = 0;
        
        // 건설 비용 순으로 정렬
        Arrays.sort(costs, Comparator.comparingInt(c -> c[2]));
        
        for (int[] cost : costs) {
            int island1 = cost[0], island2 = cost[1];
            
            // 두 섬 모두 아직 다리가 지어지지 않은 경우
            if (!bridgedIslands.contains(island1) && !bridgedIslands.contains(island2)) {
                HashSet<Integer> newGroup = new HashSet<>();
                newGroup.add(island1); newGroup.add(island2);
                groups.add(newGroup);
                bridgedIslands.add(island1); bridgedIslands.add(island2);
                answer += cost[2];
            // 두 섬 중 한 섬만 다리가 지어진 경우
            } else if (!bridgedIslands.contains(island1) == bridgedIslands.contains(island2)) {
                for (HashSet<Integer> group : groups) {
                    if (group.contains(island1) || group.contains(island2)) {
                        group.add(island1); group.add(island2);
                        bridgedIslands.add(island1); bridgedIslands.add(island2);
                        answer += cost[2];
                        break;
                    }
                }
            // 두 섬 모두 다리가 지어진 경우
            } else {
                ArrayList<HashSet<Integer>> tempList = new ArrayList<>();
                for (HashSet<Integer> group : groups) {
                    if (group.contains(island1)) tempList.add(group);
                    if (group.contains(island2)) tempList.add(group);
                    if (tempList.size() == 2) break;
                }
                
                HashSet<Integer> group1 = tempList.get(0);
                HashSet<Integer> group2 = tempList.get(1);
                // 두 섬이 다른 그룹에 속해 있다면
                if (!group1.equals(group2)) {
                    group1.addAll(group2);
                    groups.remove(group2);
                    answer += cost[2];
                }
            }
            
            if(bridgedIslands.size() == n && groups.size() == 1) break;
        }
        
        return answer;
    }
}