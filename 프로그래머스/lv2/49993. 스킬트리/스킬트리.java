import java.util.*;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        // K(선행스킬명)과 V(선행스킬우선순위)를 저장하는 Map 선언
        HashMap<Character, Integer> essentialSkillMap = new HashMap<>(skill.length() + 1);
        
        // 그냥 잠시 쓸 skills가 남아있는게 보기 싫어서 괄호로 묶음
        {
            char skills[] = skill.toCharArray();
            // 순회하며 선행 스킬 정보를 Map에 저장
            for (int i = 0; i < skill.length(); i++) {
                essentialSkillMap.put(skills[i], i);
            }
        }
        
        int answer = 0;
        
        // 스킬 트리들을 순회
        for (String skillTree : skill_trees) {
            // 현재 배운 선행 스킬의 우선 순위, 가장 낮은게 0이므로 -1로 초기화
            int essentialSkillNum = -1;
            // 스킬 트리를 순회
            for (char s : skillTree.toCharArray()) {
                // 현재 조회 중인 s(스킬)이 선행 스킬 정보 Map에 존재하면
                Integer skillNum = essentialSkillMap.getOrDefault(s, null);
                if (skillNum != null) {
                    // 선행 스킬을 배웠는지 검사
                    // 선행 스킬을 배우지 않았다면 정답처리를 하지 않고 반복문을 빠져나감
                    if ((skillNum - 1) != essentialSkillNum) {
                        answer--;
                        break;
                    }
                    // 선행 스킬을 배웠다면 현재 배운 선행 스킬의 우선 순위 갱신
                    essentialSkillNum = skillNum;
                }
            }
            answer++;
        }
        return answer;
    }
}


// 굉장히 잘한 다른 사람의 풀이, 그런데 내 풀이보다 60배 정도 느리다
// ArrayList.remove의 시간 복잡도가 O(n)이기 때문에 그렇다고 생각해 LinkedList로 바꿔보았으나 그대로인 것을 보니
// 정규표현식(replaceAll)에서 시간을 많이 먹는듯 하다, replaceAll의 일반적인 시간복잡도는 O(n), n은 입력 문자열의 길이이다

// 하지만 이 모든 것을 감안하더라도 이해하기 쉽고, 간결하기(유지-보수성이 높기) 때문에 가치있는 코드이다.
/*
import java.util.*;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        //ArrayList<String> skillTrees = new ArrayList<String>(Arrays.asList(skill_trees));
        LinkedList<String> skillTrees = new LinkedList<String>(Arrays.asList(skill_trees));
        Iterator<String> it = skillTrees.iterator();

        // 스킬 트리들을 순회
        while (it.hasNext()) {
            // 현재 조회한 스킬 트리에서 선행 스킬이 존재하는 스킬들만 남김
            // 이때 남겨진 스킬들의 순서가 올바르다면, 매개변수로 주어진 skill의 앞부분과 동일해야함(startWith)
            // 그러므로 반환 값(인덱스)가 0이 아니면 잘못된 스킬 트리이므로 ArrayList에서 제거함
            if (skill.indexOf(it.next().replaceAll("[^" + skill + "]", "")) != 0) {
                it.remove();
            }
        }
        
        // 잘못된 스킬트리는 제거되었으므로 사이즈를 리턴하면 정답
        return skillTrees.size();
    }
}
*/
