import java.util.*;

class Solution {
    public int[] solution(int n, long k) {
        int[] answer = new int[n];
        int[] factorialList = new int[n];
        
        // 1 ~ n까지 순서대로 저장
        List<Integer> numbers = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) numbers.add(i);
        
        // k를 0부터 시작하는 인덱스로 변경
        k--;
        // 전체 경우의 수
        long numOfcase = factorial(n);
        // n 번 순회하며
        for (int i = 0; i < answer.length; i++) {
            // 정답 배열의 i 번째 수를 제외한 경우 수로 갱신
            numOfcase /= (n - i);   // 3 -> 2! 1! 1
            // O(n), n = numbers.size()
            // numbers의 (k / numOfCase)번째 수가 answer[i] 번째 수
            answer[i] = numbers.remove((int)(k / numOfcase));
            // i 번째 수는 정해 졌으니, 해당 경우의 수를 제외
            k %= numOfcase;
        }
        
        return answer;
    }
    
    public long factorial(long num) {
        long res = 1;
        for (long i = 2; i <= num; i++) res *= i;
        
        return res;
    }
    
    /*
    public long factorial(int num, int[] factorialList) {
        int idx = factorialList.length - 1;
        factorialList[idx--] = 1;
        for (int i = 1; 0 <= idx; i++, idx--) {
            factorialList[idx] = factorialList[idx + 1] * i;
        }
        
        return res;
    }*/
    
    // 시간 복잡도
    // O(n^2), n = (일렬로 설 사람의 수)
}
