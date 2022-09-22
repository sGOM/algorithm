class Solution {
    boolean solution(String s) {
        
        int bracketCnt = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') bracketCnt++;
            else {
                if (bracketCnt == 0) return false;
                bracketCnt--;
            }
        }

        return bracketCnt == 0;
    }
}