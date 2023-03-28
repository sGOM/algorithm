class Solution {
    final int DIVIDER = 1_000_000_007;
    public int solution(int m, int n, int[][] puddles) {
        // dp용 배열 선언
        int[][] wayToSchool = new int[m][n];
        // 웅덩이를 -1로 마킹
        for (int[] puddle : puddles) wayToSchool[puddle[0]-1][puddle[1]-1] = -1;
        // 0번째 행, 열을 각각 웅덩이 이전까지 1로 초기화
        for (int i = 0; i < m && wayToSchool[i][0] != -1; i++) wayToSchool[i][0] = 1;
        for (int i = 0; i < n && wayToSchool[0][i] != -1; i++) wayToSchool[0][i] = 1;
        
        // 1번째 행, 열 부터 시작해 순회하며 최단 경로 갯수 카운팅
        for (int row = 1; row < n; row++) {
            for (int col = 1; col < m; col++) {
                // 현재 길이 웅덩이면 바로 다음으로
                if (wayToSchool[col][row] == -1) continue;
                // (이전 열까지의 경우의 수) + (이전 행까지의 경우의 수), 웅덩이인 경우 제외
                wayToSchool[col][row] += (wayToSchool[col - 1][row] == -1 ? 0 : wayToSchool[col - 1][row]);
                wayToSchool[col][row] += (wayToSchool[col][row - 1] == -1 ? 0 : wayToSchool[col][row - 1]);
                // 문제에서 주어진 조건
                wayToSchool[col][row] %= DIVIDER;
            }
        }
        
        // 도착지에서 가지는 경우의 수
        return wayToSchool[m - 1][n - 1];
    }
}