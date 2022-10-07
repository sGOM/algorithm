class Solution {
    // 이미 방문한 node 인지 체크하는 배열
    boolean[] isVisited;
    
    public int solution(int n, int[][] computers) {
        // node 개수만큼 초기화
        isVisited = new boolean[n];
        // 정답을 리턴할 변수 선언
        int answer = 0;
        
        // 반복문을 돌며 0~(n-1)번째 node에서 그래프 탐색을 시작
        for (int i = 0; i < computers.length; i++) {
            // i번째 node가 방문한적 없는 node면
            if (!isVisited[i]) {
                isVisited[i] = true;
                answer++;
                // 그래프 탐색
                exploringGraph(computers, i);
            }
        }
        
        return answer;
    }
    
    public void exploringGraph (int[][] computers, int from) {
        for (int i = 0; i < computers.length; i++) {
            // 자기 참조는 안됨
            if (from == i) continue;
            // 방문한적 없고 && from -> i가 연결되어 있다면
            if (!isVisited[i] && computers[from][i] == 1) {
                isVisited[i] = true;
                exploringGraph(computers, i);
            }
        }
    }
}