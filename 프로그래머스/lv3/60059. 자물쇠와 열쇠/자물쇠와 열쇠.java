import java.util.*;

class Solution {
    // lockset의 관련된 정보를 저장하는 클래스
    class LocksetInfo {
        static final int DOT = 1;
        static final int HOLE = 0;
        static final int KEY_MISMATCH_VALUE = 0;

        int[][] lockset;
        boolean isHaveFeat;
        int featInfo;
        int featStartRow;
        int featEndRow;
        int featStartCol;
        int featEndCol;

        public LocksetInfo(int[][] lockset, int featInfo) {
            this.lockset = lockset;
            this.featInfo = featInfo;
            getFeatRange(lockset, featInfo);
        }

        // O(r * c), r = lockset.length, c = lockset[0].length
        // featInfo에 해당하는 특징이 나타나는 범위를 계산
        private void getFeatRange(int[][] lockset, int featInfo) {        
            final int MAX_ROW = lockset.length;
            final int MAX_COL = lockset[0].length;

            this.isHaveFeat = false;
            this.featStartRow = Integer.MAX_VALUE; this.featEndRow = Integer.MIN_VALUE;
            this.featStartCol = Integer.MAX_VALUE; this.featEndCol = Integer.MIN_VALUE;

            for (int row = 0; row < MAX_ROW; row++) {
                for (int col = 0; col < MAX_COL; col++) {
                    if (lockset[row][col] == featInfo) {
                        this.featStartRow    = Math.min(featStartRow, row);
                        this.featEndRow      = Math.max(featEndRow, row);
                        this.featStartCol    = Math.min(featStartCol, col);
                        this.featEndCol      = Math.max(featEndCol, col);
                        this.isHaveFeat = true;
                    } 
                }
            }
        }

        // O(r * c), r = lockset.length, c = lockset[0].length
        // lockset 배열을 시계방향으로 90도 회전
        public void rotateClockwise() {
            final int MAX_ROW = this.lockset.length;
            final int MAX_COL = this.lockset[0].length;

            int[][] rotatedLockset = new int[MAX_COL][MAX_ROW];

            for (int row = 0; row < MAX_ROW; row++) {
                for (int col = 0; col < MAX_COL; col++) {
                    rotatedLockset[col][MAX_ROW - 1 - row] = this.lockset[row][col];
                }
            }
            
            this.lockset = rotatedLockset;

            int rotatedStartRow = this.featStartCol;
            int rotatedEndRow   = this.featEndCol;
            int rotatedStartCol = MAX_ROW - 1 - this.featEndRow;
            int rotatedEndCol   = MAX_ROW - 1 - this.featStartRow;

            this.featStartRow = rotatedStartRow; this.featEndRow = rotatedEndRow;
            this.featStartCol = rotatedStartCol; this.featEndCol = rotatedEndCol;
        }
    }
    
    public boolean solution(int[][] key, int[][] lock) {
        // O(r * c), r = lockset.length, c = lockset[0].length
        // 각 lockset의 정보를 추출하고 저장
        LocksetInfo lockInfo = new LocksetInfo(lock, LocksetInfo.HOLE);
        LocksetInfo keyInfo  = new LocksetInfo(key, LocksetInfo.DOT);
        
        // 자물쇠에 홈이 없으면
        if (!lockInfo.isHaveFeat) return true;
        // 열쇠에 돌기가 없으면
        if (!keyInfo.isHaveFeat) return false;
        
        // 열쇠를 90도씩 회전시키며
        for (int i = 0; i < 4; i++) {
            if (isRightKey(keyInfo, lockInfo)) {
                return true;
            }
            keyInfo.rotateClockwise();
        }
        
        return false;
    }
    
