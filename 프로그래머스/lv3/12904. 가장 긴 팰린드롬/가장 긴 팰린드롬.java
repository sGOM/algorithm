class Solution
{
    public int solution(String s)
    {
        // O(n^4), n = s.length()
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
    // O(n^4), n = s.length()
    // 이는 최악의 경우에만, 모든 부분 문자열이 palindrome이 아닐때
    // 도출법 -> Σ(1 <= L <= n){ L * (n - L + 1) * L }
    // (바깥쪽 for문) * (안쪽 for문) * (isPalindrome)
    // 이는 거듭 제곱합 공식에 따라 최고 차항이 n^4이 됨
}
