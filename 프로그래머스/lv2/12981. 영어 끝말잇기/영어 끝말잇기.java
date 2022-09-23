import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        
        HashSet<String> set = new HashSet<String>(words.length);
        String startWith = words[0].substring(words[0].length() - 1, words[0].length());
        set.add(words[0]);

        for (int i = 1; i < words.length; i++) {
            if (set.contains(words[i]) || !words[i].startsWith(startWith)) {
                int num     = i % n + 1;
                int turn    = i / n + 1;
                return new int[] { num, turn };
            }
            startWith = words[i].substring(words[i].length() - 1, words[i].length());
            set.add(words[i]);
        }

        return new int[] { 0, 0 };
    }
}