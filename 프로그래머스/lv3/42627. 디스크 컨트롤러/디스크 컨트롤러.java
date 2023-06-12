import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        // 대기 중인 작업들의 소요시간을 저장
        PriorityQueue<Integer> jobQueue = new PriorityQueue<>();
        // 작업 요청과 작업 완료 시점을 저장
        PriorityQueue<JobTime> timeQueue = new PriorityQueue<>();
        // 작업 요청 시간 추가
        for (int[] job : jobs) timeQueue.add(new JobTime(job[0], job[1], true));
        
        int answer = 0;
        // 이전 시점
        int prevTime = 0;
        // 모든 작업이 종료될 때까지
        while (!timeQueue.isEmpty()) {
            // 다음 시점
            JobTime curTime = timeQueue.poll();
            
            // (정답) += (이전 시점 - 해당 시점) * (기다린 작업의 갯수) 
            answer += (curTime.time - prevTime) * jobQueue.size();
            // 작업이 요청되는 시점인 경우
            if (curTime.isRequestTime) {
                // 대기중인 작업이 없다면
                if (jobQueue.isEmpty()) {
                    // 요청된 작업을 바로 실행 (작업 종료 시점을 추가)
                    timeQueue.add(new JobTime(curTime.time + curTime.processingTime, curTime.processingTime, false));
                }
                // 작업 대기열에 추가
                jobQueue.add(curTime.processingTime);
                
            // 작업이 완료되는 시점인 경우
            } else {
                // 작업 대기열에서 완료된 작업 제거
                jobQueue.remove(curTime.processingTime);
                
                // 대기 중인 작업이 있다면
                if (!jobQueue.isEmpty()) {
                    // 대기 중인 작업 바로 실행 (작업 종료 시점을 추가)
                    int processingTime = jobQueue.peek();
                    timeQueue.add(new JobTime(curTime.time + processingTime, processingTime, false));
                }
            }
            
            // 이전 시점 갱신
            prevTime = curTime.time;
        }
        
        return answer / jobs.length;
    }
    
    // 작업 요청, 작업 완료 시점을 저장
    class JobTime implements Comparable<JobTime> {
        int time;               // 시점
        int processingTime;     // 작업 소요 시간
        boolean isRequestTime;  // true면 작업 요청 시점, false면 작업 완료 시점
        
        public JobTime(int time, int processingTime, boolean isRequestTime) {
            this.time = time;
            this.processingTime = processingTime;
            this.isRequestTime = isRequestTime;
        }
        
        @Override
        public int compareTo(JobTime obj) {
            // 시점이 같다면 작업 소요 시간으로 비교
            if (time == obj.time) return processingTime - obj.processingTime;
            // 기본적으로 시점으로 비교
            return time - obj.time;
        }
    }
    
    // 시간 복잡도
    // O(n * k), n = jobs.length, k = jobQueue.size() <= jobs.length
    // while문은 반드시 2 * jobs.length번 반복하고,
    // 반복중에 가장 시간을 많이 소요하는 작업은 jobQueue.remove(Object obj)로 O(jobQueue.length)임
    // 따로 변수를 하나 추가해서 O(n log k)로 할 수 있지만 현재 능력으로는 가독성이 떨어질 것 같음
}
