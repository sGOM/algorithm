import java.util.*;

class Solution {
    public int solution(int n, int[] stations, int w) {
        // 정답을 저장할 변수
        int answer = 0;
        // 전파가 닿지않는 연속된 아파트 한 줄을 line이라고 하면, lines는 해당 line들의 길이를 저장
        Queue<Integer> lines = new LinkedList<>();
        
        // lines에 값 저장
        {
            // 마지막으로 등장할 station의 위치를 저장할 변수 선언
            // 추가로 첫 부분처리
            int lastStationIdx = -w;
            // 나머지 부분처리
            for (int stationIdx : stations) {
                int line = (stationIdx - w - 1) - (lastStationIdx + w + 1) + 1;
                if (0 < line) lines.add(line);  // line이 존재한다면 Queue에 추가
                lastStationIdx = stationIdx;    // lastStationIdx 갱신
            }
            // 마지막 부분처리
            if (lastStationIdx + w < n) lines.add(n - (lastStationIdx + w));
        }
        
        // 설치할 기지국 갯수 계산
        while (!lines.isEmpty()) {
            int lineLen = lines.poll();
            // line (전파가 닿지않는 연속된 아파트 한 줄)
            // (line / (기지국 한개가 커버할 수 있는 아파트 수)) + if (나머지가 존재한다면 +1)
            answer += (lineLen / (2 * w + 1)) + ((lineLen % (2 * w + 1) == 0) ? 0 : 1);
        }

        return answer;
    }
    // 시간복잡도 O(stations.length)
    // 그냥 stations 배열의 길이만큼만 2번 순회하기 때문 
}