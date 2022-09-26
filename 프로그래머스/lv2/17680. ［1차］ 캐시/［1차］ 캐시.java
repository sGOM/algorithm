import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        // LinkedHashMap(initialCapacity, loadFactor, accessOrder)
        // loadFactor -> Map이 전체에 몇 % 찼을 때 capacity를 늘릴까, 이번 문제와는 관계 X
        // accessOrder -> true : accessOrder    -> 이미 있는 key라도 순서 최신화
        //                false : insertOrder   -> 이미 있는 key면 순서 변경 X
        LinkedHashMap<String, Boolean> cache = new LinkedHashMap(cacheSize, 1.0f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                // LinkedHashMap을 상속 받는 클래스를 정의해서 capacity에 따라 변경되게 하는게 좋음
                // 여기에 cacheSize는 할당 이후 변경 불가능하기에 좋지 않음
                return size() > cacheSize;
            }
        };
        
        int answer = 0;
        for (String city : cities) {
            city = city.toLowerCase();
            // cache hit
            if (cache.containsKey(city))    answer++;
            // cache miss
            else                            answer += 5;
            cache.put(city, false);
        }
        
        return answer;
    }
}
