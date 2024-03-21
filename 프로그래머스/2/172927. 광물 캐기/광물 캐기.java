import java.util.*;

class Solution {
    final int DIA_HARDNESS = 25;
    final int IRON_HARDNESS = 5;
    final int STONE_HARDNESS = 1;
    
    final int PICKAXE_STR = 5;
    
    public int solution(int[] picks, String[] minerals) {
        // 경도(hardness)가 높은 순으로 정렬, MineralPack의 경우 경도의 총합을 기준으로
        PriorityQueue<Pickaxe> pickaxePQ = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<MineralPack> mineralPackPQ = new PriorityQueue<>(Comparator.reverseOrder());
        
        // pickaxePQ 초기화
        for (int idx = 0; idx < picks.length; idx++) {
            int hardness = 0;
            switch (idx) {
                case 0:
                    hardness = DIA_HARDNESS;
                    break;
                case 1:
                    hardness = IRON_HARDNESS;
                    break;
                case 2:
                    hardness = STONE_HARDNESS;
                    break;
            }
            
            for (int n = 0; n < picks[idx]; n++) {
                pickaxePQ.add(new Pickaxe(hardness));
            }
        }
        
        // mineralPackPQ 초기화
        {
            // 곡괭이 수에 의한 제한 추가
            final int MAXIMUM_AMOUNT_EXTRACTED = Arrays.stream(picks).sum() * PICKAXE_STR;
            
            MineralPack mp = null;
            for (int idx = 0; idx < minerals.length && idx < MAXIMUM_AMOUNT_EXTRACTED; idx++) {
                if (idx % PICKAXE_STR == 0) {
                    if (mp != null) { mineralPackPQ.add(mp); }
                    mp = new MineralPack();
                }
                
                mp.addMineral(new Mineral(mapMineralNameToHardness(minerals[idx])));
            }
            mineralPackPQ.add(mp);
        }
        
        // 피로도 계산
        int fatigueSum = 0;
        while (!mineralPackPQ.isEmpty()) {
            MineralPack mp = mineralPackPQ.poll();
            Pickaxe p = pickaxePQ.poll();
            
            fatigueSum += mp.calcFatigue(p);
        }
        
        return fatigueSum;
    }
    
    public int mapMineralNameToHardness(String mineralName) {
        switch (mineralName.toLowerCase()) {
            case "diamond":
                return DIA_HARDNESS;
            case "iron":
                return IRON_HARDNESS;
            case "stone":
                return STONE_HARDNESS;
        }
        
        return -1;
    }
    
    public class Pickaxe implements Comparable<Pickaxe> {
        private int hardness = 0;
        
        public Pickaxe(int hardness) {
            this.hardness = hardness;
        }
        
        public int getHardness() {
            return this.hardness;
        }
        
        @Override
        public int compareTo(Pickaxe other) {
            return Integer.compare(this.getHardness(), other.getHardness());
        }
    }
    
    public class Mineral {
        private int hardness = 0;
        
        public Mineral(int hardness) {
            this.hardness = hardness;
        }
        
        public int getHardness() {
            return this.hardness;
        }
    }
    
    // 광물들을 저장한 팩, 이 코드에서는 곡괭이 1개로 캘 수 있는 연속된 광물들을 저장할 예정
    public class MineralPack implements Comparable<MineralPack> {
        ArrayList<Mineral> minerals;
        
        public MineralPack() {
            this.minerals = new ArrayList<>();
        }
        
        public int calcHardness() {
            return this.minerals.stream().mapToInt(m -> m.getHardness()).sum();
        }
        
        public int calcFatigue(Pickaxe p) {
            return this.minerals.stream()
                .mapToInt(m -> Math.max(m.getHardness() / p.getHardness(), 1))
                .sum();
        }
        
        public boolean addMineral(Mineral m) {
            return this.minerals.add(m);
        }
        
        @Override
        public int compareTo(MineralPack other) {
            return Integer.compare(this.calcHardness(), other.calcHardness());
        }
    }
}

/*
* 시간 복잡도
* 가장 오래 걸리는 프로세스는 PriorityQueue.add 혹은 PriorityQueue.poll로 O(log(n))이며
* 따라서 전체 시간 복잡도를 해당 코드가 지배적으로 가짐
* n = minerals.length, a <= n/5
* O((n/5) * (log a)) == O(n log n)
*/