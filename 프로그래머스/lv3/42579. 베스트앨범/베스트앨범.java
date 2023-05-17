import java.util.*;
import java.util.stream.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        // 장르별 재생 횟수를 저장할 HashSet
        HashMap<String, Integer> genresPlayCnt = new HashMap<>();
        // 장르별 노래 정보를 담을 HashSet
        HashMap<String, List<SongInfo>> songInfoByGenre = new HashMap<>();
        
        // 모든 노래를 순회하며 2개의 HashSet 초기화
        // O(m), m = (모든 노래 수)
        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            int playCount = plays[i];
            
            if (genresPlayCnt.containsKey(genre)) {
                genresPlayCnt.compute(genre, (k, v) -> v + playCount);
                songInfoByGenre.get(genre).add(new SongInfo(i, playCount));
            } else {
                genresPlayCnt.put(genre, playCount);
                songInfoByGenre.put(genre, new ArrayList<>(List.of(new SongInfo(i, playCount))));
            }
        }
        
        // 정답을 저장할 List 정의, 배열로 하려다가 장르에 속한 곡이 1개일 수 있어서 변경
        List<Integer> answer = new ArrayList<>();
        // 재생 횟수에 의거한 장르 랭킹
        // O(n log n), n = (장르 종류 수)
        String[]    genreRanking = genresPlayCnt.entrySet().stream()
                                                .sorted((entA, entB) -> entB.getValue() - entA.getValue())
                                                .map(Map.Entry::getKey)
                                                .toArray(String[]::new);
        
        // 장르 랭킹 순으로 2곡 씩 정답 배열에 추가
        // O(n * k log k), n = (장르 종류 수), k = (장르 별 노래 수)
        for (String genre : genreRanking) {
            // 장르별 노래 정보를 정렬 재생 횟수로 정렬
            List<SongInfo> songRanking = songInfoByGenre.get(genre);
            Collections.sort(songRanking, new Comparator<SongInfo>() {
                @Override
                public int compare(SongInfo a, SongInfo b) {
                    return a.getPlayCount() == b.getPlayCount() ? a.getIndex() - b.getIndex() : b.getPlayCount() - a.getPlayCount();
                }
            });
            
            // 1, 2위 곡을 정답 배열에 추가
            for (int i = 0; i < 2 && i < songRanking.size(); i++) {
                answer.add(songRanking.get(i).getIndex());
            }
        }
                                                             
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
    
    // 노래 정보 (고유 번호, 재생 횟수)
    class SongInfo {
        int index;
        int playCount;
        
        public SongInfo(int index, int playCount) { 
            this.index = index;
            this.playCount = playCount;
        }
        
        public int getIndex() { return index; }
        public int getPlayCount() { return playCount; }
    }
    // 전체 시간 복잡도
    // O(n * k log k), n = (장르 종류 수), k = (장르 별 노래 수)
}