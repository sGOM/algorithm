import java.util.Arrays;

class Solution {
    public int solution(int[] arr) {
        boolean isCommon    = true;
        int     answer      = Arrays.stream(arr).max().getAsInt();
        
        for (int el : arr) {
            int GCD     = el;
            int temp1   = answer;
            int temp2   = 0;
            while (temp1 % GCD != 0) {
                temp2   = temp1 % GCD;
                temp1   = GCD;
                GCD     = temp2;
            }
            answer = answer * (el / GCD);
        } 
        
        return answer;
    }
}