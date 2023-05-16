import java.util.*;

class Solution {
    // 조합된 수 중에 최댓 값
    int max = 0;
    // 조합에 사용될 모든 숫자 배열
    String[] numberArr;
    // dfs를 위한 방문여부 배열
    boolean[] isVisited;
    // 조합으로 생성된 숫자를 저장할 HashSet
    HashSet<Integer> numComs = new HashSet<Integer>();
    
    public int solution(String numbers) {
        // 변수 초기화
        numberArr = numbers.split("");
        isVisited = new boolean[numberArr.length];
        
        // dfs로 모든 조합 생성
        generateCombination("");
        
        // 에라토스테네스의 체를 사용, 초기화
        boolean[] eratosPrime = new boolean[max + 1];
        Arrays.fill(eratosPrime, true);
        eratosPrime[0] = false; eratosPrime[1] = false;
        numComs.remove(0);      numComs.remove(1);
        
        // n이 소수인지 확인하기 위해서는, sqrt(n)까지 체크해보면됨 -> n이 소수가 아니라면, a * b 형태이고 a, b <= sqrt(n);
        // for문 전체 시간 복잡도 O(sqrt(max) * max)
        for (int i = 2; i <= (int)Math.sqrt(max); i++) {
            // 소수라면
            if (eratosPrime[i]) {
                // max 값 까지의 i의 배수 제거
                // 시간 복잡도 O(max)
                for (int j = 2 * i; j <= max; j += i) {
                    numComs.remove(j);
                    eratosPrime[j] = false;
                }
            }
        }
        
        return numComs.size();
    }
    
    // 시간 복잡도 O(numbers.length!)
    public void generateCombination(String parentString) {
        // 모든 숫자를 사용해서 만들었다면 정지
        if (parentString.length() == numberArr.length) return;
        
        // 모든 경우의 수를 순회
        for (int i = 0; i < numberArr.length; i++) {
            // 이미 사용한 수가 아니라면
            if (!isVisited[i]) {
                isVisited[i] = true;
                
                String childString = parentString + numberArr[i];
                int childInt = Integer.parseInt(childString);
                // 새로 생생된 수를 HashSet에 추가하고, max 값인지 확인
                if (numComs.add(childInt) && max < childInt) max = childInt;
                
                generateCombination(childString);
                
                isVisited[i] = false;
            }
        }
    }
    // 전체 코드 시간 복잡도
    // (조합 구하기) + (에라토스테네스의 체)
    // O(numbers.length!) + O(max * sqrt(max))
    // 최악의 시나리오 -> 7! + 9999999 * sqrt(9999999) => 31622776898 (근사값)
}