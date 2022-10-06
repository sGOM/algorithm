import java.util.Arrays;
import java.util.ArrayList;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        // 각 주차 정보를 String[]로 나눠 String[][]로 저장
        String[][] recordsInfo = Arrays.stream(records)
                                        .map(record -> Arrays.stream(record.split(" ")).toArray(String[]::new)) // 각 원소를 " " 으로 나누고 String[]으로 변환
                                        .toArray(String[][]::new);  // String[][]로 변환
        // 차량 번호로 정렬(오름차순)
        Arrays.sort(recordsInfo, (a, b) -> a[1].compareTo(b[1]));
        // 각 차량의 주차 요금을 저장할 ArrayList 정의 (차량 번호 순으로 저장될 것)
        ArrayList<Integer> answer = new ArrayList<>();
        
        // 입출차 정보 배열을 순회
        for (int i = 1, timeSum = 0; i <= recordsInfo.length; i++) {
            // 끝까지 순회했거나 i-1과 i을 비교하는데 차량번호가 다른 경우
            if (i == recordsInfo.length || !recordsInfo[i][1].equals(recordsInfo[i-1][1])) {
                // i-1 차량번호의 마지막 기록이 IN인 경우 (출차 기록이 없는 경우)
                if (recordsInfo[i-1][2].equals("IN")) {
                    timeSum += getParkingTime(recordsInfo[i-1][0], "23:59");
                }
                // 현재까지 합산한 주차 시간으로 주차 요금 계산하고 추가
                answer.add(calParkingFee(fees, timeSum));
                // 다음 차량의 주차 시간 계산을 위해 0으로 초기화
                timeSum = 0;
                continue;
            }
            // i-1과 i을 비교하는데 차량번호가 같고 i-1이 IN, i가 OUT인 경우
            if (recordsInfo[i][1].equals(recordsInfo[i-1][1]) && recordsInfo[i-1][2].equals("IN")) {
                // 해당 차량번호의 주차 시간을 합산
                timeSum += getParkingTime(recordsInfo[i-1][0], recordsInfo[i][0]);
                continue;
            }
        }
        
        return answer.stream()                          // ArrayList<Integer>
                        .mapToInt(Integer::intValue)    // Integer -> int
                        .toArray();                     // 배열로 반환
    }
    
    // "00:00"형태의 입차, 출차 시각을 받고 둘의 차이를 분단위로 반환
    public int getParkingTime(String timeIn, String timeOut) {
        int[]   in  = Arrays.stream(timeIn.split(":")).mapToInt(Integer::parseInt).toArray();
        int[]   out = Arrays.stream(timeOut.split(":")).mapToInt(Integer::parseInt).toArray();
               
        return (out[0] - in[0]) * 60 + (out[1] - in[1]);
    }
    
    // 요금에 대한 정보와 주차 시간(분단위)을 받고 주차 요금을 반환
    public int calParkingFee(int[] fees, int time) {
        int basicTime   = fees[0];
        int basicFee    = fees[1];
        int unitTime    = fees[2];
        int unitFee     = fees[3];
        
        if (time <= basicTime) return basicFee;
        return basicFee + (int)Math.ceil((time - basicTime)/(double)unitTime) * unitFee;
    }
}