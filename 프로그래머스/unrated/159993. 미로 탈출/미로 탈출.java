import java.util.*;

class Solution {
    final char START = 'S';
    final char END = 'E';
    final char LEVER = 'L';
    final char WALL = 'X';
    final int NO_WAY = -1;
    int MAX_ROW;
    int MAX_COL;
    
    public int solution(String[] strMaps) {
        MAX_ROW = strMaps.length;
        MAX_COL = strMaps[0].length();
        
        HashMap<Character, Point> pointMap = new HashMap<>(Map.of(START, new Point(0, 0), LEVER, new Point(0, 0), END, new Point(0, 0)));
        char[][] map = new char[MAX_ROW][MAX_COL];
        init(pointMap, map, strMaps);
        
        int time = 0;
        time = calcPathTime(pointMap.get(START), pointMap.get(LEVER), map, time);
        time = calcPathTime(pointMap.get(LEVER), pointMap.get(END), map, time);
        
        return time;
    }
    
    // O(r * c), r = map.length, c = map[0].length
    public void init(HashMap<Character, Point> pointMap, char[][] map, String[] strMaps) {
        for (int row = 0; row < MAX_ROW; row++) {
            for (int col = 0; col < MAX_COL; col++) {
                map[row][col] = strMaps[row].charAt(col);
                if (pointMap.containsKey(map[row][col])) {
                    pointMap.put(map[row][col], new Point(row, col));
                }
            }
        }
    }
    
    // O(r * c), r = map.length, c = map[0].length
    // BFS
    public int calcPathTime(Point startPoint, Point endPoint, char[][] map, int startTime) {
        // 이전에 이동에서 길이 없는 경우
        if (startTime == NO_WAY) return NO_WAY;
        
        final int[][] DIR = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
        boolean[][] isVisited = new boolean[MAX_ROW][MAX_COL];
        isVisited[startPoint.row][startPoint.col] = true;
        
        // 위치와 거리를 저장
        Queue<Map.Entry<Point, Integer>> pointQueue = new LinkedList<>(List.of(new AbstractMap.SimpleEntry<>(startPoint, startTime)));
        while (!pointQueue.isEmpty()) {
            Map.Entry<Point, Integer> infoMap = pointQueue.poll();
            Point curPoint = infoMap.getKey();
            int curTime = infoMap.getValue();
                        
            for (int[] dir : DIR) {
                Point nextPoint = new Point(curPoint.row + dir[0], curPoint.col + dir[1]);
                if (isInMap(nextPoint) && map[nextPoint.row][nextPoint.col] != WALL && !isVisited[nextPoint.row][nextPoint.col]) {
                    if (nextPoint.equals(endPoint)) return curTime + 1;
                    
                    isVisited[nextPoint.row][nextPoint.col] = true;
                    pointQueue.add(new AbstractMap.SimpleEntry<>(nextPoint, curTime + 1));
                }
            }
        }
        
        return NO_WAY;
    }
    
    public boolean isInMap(Point point) {
        return 0 <= point.row && point.row < MAX_ROW && 0 <= point.col && point.col < MAX_COL;
    }
    
    class Point {
        int row;
        int col;
        
        Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Point)) return false;
            Point that = (Point)o;
            return this.row == that.row && this.col == that.col;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(this.row, this.col);
        }
    }
    
    // 시간 복잡도
    // O(r * c), r = map.length, c = map[0].length
    // 그저 두 지점 사이의 최단 거리를 2번 구하면 되는 문제
}
