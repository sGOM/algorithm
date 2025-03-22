class Solution {
    fun solution(s: String): String {
        var min = Int.MAX_VALUE
        var max = Int.MIN_VALUE
        for (i in s.split(" ").map{it.toInt()}) {
            if (i > max) {
                max = i
            }
            if (i < min) {
                min = i
            }
        }
        return "$min $max"
    }
}