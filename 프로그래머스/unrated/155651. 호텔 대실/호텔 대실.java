import java.util.*;
import java.util.stream.*;

class Solution {
    final int CLEANING_TIME = 10;
    
    public int solution(String[][] bookTime) {
        // 손님들의 체크인 시간 (정렬됨)
        Queue<Integer> checkInQueue     = Arrays.stream(bookTime)
                                                .map(time -> timeConvertToMinute(time[0]))          // O(n)
                                                .sorted()                                           // O(n log n)
                                                .collect(Collectors.toCollection(LinkedList::new)); // O(n)
        // 손님들의 체크 아웃 시간 (정렬됨)
        Queue<Integer> checkOutQueue    = Arrays.stream(bookTime)
                                                .map(time -> timeConvertToMinute(time[1]) + CLEANING_TIME) // O(n)
                                                .sorted()                                           // O(n log n)
                                                .collect(Collectors.toCollection(LinkedList::new)); // O(n)
        
        int answer = 0;
        int numOfRoom = 0;  // 현재 객실 수
        // O(n), n = bookTime.length
        // 더 이상의 체크인이 없을 때 동안
        while (!checkInQueue.isEmpty()) {
            // 다음 체크아웃이 체크인 보다 빠른 경우
            if (checkOutQueue.peek() <= checkInQueue.peek()) {
                // 객실 하나를 비움
                checkOutQueue.poll();
                numOfRoom--;
            // 다음 체크인이 체크아웃 보다 빠른 경우
            } else {
                // 객실 하나를 추가함
                checkInQueue.poll();
                numOfRoom++;
            }
            
            // 객실 최댓값 갱신
            answer = Math.max(answer, numOfRoom);
        }
        
        return answer;
    }
    
    public int timeConvertToMinute(String time) {
        String[] times = time.split(":");
        return Integer.valueOf(times[0]) * 60 + Integer.valueOf(times[1]);
    }
    
    // 시간 복잡도
    // O(n log n), n = bookTime.length
}