import java.util.*;

class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        // 최대 공약수 (가진 카드들에 적힌 숫자를 나눌 수 있는 가장 큰 값)
        int gcdA = getGcdOfArray(arrayA);
        int gcdB = getGcdOfArray(arrayB);
        
        int answer = 0;
        if (checkGcdIsA(arrayB, gcdA)) answer = Math.max(answer, gcdA);
        if (checkGcdIsA(arrayA, gcdB)) answer = Math.max(answer, gcdB);
        
        return answer;
    }
    
    // O(n), n = opponentArray.length
    // 이 최대 공약수가 a인지 확인
    // 최대 공약수가 a가 아니라면, 다른 공약수도 최대 공약수의 일부기 때문에 불가능
    public boolean checkGcdIsA(int[] opponentArray, int gcd) {
        for (int num : opponentArray) {
            if (num % gcd == 0) return false;
        }
        
        return true;
    }
    
    // O(k log n), k = array.length, n = array[0]
    public int getGcdOfArray(int[] array) {
        int gcd = array[0];
        for (int num : array) {
            gcd = getGcd(gcd, num);
            if (gcd == 0) return 0;
        }
        
        return gcd;
    }
    
    // O(log n), n = min(a, b)
    // 유클리드 호제법 (최대공약수)
    public int getGcd(int a, int b) {
        if (b == 0) return a;
        return getGcd(b, a % b);
    }
    
    // 시간 복잡도
    // O(k log n), k = array.length, n = array[0]
    // Solution의 매개변수로 주어진 각 배열의 최대 공약수를 구하는 것이 제일 많은 연산을 필요로 함
}