    // 해당 key가 lock에 알맞은 key인지 확인
    public boolean isRightKey(LocksetInfo keyInfo, LocksetInfo lockInfo) {
        // 열쇠 구명(Feature of Lock, HOLE)과 열쇠 돌기(Feature of Key, DOT) 크기차이
        final int HEIGHT_DIFF = (keyInfo.featEndRow - keyInfo.featStartRow) - (lockInfo.featEndRow - lockInfo.featStartRow);
        final int WIDTH_DIFF  = (keyInfo.featEndCol - keyInfo.featStartCol) - (lockInfo.featEndCol - lockInfo.featStartCol);
        
        // 열쇠 구멍이 열쇠 돌기보다 크면
        if (HEIGHT_DIFF < 0 || WIDTH_DIFF < 0) return false;
        
        // O((KFH - LFH) * (KFW - LFW) * KFH * KFW), KFH = (key의 특징이 나타나는 범위의 높이), KFW = (key의 특징이 나타나는 범위의 넓이), LFH = (lock의 특징이 나타나는 범위의 높이), LFW = (lock 특징이 나타나는 범위의 넓이)
        // 열쇠 구멍에 맞게 열쇠를 움직여가면서 확인
        // (열쇠 구멍 부분 검사) + (열쇠 구멍 이외에 열쇠가 자물쇠에 닿는 부분 검사)
        for (int row = 0; row <= HEIGHT_DIFF; row++) {
            for (int col = 0; col <= WIDTH_DIFF; col++) {
                if (verifyKey(keyInfo, lockInfo, lockInfo.featStartRow - row,  lockInfo.featStartCol - col)) {
                    return true;
                }
            }
        }
                
        return false;
    }
    
    // O(KFH * KFW), KFH = (key의 특징이 나타나는 범위의 높이), KFW = (key의 특징이 나타나는 범위의 넓이)
    // key의 특징이 나타나는 부분 중 왼쪽 상단을 lock에 (keyRowOnLock, keyColOnLock)에 두었을 때 맞는지 검증
    public boolean verifyKey(LocksetInfo keyInfo, LocksetInfo lockInfo, int keyRowOnLock, int keyColOnLock) {
        // key의 특징이 나타나는 범위
        final int KEY_FEAT_HEIGHT = keyInfo.featEndRow - keyInfo.featStartRow;
        final int KEY_FEAT_WIDTH  = keyInfo.featEndCol - keyInfo.featStartCol;
        
        // key의 특징(HOLE == 1)이 나타나는 범위를 lock에 올려두고 검사
        for (int r = 0; r <= KEY_FEAT_HEIGHT; r++) {
            for (int c = 0; c <= KEY_FEAT_WIDTH; c++) {
                // (key가 lock에서 삐져나가지 않은 부분) && (열쇠의 돌기가 자물쇠의 홈과 맞물리지 않는 경우)
                if (isInLockset(lockInfo.lockset, keyRowOnLock + r, keyColOnLock + c) &&
                    (keyInfo.lockset[keyInfo.featStartRow + r][keyInfo.featStartCol + c] ^ lockInfo.lockset[keyRowOnLock + r][keyColOnLock + c]) == 0) {
                    return false;
                }
            }
        }
        
        // 열쇠의 돌기가 자물쇠의 모든 홈과 맞물린 경우
        return true;
    }
    
    public boolean isInLockset(int[][] lockset, int row, int col) {
        final int MAX_ROW = lockset.length;
        final int MAX_COL = lockset[0].length;
        
        return 0 <= row && row < MAX_ROW && 0 <= col  && col < MAX_COL;
    }
    
    // 시간 복잡도
    // O((KFH - LFH) * (KFW - LFW) * KFH * KFW), KFH = (key의 특징이 나타나는 범위의 높이), KFW = (key의 특징이 나타나는 범위의 넓이), LFH = (lock의 특징이 나타나는 범위의 높이), LFW = (lock 특징이 나타나는 범위의 넓이)
    // -> O(KFH^2 * KFW^2 - (KFH^2 * KFW * LFW + KFW^2 * KFH * LFH) + KFH * KFW * LFH * LFW)
    // -> O(KFH^2 * KFW^2)
    // Key의 특징(돌기)이 나타나는 범위가 넓으면 넓을 수록 많은 시간(연산)을 소모함
    // 해당 연산은 isRightKey 함수에서 실행
}
