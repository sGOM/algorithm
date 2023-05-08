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