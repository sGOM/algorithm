import java.util.*;

class Solution {
    HashMap<String, LinkedList<String>> flightMap;
    Stack<String> answer;
    int travelLength;
    
    public String[] solution(String[][] tickets) {
        init(tickets);
        travelLength = tickets.length + 1;
        
        searchTravelCourse();
        
        return answer.toArray(String[]::new);
    }
    
    public void init(String[][] tickets) {
        flightMap = new HashMap<>();
        for (String[] ticket : tickets) {
            String departure    = ticket[0];
            String destination  = ticket[1];
            flightMap.computeIfAbsent(departure, (k) -> new LinkedList<>()).add(destination);
        }
        
        answer = new Stack<>();
        answer.push("ICN");
    }
    
    public void searchTravelCourse() {
        LinkedList<String> destinations = flightMap.get(answer.peek());
        if (destinations == null) return;
        
        for (String d : destinations.stream().sorted().toArray(String[]::new)) {
            destinations.remove(d);
            answer.push(d);
            searchTravelCourse();
            if (travelLength == answer.size()) return;
            destinations.add(d);
        } 
    }
}