import java.util.*;

class Solution {
    HashMap<String, Integer> airportNumbering;
    Stack<String> answer;
    String[][]  flightGraph;
    boolean[][] isVisited;
    
    int travelLength;
    
    public String[] solution(String[][] tickets) {
        init(tickets);
        travelLength = tickets.length + 1;
        
        searchTravelCourse();
        
        return answer.toArray(String[]::new);
    }
    
    public void init(String[][] tickets) {
        HashMap<String, ArrayList<String>> flightMap = new HashMap<>();
        for (String[] ticket : tickets) {
            String departure    = ticket[0];
            String destination  = ticket[1];
            flightMap.computeIfAbsent(departure, (k) -> new ArrayList<>()).add(destination);
        }
        
        airportNumbering = new HashMap<>();
        flightGraph = new String[flightMap.size()][];
        isVisited   = new boolean[flightMap.size()][];
        int index = 0;
        for (Map.Entry<String, ArrayList<String>> ent : flightMap.entrySet()) {
            String              departure     = ent.getKey();
            ArrayList<String>   destinations  = ent.getValue();
            
            airportNumbering.put(departure, index);
            flightGraph[index]  = destinations.stream().sorted().toArray(String[]::new);
            isVisited[index]    = new boolean[flightGraph[index].length];
            index++;
        }
        
        answer = new Stack<>();
        answer.push("ICN");
    }
    
    public void searchTravelCourse() {
        if (airportNumbering.get(answer.peek()) == null) return;
        int curAirportNum = airportNumbering.get(answer.peek());
        
        for (int i = 0; i < flightGraph[curAirportNum].length; i++) {
            String destination = flightGraph[curAirportNum][i];
            
            if (!isVisited[curAirportNum][i]) {
                isVisited[curAirportNum][i] = true;
                answer.push(destination);
                
                searchTravelCourse();
                if (travelLength == answer.size()) return;

                isVisited[curAirportNum][i] = false;
                answer.pop();
            }
        } 
    }
}