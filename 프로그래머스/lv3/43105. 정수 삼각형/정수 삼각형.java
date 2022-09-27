import java.util.Arrays;

class Solution {
    public int solution(int[][] triangle) {
        // 삼각형 높이 구하기
        int     tri_h   = triangle.length;

        // 0번째 층을 제외하고 시작
        for (int h = 1; h < tri_h; h++) {
            // 삼각형 변 쪽은 경로가 하나씩 뿐
            triangle[h][0] += triangle[h-1][0];
            triangle[h][h] += triangle[h-1][h-1];
            // 길이 2개 있을 수 있는 경우 두 경로 중 max를 더함
            for (int w = 1; w <= h - 1; w ++) {
                triangle[h][w] += Math.max(triangle[h-1][w-1], triangle[h-1][w]);
            }
        }
        // 마지막 층에 값들 중 max
        return Arrays.stream(triangle[tri_h - 1])
                        .max()
                        .getAsInt();
    }
}
