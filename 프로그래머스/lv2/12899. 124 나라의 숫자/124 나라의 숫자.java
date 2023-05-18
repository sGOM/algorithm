class Solution {
    public String solution(int n) {
        // 정답을 저장할 변수
        StringBuilder answer = new StringBuilder();
        // 진법 정의
        int[] scale = {1, 2, 4};
        // 자릿수를 확인하기 위해서는 1을 빼야함
        n -= 1;
        
        // 자릿수를 저장하는 변수
        int cipher = 1;
        // n의 자릿수 구하기
        while (pow(scale.length, cipher) <= n) {
            // n에서 cipher자릿수의 숫자 갯수만큼 빼기
            n -= pow(scale.length, cipher); 
            // 자릿수 + 1
            cipher++;
        }
        
        // 해당 시점에서 정답은, cipher 자릿수 숫자 중 n번째 자릿 수
        
        // 자릿수 만큼 순회하며 각 자릿수에 올 숫자 결정
        for (; 0 < cipher; cipher--) {
            int divider = pow(scale.length, cipher - 1);
            answer.append(scale[n / divider]);
            n %= divider;
        }
        
        return answer.toString();
    }
    
    // 제곱함수 이쁘게 뺌
    public int pow(int base, int exp) {
        return (int)Math.pow(base, exp);
    }
    
    // 시간복잡도 O(정답 자릿수)
    // Math.pow도 O(1)이기 때문
}
