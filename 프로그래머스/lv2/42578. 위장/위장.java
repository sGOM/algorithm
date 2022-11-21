import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

class Solution {
    public int solution(String[][] clothes) {
        return Arrays.stream(clothes)
                // clothes[0]~clothes[clothes.length-1] 각각 역시 배열이므로 각 배열에서 원하는 값인 1번째 값만 추출
                .map(cloth -> cloth[1])
                // groupingBy 라는 놀라운 기능으로 key 는 위에서 추출한 값 그대로, value 는 Collectors 에서 지원하는 counting 메서드를 사용
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                // 위에서 반환한 Map 을 value 값으로만 이뤄진 Collection 으로 변환하고 해당 Collection 의 stream 을 생성
                .values().stream()
                // Collectors.counting() 의 반환 값이 Long 이 었으므로 윗줄에서 생성된 stream 이 Long 타입임, Integer 로 변환
                .map(Long::intValue)
                // 초기값을 1로 설정하고 (각 요소 + 1)을 이전 결과 값에 곱하고 반환한후에
                // 반환값에 최종적으로 -1 을 하고 return
                .reduce(1, (a, b) -> a * (b + 1)) - 1;
    }
}