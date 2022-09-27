import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        Solution sol = new Solution();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = bf.readLine();

        bw.write(sol.solution(str)+"\n");
        bw.flush();
        bw.close();
    }
}

class Solution {
    public char solution(String str) {
        final int ALP_NUM     = 26;
        int[]     countArr    = new int[ALP_NUM];
        
        str = str.toUpperCase();

        for (char c : str.toCharArray()) 
            countArr[c - 'A']++;

        int     maxIndex  = 0;
        char    answer    = 'A';
        for (int i = 0; i < ALP_NUM; i++) {
            if (countArr[i] > countArr[maxIndex]) {
                maxIndex  = i;
                answer    = (char)('A' + maxIndex);
            }
            else if (i != 0 && countArr[i] == countArr[maxIndex])
                answer = '?';
        }
        
        return answer;
    }
}