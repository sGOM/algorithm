import java.util.*;

// 용어 정의
// 그룹 - 다리로 서로 오갈 수 있는 섬의 집합
class Solution {
    public int solution(int n, int[][] costs) {
        LinkedList<HashSet<Integer>> groups = new LinkedList<>();       // 여러 그룹들을 저장
        ArrayList<HashSet<Integer>> findedGroup = new ArrayList<>();    // 현재 건설을 고려하는 다리들의 그룹들
        int answer = 0;
        
        // 건설 비용 순으로 정렬
        Arrays.sort(costs, Comparator.comparingInt(c -> c[2]));
        
        // 건설 비용 오름차순으로 모든 다리 후보를 순회
        for (int[] cost : costs) {
            int island1 = cost[0], island2 = cost[1];
            
            // 이미 다리가 지어진(그룹에 속해있는) 섬이라면, 해당 섬이 속한 그룹을 findedGroup에 추가 
            for (HashSet<Integer> group : groups) {
                if (group.contains(island1)) findedGroup.add(group);
                if (group.contains(island2)) findedGroup.add(group);
                if (findedGroup.size() == 2) break;
            }
            
            // 이미 그룹에 속한 섬의 갯수에 따라
            switch (findedGroup.size()) {
                // 두 섬 모두 그룹에 속하지 못한 경우 (다리가 하나도 안지어진 경우)
                // 새로운 그룹 생성
                case 0:
                    groups.add(new HashSet<>(List.of(island1, island2)));
                    answer += cost[2];
                    break;
                // 한 섬만 그룹에 속한 경우
                // 그룹을 가지지 못한 섬을 해당 그룹에 추가
                case 1:
                    HashSet<Integer> group = findedGroup.get(0);
                    group.addAll(List.of(island1, island2));
                    answer += cost[2];
                    break;
                // 두 섬 모두 그룹에 속한 경우
                case 2:
                    HashSet<Integer> group1 = findedGroup.get(0);
                    HashSet<Integer> group2 = findedGroup.get(1);
                    // 이미 같은 그룹이 아니라면
                    if (!group1.equals(group2)) {
                        // 두 그룹을 합침
                        group1.addAll(group2);
                        groups.remove(group2);
                        answer += cost[2];
                    }
                    break;
            }
            
            // 모든 섬이 1개의 그룹이 되었다면 반복문 종료
            if(groups.get(0).size() == n) break;
            findedGroup.clear();
        }
        
        return answer;
    }   
    // 시간 복잡도
    // O(n * k), n = costs.length, k = groups.length <= (섬 갯수)/2
}
