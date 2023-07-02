import java.util.*;

class Solution {
    public int solution(int[][] data, int col, int rowBegin, int rowEnd) {
        // O(n log n), n = data.length
        Arrays.sort(data, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[col - 1] == b[col - 1]) return b[0] - a[0];
                return a[col - 1] - b[col - 1];
            }
        });
        
        int answer = 0;
        // O(h * w), h = (rowEnd - rowBegin), w = (data의 원소의 길이 == data[0].length)
        // rowBegin부터 rowEnd까지 순회
        for (int row = rowBegin; row <= rowEnd; row++) {
            int modSum = 0;
            // row행의 data들을 row로 나눈 나머지 값들의 합
            for (int d : data[row - 1]) {
                modSum += d % row;
            }
            // XOR
            answer = answer ^ modSum;
        }
        
        return answer;
    }
    
    // 시간 복잡도
    // O(h * w + n log n), n = data.length, h = (rowEnd - rowBegin), w = (data의 원소의 길이 == data[0].length)
}
