class Solution {
    public int solution(int n) {
        int answer = 0;

        int max = 0;
        while ((max * (max + 1)) / 2 < n) max++;

        for (int i = max; 1 <= i; i--) 
            if ((n - (i*(i-1)/2)) % i == 0) answer++;

        return answer;
    }
}