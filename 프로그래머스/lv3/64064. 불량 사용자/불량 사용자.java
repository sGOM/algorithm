import java.util.*;

class Solution {
    // 제재 아이디를 제외한 모든 경우의 수
    HashSet<String> userSet = new HashSet<>();
    public int solution(String[] userId, String[] bannedId) {
        // 정규표현식을 사용할 수 있도록 '*' -> '.'로 변경
        // 시간 복잡도 : O(bannedId.length * bannedId[i].length)
        for (int i = 0; i < bannedId.length; i++) {
            // 시간 복잡도 : O(bannedId[i].length)
            bannedId[i] = bannedId[i].replace('*', '.');
        }
        
        // 모든 경우의 수 탐색 (userId 길이가 그렇게 크지 않아 충분히 가능)
        dfs(userId, bannedId, new LinkedList<>(Arrays.asList(userId)), 0);
        
        // 중복을 제외한 경우의 수
        return userSet.size();
    }
    
    // bannedId[bannedIdx]에 해당하는 아이디를 userList로 부터 삭제하고, 모두 삭제한 후에는 해당 경우의 수를 userSet에 추가
    public void dfs(String[] userId, String[] bannedId, List<String> userList, int bannedIdx) {
        // 모두 삭제한 경우
        if (bannedIdx == bannedId.length) {
            userSet.add(userList.toString());
            return;
        }
        
        // userList를 순회하며 삭제할 아이디 탐색
        for (String user : userList) {
            // 삭제할 아이디를 찾았다면 해당 아이디를 삭제한 List를 다음 dfs에 넘김
            // 시간 복잡도 : O(user.length + userList.size())
            if (user.matches(bannedId[bannedIdx])) {
                List<String> nextList = clone(userList);
                nextList.remove(user);
                dfs(userId, bannedId, nextList, bannedIdx + 1);
            }
        }
    }
    
    // 깊은 복사
    // 시간 복잡도 : O(source.length)
    public List<String> clone(List<String> source) {
        List<String> newList = new LinkedList<>();
        for (String str : source) {
            newList.add(str);
        }
        return newList;
    }
}
