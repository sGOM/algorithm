import java.util.*;

class Solution {
    public double[] solution(int k, int[][] ranges) {
        // 구간 별 정적분 수열의 누적 합 저장 -> 부분 수열의 합을 구하기 쉬움
        ArrayList<Double> prefixSumDp = memoDefIntegralPrefixSum(k);
        
        double[] answer = new double[ranges.length];
        
        // 구간 별 정적분 결과 기입
        for (int idx = 0; idx < ranges.length; idx++) {
            int a = ranges[idx][0];
            int b = (ranges[idx][1] < 1) ? prefixSumDp.size() - 1 + ranges[idx][1] : ranges[idx][1];
            
            answer[idx] = (b < a) ? -1.0 : prefixSumDp.get(b) - prefixSumDp.get(a);
        }
        
        return answer;
    }
    
    public ArrayList<Double> memoDefIntegralPrefixSum(int k) {
        // DP 배열
        ArrayList<Double> prefixSumDp = new ArrayList<>();
        // 점의 갯수는 구간 보다 1개 많으므로 추가
        prefixSumDp.add(0.0);
        
        int curN = k;
        int nextN;
        
        double prefixSum = 0.0;
        
        while (curN != 1) {
            nextN = (curN % 2 == 0) ? curN / 2 : curN * 3 + 1;
            
            prefixSum += (curN + nextN) / 2.0;
            prefixSumDp.add(prefixSum);
            
            curN = nextN;
        }
        
        return prefixSumDp;
    }
}

/*
* 시간 복잡도
* k_seq = 초항이 k인 우박수열의 길이
* O(k_seq + ranges.length)
*/
