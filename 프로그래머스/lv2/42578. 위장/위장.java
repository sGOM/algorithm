import java.util.HashMap;

class Solution {
    public int solution(String[][] clothes) {
        // 옷의 종류 별로 카운팅할 HashMap 선언
        HashMap<String, Integer> kindClothes = new HashMap<>();
        // 주어진 clothes를 순회하면서 옷 종류 카운팅
        for (String[] c : clothes) {
            // 이미 있는 옷 종류면 기존에 + 1
            if (kindClothes.containsKey(c[1]))  kindClothes.put(c[1], kindClothes.get(c[1]) + 1);
            // 새로운 옷 종류면 value를 1로 하고 추가
            else                                kindClothes.put(c[1], 1);
        }
        
        int answer = 1;
        // (이전 경우의 수) *= (해당 종류의 수 + 해당 종류를 안입는 경우(==1))
        for (Integer num : kindClothes.values()) 
            answer *= (num + 1);

        // 모든 종류의 옷을 안 입는 경우는 빼줌
        answer--;
        
        return answer;
    }
}