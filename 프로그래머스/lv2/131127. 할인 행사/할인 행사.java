import java.util.*;
import java.util.stream.*;
import java.util.function.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        // want 배열에 "상품명"과 "인덱스"를 엮는 사전 생성
        HashMap<String, Integer> indexMap = 
            IntStream.range(0, want.length)
                .boxed()
                .collect(Collectors.toMap(i -> want[i], Function.identity(), (k, v) -> k, HashMap::new)); // 참고 링크 : https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/stream/Collectors.html#toMap(java.util.function.Function,java.util.function.Function,java.util.function.BinaryOperator,java.util.function.Supplier)
        
        int answer = 0;
        int onSaleWant = 0; // 원하는 만큼 모두 구매한 세일 상품 갯수 (0 <= onSaleWant <= want.length)
        int totalItemCnt = Arrays.stream(number).sum(); // 10으로 고정되어 있지만, 하드 코딩보다는 이 편이 낫다고 판단
        // 초기 totalItemCnt 개 만큼 초기화
        for (int i = 0; i < totalItemCnt; i++) {
            Integer wantIndex = indexMap.get(discount[i]);
            if (wantIndex != null && --number[wantIndex] == 0) onSaleWant++;
        }
        if (onSaleWant == want.length) answer++;
        
        // 오늘의 할인 제품, 어제의 할인 제품에 대한 검사를 진행하고, 원하는 것을 모두 구매했다면(onSaleWant==want.length) answer에 1추가
        for (int presentIndex = totalItemCnt, pastIndex = 0; presentIndex < discount.length; presentIndex++, pastIndex++) {
            Integer newWantIndex = indexMap.get(discount[presentIndex]);
            Integer oldWantIndex = indexMap.get(discount[pastIndex]);
            if (newWantIndex != null && --number[newWantIndex] == 0) onSaleWant++;  // 새로 추가된 할인 상품이 리스트에 존재하면 구매
            if (oldWantIndex != null && ++number[oldWantIndex] == 1) onSaleWant--;  // 어제까지 할인이었던 상품은 제외
            if (onSaleWant == want.length) answer++;    // 원하는 것을 모두 구매한 경우
        }
        
        return answer;
    }
}