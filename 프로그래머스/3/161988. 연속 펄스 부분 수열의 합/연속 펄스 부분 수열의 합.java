/* 카데인 알고리즘
* pre_sum_max = (누적 합 최댓 값)
* pre_sum_min = (누접 합 최솟 값)
* big_seq = (부분 수열의 합 중 가장 큰 것) 라고 하면
* pre_sum_max - pre_sum_min => big_seq
* (누적 합이 최솟 값이 되는 지점) == (big_seq의 시작 부분 인덱스 - 1)
* (누적 합이 최댓 값이 되는 지점) == (big_seq의 끝 부분 인덱스)
* ====
* 간단 설명
* big_seq의 시작 인덱스부터 역순으로 수를 더해 나간다고 할 때,
* 그게 만약 누적합에 -가 되는 부분이 었으면 누적 합 최솟값에 포함되었을 것이다.
* 그렇기 때문에 (누적 합이 최솟 값이 되는 지점) == (big_seq의 시작 부분 인덱스 - 1)이 될 수 밖에 없다
* 또한, 동일한 이유로 (누적 합이 최댓 값이 되는 지점) == (big_seq의 끝 부분 인덱스)이 된다.
* ====
* O(n)
*/
class Solution {
    public long solution(int[] sequence) {
        // pre_sum_max과 pre_sum_min 저장
        // pre_sum_max 혹은 pre_sum_min 둘 중 하나에 sequence의 첫 번째 원소가 포함 된 경우,
        // 누적합에 +가 되는 부분을 제거하지 않도록 초기값을 0으로 설정한다
        long max = 0;
        long min = 0;
        
        // 누적 합, 펄스
        long pulse = 1L;
        long prefixSum = 0L;
        
        // O(n)
        for (long seq : sequence) {
            prefixSum += seq * pulse;
            max = Math.max(max, prefixSum);
            min = Math.min(min, prefixSum);
            pulse *= -1L;
        }
        
        return Math.abs(max - min);
    }
}

/* 
이전 코드
*/
// import java.util.*;

// class Solution {
//     public long solution(int[] sequence) {
//         List<Long> arr = new ArrayList<>();
        
//         for (int idx = 0, pulse = 1; idx < sequence.length; idx++, pulse *= -1) {
//             sequence[idx] *= pulse;
            
//             if (idx == 0 || !areSignEqual(sequence[idx - 1], sequence[idx])) {
//                 arr.add(Math.abs((long)sequence[idx]));
//             } else {
//                 arr.set(arr.size() - 1, arr.get(arr.size() - 1) + Math.abs(sequence[idx]));
//             }
//         }
        
//         long max = 0;
//         long val = 0;
//         for (int idx = 0; idx < arr.size(); idx += 2) {
//             val = 0;
//             for (int i = 0, pulse = 1; idx + i < arr.size(); i++, pulse *= -1) {
//                 val += pulse * arr.get(idx + i);
//                 max = Math.max(max, val);
//                 if (val <= 0) {
//                     idx += (i / 2) * 2 + 2;
//                     break;
//                 }
//             }
//         }
        
//         for (int idx = 1; idx < arr.size(); idx += 2) {
//             val = 0;
//             for (int i = 0, pulse = 1; idx + i < arr.size(); i++, pulse *= -1) {
//                 val += pulse * arr.get(idx + i);
//                 max = Math.max(max, val);
//                 if (val <= 0) {
//                     idx += (i / 2) * 2 + 2;
//                     break;
//                 }
//             }
//         }
        
//         return max;
//     }
    
//     public boolean areSignEqual(int num1, int num2) {
//         return (num1 <= 0 && num2 <= 0) || (num1 > 0 && num2 > 0);
//     }
// }
