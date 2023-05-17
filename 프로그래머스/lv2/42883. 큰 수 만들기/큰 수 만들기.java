class Solution {
    public String solution(String number, int k) {
        StringBuilder answer = new StringBuilder(number);
        
        // 로직 : 뒷 수 보다 작은 앞 수 제거
        int index = 0;
        while (0 < k) {
            // 제거하다가, index가 -로 가면 다시 0으로 초기화
            if (index < 0) index = 0;
            // 모든 수가 내림차 순인데, 아직 덜 제거되었다면 뒤에서 부터 제거
            if (answer.length() - 1 == index) { 
                answer.delete(answer.length() - k, answer.length());
                break;
            }
            // 뒷 수 보다 작은 앞 수 제거
            if (answer.charAt(index) < answer.charAt(index + 1)) {
                answer.deleteCharAt(index);
                // answer[index - 1]과 비교하던 answer[index]가 삭제되었으니, answer[index - 1]을 다시 체크
                index--;
                k--;
            // 위 제거 조건에 부합하지 않으면, 다음을 체크
            } else {
                index++;
            }
        }
        
        return answer.toString();
    }
    // 시간 복잡도
    // O(number.length() + k)
    // 최악의 상황이라도, 모든 숫자를 끝까지 체크하고 k만큼 추가로 체크함
}