import java.util.PriorityQueue;

class Solution {
    public int[] solution(String[] operations) {
        // 오름차순 PriorityQueue
        PriorityQueue<Integer> minPriQueue = new PriorityQueue<>();
        // 내림차순 PriorityQueue
        PriorityQueue<Integer> maxPriQueue = new PriorityQueue<>(11, (a, b) -> b - a);

        // 명령어 배열을 순회
        for (String op : operations) {
            // 명령어 유형에 따라 실행
            switch (op.charAt(0)) {
                // 삭제 명령인 경우
                case 'D' :
                    // PriorityQueue의 크기가 0이면 아무것도 안함
                    if(minPriQueue.size() == 0) break;
                    switch (Integer.parseInt(op.substring(2))) {
                        // 최소값 제거
                        case -1 :
                            maxPriQueue.remove(minPriQueue.poll()); break;
                        // 최대값 제거
                        case  1 :
                            minPriQueue.remove(maxPriQueue.poll()); break;
                    }
                    break;
                // 삽입 명령인 경우
                case 'I' :
                    // 2가지 PriorityQueue에 명령어가 준 숫자 추가
                    minPriQueue.add(Integer.parseInt(op.substring(2)));
                    maxPriQueue.add(Integer.parseInt(op.substring(2)));
                    break;
            }
        }

        // 명령어 실행이 끝난 후 PriorityQueue의 크기가 0이면 [0, 0] 반환
        if (minPriQueue.size() == 0) return new int[] {0, 0};
        // 그렇지 않다면 [최대값, 최소값] 반환
        return new int[] {maxPriQueue.peek(), minPriQueue.peek()};
    }
}
