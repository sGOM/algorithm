import java.util.*;
import java.util.stream.*;

class Solution {
    HashMap<Integer, Integer> courseMax = new HashMap<>();
    HashMap<String, Integer> courseCandidates = new HashMap<>();
    int courseMaxLen = 0;
    
    public String[] solution(String[] orders, int[] course) {
        
        for (int c : course) {
            courseMax.put(c, 0);
            if (courseMaxLen < c) courseMaxLen = c;
        }
        
        for (String o : orders) {
            o = Stream.of(o.split(""))
                      .sorted()
                      .collect(Collectors.joining());
            makeCombination(o, "", 0);
        }
        
        String[] answer = courseCandidates.entrySet().stream()
                            .filter(ent -> courseMax.get(ent.getKey().length()) == ent.getValue() && 2 <= ent.getValue())
                            .map(Map.Entry::getKey)
                            .sorted()
                            .toArray(String[]::new);
        
        return answer;
    }
    
    // orders에 담겨 있는 문자열이 각각 알파벳 순으로 정렬되었다고 가정하고 작성함
    public void makeCombination(String order, String comb, int idx) {
        if (courseMax.containsKey(comb.length())) {
            int count = courseCandidates.compute(comb, (k, v) -> v == null ? 1 : v + 1);
            courseMax.compute(comb.length(), (k, v) -> v < count ? count : v);
        }
        if (comb.length() == courseMaxLen) return;
        
        for (int i = idx; i < order.length(); i++) {
            makeCombination(order, comb + order.charAt(i), i + 1);
        }
    }
}