class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder answer = new StringBuilder(t+1);
        
        int nthTurn = 0;
        if (p == 1) { answer.append("0"); nthTurn++; } // 첫번째라 0을 말해야하는 경우 수열이 적용되지 않으므로 미리 처리
        
        int digit = 1;  // 한 자리 수 먼저 시작
        int digitSum = 1;   // 0은 미리 처리했으므로 현재까지 나온 자릿수의 합에 추가
        
        for (; nthTurn < t; nthTurn++) {
            // 이번 턴의 나의 차례
            int index = p + m * nthTurn;
            
            while (true) {
                // (현재 자리수가 가지는 모든 경우의 수 * 자리 수)
                int curDigitSum = (n - 1) * ((int)Math.pow(n, digit - 1)) * digit;

                // 나의 차례가 이번 자릿 수에 존재하는 경우 반복문 탈출
                if (index <= digitSum + curDigitSum) break;
                // 아닌 경우 자릿 수를 올림
                digitSum += curDigitSum;
                digit++;
            }
            // 현재 자리 수의 숫자들을 모두 늘어놨을 때 원하는 자리가 존재하는 index
            int targetIndex = (index - digitSum - 1);
            
            // digit 자리수를 가진 n진수의 첫번째 숫자
            int firstNumber = Integer.parseInt("1" + "0".repeat(digit - 1), n);
            // 현재 (digit)자리 수 중에 targetIndex / digit 번째 숫자
            int targetNumber = firstNumber + targetIndex / digit;
            // 그 것의 targetIndex % digit 번째 자리 수
            String targetInBaseN = Integer.toString(targetNumber, n);
            String targetDigit = targetInBaseN.substring(targetIndex % digit, targetIndex % digit + 1).toUpperCase();
            
            answer.append(targetDigit);
        }
        return answer.toString();
    }
}