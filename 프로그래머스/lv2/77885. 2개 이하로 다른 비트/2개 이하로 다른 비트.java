class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        
        for (int i = 0; i < numbers.length; i++) {
            answer[i] = func(numbers[i]);
        }
        
        return answer;
    }
    
    // 변환 함수
    public long func(long number) {
        // 쉬운 변환을 위해 맨앞에 0을 추가 (값은 변하지 않음) 
        StringBuilder binaryNum = new StringBuilder("0" + Long.toBinaryString(number));
        // 가장 뒤쪽에 있는 0 탐색
        int idx = binaryNum.lastIndexOf("0");

        return (binaryNum.length() - idx <= 2)
            // 맨 끝, 2개의 비트 중에 0이 존재하는 경우 기존 값에 +1
            ? number + 1L
            // 그렇지 않은 경우, 발견한 위치에 존재하는 "01"을 "10"으로 변경
            : Long.parseLong(binaryNum.replace(idx, idx + 2, "10").toString(), 2);
    }
}

/* 다른 사람의 풀이
// 동작은 내 풀이와 동일하지만 코드와 실행 속도는 훨씬 좋음
// answer[i]++;이 가장 처음나오는 0을 1, 그 이전 값을 모두 0으로 바꿈 (ex. 11101111 -> 11110000)
// (answer[i]^numbers[i])>>>2; 는 (바뀐 비트의 길이 - 2)만큼 1로 채운 값을 생성함 (ex. 00000111)
// 해당 값을 더 하면 동일한 결과 도출

class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = numbers.clone();
        for(int i = 0; i < answer.length; i++){
            answer[i]++;
            answer[i] += (answer[i]^numbers[i])>>>2;
        }
        return answer;
    }
}
*/
