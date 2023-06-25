import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        // O(log k), k = times.length
        // 오름차순 정렬
        Arrays.sort(times);
        
        long low = (long)times[0];
        long high = (long)times[0] * n;
        
        // O(k * log i), k = time.length, i = (min(times) * n)
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
    // O(k * log i), k = time.length, i = (min(times) * n)
}
