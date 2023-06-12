import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        PriorityQueue<Integer> jobQueue = new PriorityQueue<>();
        PriorityQueue<JobTime> timeQueue = new PriorityQueue<>();
        
        for (int[] job : jobs) {
            timeQueue.add(new JobTime(job[0], job[1], true));
        }
        
        int answer = 0;
        
        int prevTime = 0;
        while (!timeQueue.isEmpty()) {
            JobTime curTime = timeQueue.poll();
            
            answer += (curTime.time - prevTime) * jobQueue.size();
            // 작업이 요청되는 시점인 경우
            if (curTime.isRequestTime) {
                // 대기중인 작업이 없다면
                if (jobQueue.isEmpty()) {
                    timeQueue.add(new JobTime(curTime.time + curTime.processingTime, curTime.processingTime, false));
                }
                jobQueue.add(curTime.processingTime);
                
            // 작업이 완료되는 시점인 경우
            } else {
                jobQueue.remove(curTime.processingTime);
                
                // 대기 중인 작업이 있다면
                if (!jobQueue.isEmpty()) {
                    int processingTime = jobQueue.peek();
                    timeQueue.add(new JobTime(curTime.time + processingTime, processingTime, false));
                }
            }
            prevTime = curTime.time;
        }
        
        return (int) (answer / jobs.length);
    }
    
    class JobTime implements Comparable<JobTime> {
        int time;
        int processingTime;
        boolean isRequestTime;
        
        public JobTime(int time, int processingTime, boolean isRequestTime) {
            this.time = time;
            this.processingTime = processingTime;
            this.isRequestTime = isRequestTime;
        }
        
        @Override
        public int compareTo(JobTime obj) {
            if (time == obj.time) return processingTime - obj.processingTime;
            return time - obj.time;
        }
    }
}