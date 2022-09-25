class Solution
{
    public int solution(int n, int a, int b)
    {
        int answer = 1;
        a = (a - 1)/2; b = (b - 1)/2;
        
        while(a != b) {
            a /= 2;
            b /= 2;
            answer++;
        }

        return answer;
        // 다른 사람의 미친 풀이
        // return Integer.toBinaryString((a-1)^(b-1)).length();
    }
}