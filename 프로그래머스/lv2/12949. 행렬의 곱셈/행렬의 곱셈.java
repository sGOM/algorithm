class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int[][] answer = new int[arr1.length][arr2[0].length];
        
        for (int row = 0; row < answer.length; row++) {
            for (int col = 0; col < answer[0].length; col++) {
                for (int i = 0; i < arr1[0].length; i++) {
                    answer[row][col] += arr1[row][i] * arr2[i][col]; 
                }
            }
        }
        
        return answer;
    }
}