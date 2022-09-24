import java.util.Arrays;

class Solution {
    public int solution(int[] arr) {
        Arrays.sort(arr);
        boolean isCommon    = true;
        int     answer      = arr[arr.length - 1];
        
        for (int i = arr.length - 1; 0 <= i; i--) {
            int GCD     = arr[i];
            int temp1   = answer;
            int temp2   = 0;
            while (temp1 % GCD != 0) {
                temp2   = temp1 % GCD;
                temp1   = GCD;
                GCD     = temp2;
            }
            answer = answer * (arr[i] / GCD);
        } 
        
        return answer;
    }
}