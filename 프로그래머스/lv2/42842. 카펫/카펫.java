class Solution {
    public int[] solution(int brown, int yellow) {
        int qf_b = (brown + 4) / 2;
        int qf_c = (brown + yellow);
        int width = (qf_b + (int)Math.sqrt(qf_b*qf_b - 4 * qf_c)) / 2;
        int height = (brown + yellow) / width;
        
        
        return new int[] { width, height };
    }
}