import java.util.Arrays;
import java.util.LinkedHashSet;

// 원소가 n개인 tuple의 n개 원소를 가진 집합을 s(n)
//                    1개 원소를 가진 집합을 s(1) 이라고 하면
// tuple은 s(k)에서 s(k-1)에는 없었던 원소를 순서대로 추가하는 것으로 만들 수 있음 (2 <= k <= n)
class Solution {
    public int[] solution(String s) {
        // 앞에 "{{"와 "}}"를 제거
        s = s.substring(2, s.length()-2);
        // 남은 문자열을 "},{"로 split 해서 각 s를 다음과 같이 문자열로 만들고
        // 아래의 배열에 저장함 s(1) = "a1", s(2) = "a1,a2", s(3) = "a1,a2,a3"...
        String[] tupleSet = s.split("\\}\\,\\{");
        // s의 원소 개수 순으로 정렬
        Arrays.sort(tupleSet, (a, b) -> a.length() - b.length());

        // 순서있고 중첩없는 tuple을 만들기위한 LinkedHashSet 생성
        LinkedHashSet<Integer> tuple = new LinkedHashSet<>();

        // s(1) ~ s(n)을 순서대로 가져오면서 int[]로 변환
        for (String subtuple : tupleSet) {
            int[] subtupleInt = Arrays.stream(subtuple.split("\\,"))
                                        .mapToInt(Integer::parseInt)
                                        .toArray();
            // 크기가 작은 s부터 원소들을 추가
            // tuple -> (s(1), s(2)-s(1), ..., s(n)-s(n-1))
            for (int el : subtupleInt) {
                tuple.add(el);
            }
        }

        // LinkedHashSet<Integer> -> Integer[] -> int[]
        return Arrays.stream(tuple.toArray(new Integer[tuple.size()]))
                                    .mapToInt(i -> i)
                                    .toArray();
    }
}