import java.util.Stack;

class Solution {
    public int[] solution(int[] prices) {
        // 정답 배열 생성
        int[] answer = new int[prices.length];
        // 가격이 떨어지지 않은 시점들 저장
        Stack<TimePoint> log = new Stack<>();

        // 주식가격 배열 순회
        for (int i = 0; i < prices.length; i++) {
            // 가격이 떨어진 시점이 있으면
            while (!log.empty() && prices[i] < log.peek().getValue()) {
                // 해당 시점의 가격 유지기간 기입
                int idx = log.pop().getTime();
                answer[idx] = i - idx;
            }
            // 현재 시점 추가
            log.push(new TimePoint(i, prices[i]));
        }
        // 마지막까지 가격이 떨어지지 않은 시점 유지기간 기입
        while (!log.empty()) {
            int idx = log.pop().getTime();
            answer[idx] = prices.length - (idx + 1);
        }

        return answer;
    }
    
    // 시점과 시점의 주식 가격 저장
    public class TimePoint {
        private int time;
        private int value;

        public TimePoint(int time, int value) {
            this.time = time;
            this.value = value;
        }

        public int getTime() { return time; }
        public void setTime(int time) { this.time = time; }

        public int getValue() { return value; }
        public void setValue(int value) { this.value = value; }
    }
}