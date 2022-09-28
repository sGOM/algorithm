import java.util.PriorityQueue;

class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> minPriQueue = new PriorityQueue<>();
        PriorityQueue<Integer> maxPriQueue = new PriorityQueue<>(11, (a, b) -> b - a);

        String opRemoveMax = "D 1";
        String opRemoveMin = "D -1";

        for (String op : operations) {
            if (op.equals(opRemoveMax)) {
                if (minPriQueue.size() != 0) {
                    minPriQueue.remove(maxPriQueue.poll());
                }
                continue;
            }
            if (op.equals(opRemoveMin)) {
                if (maxPriQueue.size() != 0) {
                    maxPriQueue.remove(minPriQueue.poll());
                }
                continue;
            }
            minPriQueue.add(Integer.parseInt(op.substring(2)));
            maxPriQueue.add(Integer.parseInt(op.substring(2)));
        }

        if (minPriQueue.size() == 0) return new int[] {0, 0};
        return new int[] {maxPriQueue.peek(), minPriQueue.peek()};
    }
}