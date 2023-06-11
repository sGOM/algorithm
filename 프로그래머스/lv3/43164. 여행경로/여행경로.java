import java.util.*;

class Solution {
    HashMap<String, Integer> airportNumbering;  // 모든 공항을 넘버링하여 저장
    Stack<String> answer;                       // 여행 경로(정답)를 저장
    
    String[][]  flightGraph;                    // No.i 공항에서 flightGraph[i][j]공항으로 가는 티켓이 있다는 것
    boolean[][] isVisited;                      // No.i 공항에서 flightGraph[i][j]공항으로 가는 티켓을 사용했다는것
    
    int travelLength;                           // 정답이 되는 answer의 크기
    
    public String[] solution(String[][] tickets) {
        // 초기화
        init(tickets);
        travelLength = tickets.length + 1;
        
        // 여행 경로 탐색 (DFS, Backtracking)
        searchTravelCourse();
        
        return answer.toArray(String[]::new);
    }
    
    // 맴버변수 초기화 함수
    public void init(String[][] tickets) {
        // Key : 출발 공항, Value : 도착 공항들을 저장한 List
        HashMap<String, ArrayList<String>> flightMap = new HashMap<>();
        for (String[] ticket : tickets) {
            String departure    = ticket[0];
            String destination  = ticket[1];
            flightMap.computeIfAbsent(departure, (k) -> new ArrayList<>()).add(destination);
        }
        
        airportNumbering = new HashMap<>();
        flightGraph = new String[flightMap.size()][];
        isVisited   = new boolean[flightMap.size()][];
        
        // 위에서 작성한 flightMap을 순회
        int index = 0;
        for (Map.Entry<String, ArrayList<String>> ent : flightMap.entrySet()) {
            String              departure     = ent.getKey();
            ArrayList<String>   destinations  = ent.getValue();
            
            // 출발 공항을 0부터 넘버링
            airportNumbering.put(departure, index);
            // index 번 공항에서 출발해서 도착할 수 있는 공항들을 알파벳 순으로 정렬하여 추가
            flightGraph[index]  = destinations.stream().sorted().toArray(String[]::new);
            isVisited[index]    = new boolean[flightGraph[index].length];
            index++;
        }
        
        // 여행 경로(정답), ICN(인천)에서 시작 
        answer = new Stack<>();
        answer.push("ICN");
    }
    
    // 여행 경로 탐색, DFS
    public void searchTravelCourse() {
        // 현재 여행경로 끝에 추가된 공항에서 출발하는 티켓이 없는 경우
        if (airportNumbering.get(answer.peek()) == null) return;
        
        // 현재 방문한 공항의 넘버링을 얻음
        int curAirportNum = airportNumbering.get(answer.peek());
        
        // 현재 공항에서 갈 수 있는 공항 순회
        for (int i = 0; i < flightGraph[curAirportNum].length; i++) {
            String destination = flightGraph[curAirportNum][i];
            
            // 티켓을 아직 사용하지 않았다면
            if (!isVisited[curAirportNum][i]) {
                isVisited[curAirportNum][i] = true;
                answer.push(destination);
                
                // 다음 공항으로
                searchTravelCourse();
                // 답을 찾았다면
                if (travelLength == answer.size()) return;

                isVisited[curAirportNum][i] = false;
                answer.pop();
            }
        } 
    }
    // 시간 복잡도
    // 굉장히 구하기 어려움, 가장 많은 시간을 먹는 작업은 void searchTravelCourse()
    // 각 층마다 재귀적으로 자신을 불러내는 횟수에 따라 시간 복잡도가 변경됨
    // 그러나 불러내는 횟수는 (어떤 공항에서 사용할 수 있는 아직 사용하지 않은 티켓 수) 이므로 알기 힘들고,
    // 정답을 찾으면 모든 탐색이 중지되기 때문
}
