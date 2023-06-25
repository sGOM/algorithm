import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        // O(log k), k = times.length
        // 오름차순 정렬
        Arrays.sort(times);
        
        long low = 0L;
        long high = 1L;
        
        // O(k * log a), k = times.length, a = (모든 사람이 심사를 받는데 걸리는 최소 시간, 정답)
        // 탐색 범위 설정
        while (getPassCount(times, high) < n) {
            low = high;
            high *= 2;
        }
        
        // O(log i), i = (high - low)
        // 이분 탐색
        while (low < high) {
            long mid = (high + low) / 2L;
            if (getPassCount(times, mid) < n) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        
        return high;
    }
    
    // O(k), k = time.length
    // 주어진 시간(time)안에 최대 몇명이 통과할 수 있는지 반환
    public long getPassCount(int[] times, long time) {
        long passCount = 0L;
        for (long t : times) {
            long pass = time / t;
            passCount += pass;
            if (pass == 0L) break;  // times는 정렬되어 있으므로, 이 이상은 의미 없음
        }
        return passCount;
    }
    
    // 시간 복잡도
    // O(k * log a), k = times.length, a = (모든 사람이 심사를 받는데 걸리는 최소 시간, 정답)
}
