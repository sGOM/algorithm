import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        
        HashSet<String> set = new HashSet<String>(words.length);
        String startWith = words[0].substring(0, 1);

        for (int i = 0; i < words.length; i++) {
            if (set.contains(words[i]) || !words[i].startsWith(startWith)) {
                int num     = (i + 1) % n;
                int turn    = ((i + 1) / n) + 1;
                if ((i + 1) % n == 0) { num = n; turn--; }
                return new int[] { num, turn };
            }
            set.add(words[i]);
            startWith = words[i].substring(words[i].length() - 1, words[i].length());
        }

        return new int[] { 0, 0 };
    }
}