import java.util.Arrays;

class Solution {
    public int solution(int[][] triangle) {
        int     tri_h   = triangle.length;

        int[][] dp      = new int[tri_h][];
        for (int i = 0; i < tri_h; i++)
            dp[i] = new int[i + 1];

        dp[0][0] = triangle[0][0];
        for (int h = 1; h < tri_h; h++) {
            dp[h][0] = dp[h-1][0] + triangle[h][0];
            dp[h][h] = dp[h-1][h-1] + triangle[h][h];
            for (int w = 1; w <= h - 1; w ++) {
                dp[h][w] = triangle[h][w] + Math.max(dp[h-1][w-1], dp[h-1][w]);
            }
        }
        return Arrays.stream(dp[tri_h - 1])
                        .max()
                        .getAsInt();
    }
}