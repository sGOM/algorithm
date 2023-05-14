import java.util.*;

class Solution {
    public int solution(int bridgeLength, int weight, int[] truckWeights) {
        // 다리 초기 설정, 트럭이 없는 부분은 0으로 채움
        Queue<Integer> bridge = new LinkedList<Integer>();
        for (int i = 0; i < bridgeLength; i++) bridge.add(0);
        int time = 0;
        int totalWeight = 0;
        
        // 모든 차를 순서대로 순회
        for (int truckWeight : truckWeights) {
            // 일단 차 1대 내보냄
            totalWeight -= bridge.poll();
            
            // 내보냈는데도 무게가 기준치 이상이면
            while (weight < totalWeight + truckWeight) {
                // 추가로 1대 더 내보내고 0짜리 추가
                totalWeight -= bridge.poll();
                bridge.add(0);
                // 시간 흐름
                time++;
            }
            
            // 새로운차 다리 위로 올림
            bridge.add(truckWeight);
            totalWeight += truckWeight;
            time++;
        }
        
        // 마지막 차가 지나가는 시간 추가
        time += bridgeLength;
        
        return time;
    }
}