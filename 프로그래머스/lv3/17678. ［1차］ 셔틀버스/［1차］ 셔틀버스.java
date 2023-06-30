import java.util.*;
import java.util.stream.*;

class Solution {
    final String SHUTTLE_START_TIME = "09:00";
    final int MINUTE_PER_HOUR = 60;
    
    public String solution(int n, int interval, int shuttleCapacity, String[] timetable) {
        // O(n log n), n = timetable.length
        PriorityQueue<Integer> timeTableQueue = Arrays.stream(timetable)
                                                        .mapToInt(this::convertTimeToMinute).boxed()
                                                        .collect(Collectors.toCollection(PriorityQueue::new));
        
        int shuttleTime = convertTimeToMinute(SHUTTLE_START_TIME);
        int answer = -1;
        
        // O(n log n), n = timetable.length
        // 셔틀 운행 횟수만큼 순회
        for (int i = 0; i < n; i++, shuttleTime += interval) {
            int passengerCount = 0;
            int lastPassengerTime = -1;
            
            // (셔틀에 여유 공간이 있음) && (기다리는 사람이 있음)
            while (passengerCount < shuttleCapacity && !timeTableQueue.isEmpty() && timeTableQueue.peek() <= shuttleTime) {
                passengerCount++;
                lastPassengerTime = timeTableQueue.poll();
            }
            
            // 셔틀에 여유 공간이 있는 경우 -> 셔틀이 도착하는 시간에 줄을 섬
            // 셔틀에 여유 공간이 없는 경우 -> 마지막으로 셔틀에 탄 승객보다 1분 일찍 줄을 섬
            answer = (passengerCount < shuttleCapacity ? shuttleTime : lastPassengerTime - 1);
        }
        
        return convertMinuteToTime(answer);
    }
    
    // "HH:MM" -> int
    public int convertTimeToMinute(String time) {
        String[] part = time.split(":");
        if (part.length != 2) return -1;
        
        return Integer.parseInt(part[0]) * MINUTE_PER_HOUR + Integer.parseInt(part[1]);
    }
    
    // int -> "HH:MM"
    public String convertMinuteToTime(int minute) {
        return String.format("%02d:%02d", minute / MINUTE_PER_HOUR, minute % MINUTE_PER_HOUR);
    }
    
    /* 시간 복잡도
    * O(n log n), n = timetable.length
    *
    * timeTableQueue를 초기화하는 부분은 O(n log n)으로 고정되어 있으므로 설명 스킵함
    *
    * for - while의 2중 반복문에서 가장 많은 시간을 사용하는 연산은 timeTableQueue.poll()로 시간 복잡도는 O(log n) 임
    * timeTableQueue.poll()은 최대 timetable.length만큼만 실행 가능함
    */ 
}
