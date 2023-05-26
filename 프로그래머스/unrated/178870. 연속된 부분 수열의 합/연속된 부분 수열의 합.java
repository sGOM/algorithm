class Solution {
    public int[] solution(int[] sequence, int k) {
        // 정답 배열, 최대값으로 설정
        int[] answer    = { 0, Integer.MAX_VALUE };
        // 현재 확인하고 있는 부분 수열
        int[] seq       = { 0, -1 };
        // 현재 확인하고 있는 부분 수열의 합
        int sum         = 0;
        
        // 부분 수열의 시작이 수열의 끝이 아닌 동안
        while (seq[0] != sequence.length - 1) {
            // (부분 수열의 합이 k보다 작거나 같으면 || 부분 수열의 시작과 끝이 같으면) && 부분 수열의 끝이 수열의 끝이 아니면
            if ((sum <= k || seq[0] == seq[1]) && seq[1] != sequence.length - 1) {
                sum += sequence[++seq[1]];
            // 부분 수열의 합이 k보다 크면
            } else {
                sum -= sequence[seq[0]++];
            }
            
            // 부분 수열의 합이 k와 같음 && 현재 정답보다 길이가 짧음
            if (sum == k && seq[1] - seq[0] < answer[1] - answer[0]) {
                // 정답 최신화
                answer[0] = seq[0];
                answer[1] = seq[1];
            }
        }
        
        return answer;
    }
    // 시간복잡도
    // O(sequence.length)
    // sequence.length의 2배만큼 반복하기 때문
}

/* 다른 사람이 작성한 가독성이 더 좋은 코드, 시간 복잡도는 동일하나 기대되는 실행횟수는 더 적고, 이해하기 쉬움
class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] result = new int[2];
        int start = 0, end = 0;
        int sum = 0, len = Integer.MAX_VALUE;

        while (end < sequence.length) {
            sum += sequence[end];
            end++;

            while (sum > k) {
                sum -= sequence[start];
                start++;
            }

            if (sum == k && end - start < len) {
                len = end - start;
                result[0] = start;
                result[1] = end - 1;
            }
        }

        return result;
    }
}
*/
