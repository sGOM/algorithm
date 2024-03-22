import java.util.*;

class Solution {
    public int solution(String[][] relation) {
        final int COL_NUM = relation[0].length;
        final int ROW_NUM = relation.length;   
        
        Set<String> candidateSet = new HashSet<>();
        Queue<String> combQueue = new LinkedList<>();
        for (int idx = 0; idx < COL_NUM; idx++) {
            combQueue.add(Integer.toString(idx));
        }
        
        while (!combQueue.isEmpty()) {
            Queue<String> nextDepthQueue = new LinkedList<>();
        
            while (!combQueue.isEmpty()) {
                Set<String> set = new HashSet<>();
                String combIndex = combQueue.poll();
                for (int row = 0; row < ROW_NUM; row++) {
                    String combStr = "";
                    for (char col : combIndex.toCharArray()) {
                        combStr += relation[row][Character.getNumericValue(col)];
                    }
                    if (!set.add(combStr)) {
                        int lastCol = Character.getNumericValue(combIndex.charAt(combIndex.length() - 1));
                        for (int col = lastCol + 1; col < COL_NUM; col++) {
                            String nextCombIndex = combIndex + col;
                            nextDepthQueue.add(nextCombIndex);
                        }
                        break;
                    }
                }
                if (set.size() == ROW_NUM) {
                    candidateSet.add(combIndex);
                }
            }
            
            combQueue = verifyCombQueue(candidateSet, nextDepthQueue);
        }
        
        return candidateSet.size();
    }
    
    public Queue<String> verifyCombQueue(Set<String> candidateSet, Queue<String> combQueue) {
        Queue<String> verifiedCombQueue = new LinkedList<>();
        
        while (!combQueue.isEmpty()) {
            String indexComb = combQueue.poll();
            boolean isPromising = true;
            
            for (String candidateComb : candidateSet) {
                boolean allContained = true;
                for (char combIdx : candidateComb.toCharArray()) {
                    if (indexComb.indexOf(combIdx) == -1) {
                        allContained = false;
                        break;
                    }
                }
                if (allContained) {
                    isPromising = false;
                    break;
                }
            }
            
            if (isPromising) {
                verifiedCombQueue.add(indexComb);
            }
        }
        
        return verifiedCombQueue;
    }
}
