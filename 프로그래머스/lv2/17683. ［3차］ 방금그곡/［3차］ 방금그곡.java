class Solution {
    public String solution(String m, String[] musicInfos) {
        // 정답 변수 초기화
        String answer = "(None)";
        // 정답 변수의 초기값의 길이가 6이기 때문에, 실제 정답의 길이가 6보다 작은 경우를 위해 따로 재생 시간에 대한 변수를 만들어줘야함
        int maxPlayTime = 0;
        
        // musicInfos를 순회
        for (String mi : musicInfos) {
            // "," 단위로 나눠 정보를 추출
            String[] musicInfo = mi.split(",");
            // 분단위로 음악의 재생 시간을 변환
            int playTime = timeConverterToMinute(musicInfo[0], musicInfo[1]);
            // 재생 시간만큼 악보 정보를 늘림
            String score = changeMusicLength(sharpConverter(musicInfo[3]), playTime);
            
            // O(n + m), n = score.length(), m = m.length()
            if (score.indexOf(sharpConverter(m)) != -1 && maxPlayTime < playTime) {
                // 정답 갱신
                answer = musicInfo[2];
                maxPlayTime = playTime;
            }
        }
        
        return answer;
    }
    
    // O(n), n = (나눌 문자열 길이)
    // 분단위로 음악의 재생 시간을 변환
    public int timeConverterToMinute(String s, String e) {
        String[] startTimeInfo = s.split(":");
        String[] endTimeInfo = e.split(":");
        int startTime = Integer.valueOf(startTimeInfo[0]) * 60 + Integer.valueOf(startTimeInfo[1]);
        int endTime = Integer.valueOf(endTimeInfo[0]) * 60 + Integer.valueOf(endTimeInfo[1]);
        
        return endTime - startTime;
    }
    
    // O(n), n = melody.length
    // #이 붙어 있는 음표를 소문자로 변경, ("C#" -> "c")
    public String sharpConverter(String melody) {
        StringBuilder convertedMelody = new StringBuilder();
        
        // 주어진 문자열을 순회
        for (int i = 0; i < melody.length(); i++) {
            char note = melody.charAt(i);
            
            // 현재 옴표가 '#'이라면 이전 음표를 소문자로 변경
            if (note == '#') {
                convertedMelody.setCharAt(convertedMelody.length() - 1, (char)(convertedMelody.charAt(convertedMelody.length() - 1) + 32));
            // 그렇지 않다면 convertedMelody에 추가
            } else {
                convertedMelody.append(note);
            }
        }
        
        return convertedMelody.toString();
    }
    
    public String changeMusicLength(String music, int length) {
        return music.repeat(length / music.length()) + music.substring(0, length % music.length());
    }
    
    // 시간 복잡도
    // O(n * m), n = musicInfos.length, m = (musicInfos[i]의 악보 정보 길이의 최댓값)
}