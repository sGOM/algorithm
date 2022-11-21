import java.util.Arrays;
import static java.util.stream.Collectors.*;

class Solution {
    public int solution(String[][] clothes) {
        return Arrays.stream(clothes)
                    // 각 의상의 종류 별로 counting 해 Map으로 반환
                    .collect(groupingBy(cloth -> cloth[1], counting()))
                    // Map 의 value 들만으로 이뤄진 Collection 으로 변환
                    .values()
                    .stream()
                    // 초기값을 1로 설정하고 (각 요소 + 1)을 이전 결과 값에 곱하고 반환한후에 -1
                    .reduce(1L, (x, y) -> x * (y + 1)).intValue() - 1;
    }
}