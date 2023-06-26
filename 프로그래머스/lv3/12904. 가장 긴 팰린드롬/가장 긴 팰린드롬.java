class Solution {
    // 긴 문자열을 먼저 확인하고, palindrome을 발견하면 즉시 종료
    // 중심을 잡고 확인하는 코드보다 반복은 더 많이 하지만 전체적인 연산횟수는 비슷하거나 더 적을 수 있다고 생각함, 이후 추가
    public int solution(String s) {
        // O(n^3), n = s.length()
        for (int len = s.length(); 0 < len; len--) {
            for (int left = 0, right = len - 1; right < s.length(); left++, right++) {
                if (isPalindrome(s, left, right)) {
                    return len;
                }
            }
        }

        return 0;
    }
    
    // O(n), n = (rightIndex - leftIndex)
    public boolean isPalindrome(String target, int leftIndex, int rightIndex) {
        while (leftIndex < rightIndex) {
            if (target.charAt(leftIndex++) != target.charAt(rightIndex--)) {
                return false;
            }
        }
        
        return true;
    }
    
    // 시간 복잡도
    // return을 사용한 백트래킹이 없는 경우 아래와 같음
    // O(n^3), n = s.length()
    // 도출법 -> Σ(1 <= L <= n){ (n - L + 1) * L }
    // (안쪽 for문) * (isPalindrome)
    // 이는 거듭 제곱합 공식에 따라 최고 차항이 n^3이 됨
    
    // 백트랙킹을 사용한 경우는 따로 계산해봐야하는데 너무 복잡...
}

/* 다른 사람 코드, 시간복잡도 O(n^2), n = s.length()
public int solution(String s) {
    int left = 0, right = 0;
    int result = 1;
    if(s.length() != 1) return 1;
    
    // palindrome의 길이가 홀수일 때
    for (int center = 1; center < s.length() - 1; center++) {
        left = center - 1;
        right = center + 1;
        while (left >= 0 && right <= s.length() - 1) {
            if (s.charAt(left) != s.charAt(right))
                break;

            result = right - left + 1 > result ? right - left + 1 : result;
            left--;
            right++;
        }
    }

    // palindrome의 길이가 짝수일 때
    for (int center = 0; center <= s.length() - 2; center++) {
        left = center;
        right = center + 1;
        while (left >= 0 && right <= s.length() - 1) {
            if (s.charAt(left) != s.charAt(right))
                break;
            result = right - left + 1 > result ? right - left + 1 : result;
            left--;
            right++;
        }
    }
    

    return result;
}
*/
