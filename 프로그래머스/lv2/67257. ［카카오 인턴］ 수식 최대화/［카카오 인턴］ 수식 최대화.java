import java.util.*;

class Solution {
    // expression에 등장하는 연산자 종류를 저장할 배열
    Character[] ops;
    // ops와 1대1 대응되어, 해당 연산자를 이미 연산했는지를 확인
    boolean[] isVisited;
    
    public long solution(String expression) throws Exception {
        ArrayList<Long> operands = new ArrayList<>();       // 피연산자
        ArrayList<Character> operators = new ArrayList<>(); // 연산자
        
        // 초기값 설정
        init(expression, operands, operators);
        
        return calcPriorityDfs(operands, operators);
    }
    
    // O(n), n = expression.length()
    // 초기값 설정
    public void init(String expression, ArrayList<Long> operands, ArrayList<Character> operators) {
        // 연산자 종류 저장
        HashSet<Character> opSet = new HashSet<>();
        
        // expression을 순회
        for (int i = 0, lastOp = -1; i < expression.length(); i++) {
            // 피연산자가 아닌 경우 (연산자인 경우)
            if (!(48 <= expression.charAt(i) && expression.charAt(i) <= 57)) {
                // 피연산자, 연산자를 각각의 Collection에 추가
                operands.add(Long.valueOf(expression.substring(lastOp + 1, i)));
                operators.add(expression.charAt(i));
                // 연산자 종류 파악
                opSet.add(expression.charAt(i));
                // 이전 연산자 위치 갱신
                lastOp = i;
            }
            
            // 마지막 피연산자 추가
            if (i == expression.length() - 1) {
                operands.add(Long.valueOf(expression.substring(lastOp + 1, expression.length())));
            }
        }
        
        // 맴버 변수 초기화
        ops = opSet.toArray(new Character[opSet.size()]);
        isVisited = new boolean[ops.length];
    }
    
    // O(n! * m), n = ops.length, m = operators.size()
    // DFS를 이용해 모든 우선순위 경우의 수로 계산해보고, 최댓값을 반환
    public long calcPriorityDfs(ArrayList<Long> operands, ArrayList<Character> operators) throws Exception {
        // 피연산자가 1개 뿐이면 그 값을 반환
        if (operands.size() == 1) return operands.get(0);
        
        long answer = 0L;
        // ops를 순회
        for (int i = 0; i < ops.length; i++) {
            // 아직 연산을 시행하지 않은 ops라면
            if (!isVisited[i]) {
                isVisited[i] = true;
                
                // ops[i]를 연산한 이후의 피연산자와 연산자 Collection
                ArrayList<Long> newOperands = new ArrayList<>();
                ArrayList<Character> newOperators = new ArrayList<>();
                
                // ops[i] 연산
                newOperands.add(operands.get(0));
                for (int j = 0; j < operators.size(); j++) {
                    if (operators.get(j) == ops[i]) {
                        long operand1 = newOperands.remove(newOperands.size() - 1);
                        long operand2 = operands.get(j + 1);
                        newOperands.add(calc(operand1, operators.get(j), operand2));
                    } else {
                        newOperands.add(operands.get(j + 1));
                        newOperators.add(operators.get(j));
                    }
                }
                
                // 결과 값들 중, 최대값
                answer = Math.max(Math.abs(calcPriorityDfs(newOperands, newOperators)), answer);
                
                isVisited[i] = false;
            }
        }
        
        // 최대값 반환
        return answer;
    }
    
    // 연산자에 따른 계산
    public long calc(long operand1, char operator, long operand2) throws Exception {
        switch (operator) {
            case '+': return operand1 + operand2;
            case '-': return operand1 - operand2;
            case '*': return operand1 * operand2;
        }
        
        throw new Exception(String.format("Unexpected operator : %c", operator)); 
    }
    
    // 전체 시간 복잡도
    // O(n! * m), n = ops.length, m = operators.size()
    // '-'와 '+'의 우선순위가 1단계 밖에 차이가 안나는 경우
    // 둘에 우선순위가 의미없기에 해당 경우를 제외할 수 있지만
    // 그런 경우 코드가 난해해짐
}
