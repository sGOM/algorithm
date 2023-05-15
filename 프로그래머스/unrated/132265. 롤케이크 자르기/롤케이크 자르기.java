import java.util.*;

class Solution {
    public int solution(int[] toppings) {
        // 자른 2개의 덩어리에 토핑의 갯수를 저장할 Collection 정의
        HashMap<Integer, Integer>   rightSide  = new HashMap<>();
        HashSet<Integer>            leftSide   = new HashSet<>();
        int answer = 0;
        
        // 우측 덩어리의 토핑에 대한 정보를 전부 추가
        for (int i = 0; i < toppings.length; i++) {
            rightSide.merge(toppings[i], 1, Integer::sum);
        }
        
        // 우측부터 좌측까지 순회하며
        for (int topping : toppings) {
            // 해당 토핑 1개를 우측에서 제거하고, 그 값이 0이면 HashMap에서 제거
            if (rightSide.compute(topping, (k, v) -> v - 1) == 0) {
                rightSide.remove(topping);
            }
            // 해당 종류의 토핑을 좌측에 추가
            leftSide.add(topping);
            
            // 양쪽의 토핑 갯수가 같다면 답에 +1
            if (leftSide.size() == rightSide.size()) {
                answer++;
            }
        }
        
        return answer;
    }
    
}