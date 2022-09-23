import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        
        Arrays.sort(people);
        int leftIdx = 0;
        int rightIdx = people.length - 1;
        
        int cnt = 0;
        while (leftIdx < rightIdx) {
            if (people[leftIdx] <= (limit - people[rightIdx--])) leftIdx++;
            cnt++;
        }
        if (rightIdx == leftIdx) cnt++;
        
        return cnt;
    }
}