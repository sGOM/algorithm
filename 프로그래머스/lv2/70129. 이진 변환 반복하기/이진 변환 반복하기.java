import java.util.stream.*;

class Solution {
    public int[] solution(String s) {
        int     zeroCnt = 0;
        int     transCnt = 0;
        int     nextLen = 0;
        
        while (s.length() != 1) {
            nextLen = (int)Stream.of(s.split(""))
                            .filter(str -> str.equals("1"))
                            .count();
            zeroCnt += s.length() - nextLen;
            s = Integer.toBinaryString(nextLen);
            transCnt++;
        }
        
        return new int[] {transCnt, zeroCnt};
    }
}