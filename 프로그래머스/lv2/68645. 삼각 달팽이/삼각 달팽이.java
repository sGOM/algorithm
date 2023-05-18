// 풀이 : 삼각 달팽이의 순서를 따라감, 수학적 풀이도 있겠지만 쉽게 떠오르지 않고, 코드가 더 복잡해 질 것 같았음
class Solution {
    public int[] solution(int n) {
        // 총 칸의 갯수는 1 ~ n까지의 합
        int[] answer = new int[n * (n + 1) / 2];
        // 1층 초기화
        answer[0] = 1;
        
        // 현재 층 수를 나타내는 변수
        int floor = 1;
        // 현재 위치를 나타내는 변수
        int curIdx = 0;
        // 아직 못채운 칸 갯수 (1개는 미리 채움)
        int k = answer.length - 1;
        
        // 칸을 다 채울 때 까지
        while (k != 0) {
            // 아래로 이동
            for (;curIdx + floor < answer.length && answer[curIdx + floor] == 0; k--) {
                answer[curIdx + floor] = answer[curIdx] + 1;
                curIdx += floor++;
            }
            // 오른쪽으로 이동
            for (; curIdx + 1 < answer.length && answer[curIdx + 1] == 0; k--) {
                answer[curIdx + 1] = answer[curIdx] + 1;
                curIdx += 1;
            }
            // 위로 이동
            for (; 0 < curIdx - floor && answer[curIdx - floor] == 0; k--) {
                answer[curIdx - floor] = answer[curIdx] + 1;
                curIdx -= floor--;
            }
        }
        
        return answer;
    }
    // 시간 복잡도
    // O(n^2), 그냥 칸의 갯수만큼 돈다, 엄청 Low 레벨에서의 발상아니면 이보다 빠르긴 힘들듯
}
