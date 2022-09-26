import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        LinkedHashMap<String, Boolean> cache = new LinkedHashMap(cacheSize) {
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
            if (cache.containsKey(city))    { answer++; cache.remove(city); }
            // cache miss
            else                            answer += 5;
            cache.put(city, false);
        }
        
        return answer;
    }
}