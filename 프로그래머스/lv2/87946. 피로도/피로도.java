class Solution {
    public int solution(int k, int[][] dungeons) {
        boolean[] isVisited = new boolean[k];
        
        return dfs(0, k, dungeons, isVisited);
    }
    
    public int dfs(int sum, int fatigue, int[][] dungeons, boolean[] isVisited) {
        int maxSum = sum;
        for (int i = 0; i < dungeons.length; i++) {
            if (!isVisited[i] && dungeons[i][0] <= fatigue) { 
                isVisited[i] = true;
                maxSum = Math.max(dfs(sum + 1, fatigue - dungeons[i][1], dungeons, isVisited), maxSum);
                isVisited[i] = false;   
            }
        }
        return maxSum;
    }
}