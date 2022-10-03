import java.util.Arrays;

class Solution {
    public int solution(int n, int k) {
        // 입력값 n진수 String으로 변환
        String      nNumberStr  = Long.toString(n, k);
        // 변환된 n진수 String을 0으로 끊은 값들을 long으로 변환한 배열
        long[]       numbersArr  = Arrays.stream(nNumberStr.split("0+"))
                                        .mapToLong(Long::parseLong)
                                        .toArray();
        
        // numbersArr 중 소수만 카운팅해서 반환
        return (int)Arrays.stream(numbersArr)
                            .filter(num -> isPrime(num))
                            .count();
    }
    
    // 소수 판별
    public boolean isPrime(long num) {
        if (num < 2) return false;
        for (long i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
    
    // 10진수 기준 소수 판별 배열, 에라토스테네스의 체
    // 들어오는 수가 int의 범위보다 크면 java에서는 할당을 할 수 없어 에라토스테네스의 체 사용 불가능
    // https://stackoverflow.com/questions/1071858/java-creating-byte-array-whose-size-is-represented-by-a-long
//     public void Eratos(boolean[] isPrime) {
//         final int end = isPrime.length - 1;
        
//         if (end < 1) return;
//         Arrays.fill(isPrime, true);
//         isPrime[0] = false; isPrime[1] = false;
        
//         for (int i = 2; i <= Math.sqrt(end); i++) {
//             if (isPrime[i]) {
//                 for (int j = 2; i * j <= end; j++) {
//                     isPrime[i * j] = false;
//                 }
//             }
//         }
//     }
