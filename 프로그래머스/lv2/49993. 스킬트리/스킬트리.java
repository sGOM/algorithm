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