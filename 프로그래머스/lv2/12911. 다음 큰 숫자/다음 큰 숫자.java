import java.util.Arrays;

class Solution {
    public int solution(int n) {
        StringBuilder   answer = new StringBuilder();
        String          str = Integer.toBinaryString(n);
        boolean         flag = false;
        int             oneCnt = 0, zeroCnt = 0;
        
        answer.append(str);
        
        for (int i = answer.length() - 1; 0 <= i; i--) {
            if (answer.charAt(i) == '0' && !flag) { zeroCnt++; continue; }
            if (answer.charAt(i) == '1') {
                if (i == 0) {
                    answer.insert(1, "0");
                    int len = answer.length();
                    answer.delete(2, len);
                    answer.append("0".repeat(zeroCnt));
                    answer.append("1".repeat(oneCnt));
                    break;
                }
                oneCnt++;
                flag = true; continue;
            }
            if (answer.charAt(i) == '0') {
                int len = answer.length();
                answer.delete(i, len);
                answer.append("1"); oneCnt--; zeroCnt++;
                answer.append("0".repeat(zeroCnt));
                answer.append("1".repeat(oneCnt));
                break;
            }
        }
        
        return Integer.parseInt(answer.toString(), 2);
    }
}