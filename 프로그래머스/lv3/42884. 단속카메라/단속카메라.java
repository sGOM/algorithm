import java.util.*;

class Solution {
    // 최대한 각 경로가 끝나는 지점에 카메라를 배치해, 다음 경로와 겹칠 수 있게함
    public int solution(int[][] routes) {
        // 각 경로가 끝나는 지점을 기준으로 정렬
        Arrays.sort(routes, (a, b) -> a[1] - b[1]);
        
        // 초기 값 설정
        // 마지막으로 카메라를 설치한 위치를 초기화하고 정답을 1로 초기화
        int answer = 1;
        int lastCameraIndex = routes[0][1];
        
        // 2번째 경로부터 끝까지 순회
        for (int i = 1; i < routes.length; i++) {
            // 해당 경로를 이전에 설치한 카메라가 존재 하지 않는다면
            if (lastCameraIndex < routes[i][0] || routes[i][1] < lastCameraIndex) {
                // 해당 경로의 끝에 새로운 카메라를 설치하고 값을 갱신
                answer++;
                lastCameraIndex = routes[i][1];
            }
        }
        
        return answer;
    }
}