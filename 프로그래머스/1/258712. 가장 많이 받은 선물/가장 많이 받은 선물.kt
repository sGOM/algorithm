class Solution {
    fun solution(friends: Array<String>, gifts: Array<String>): Int {
        val fs = friends.associateWith{ Friend(it, friends.filter{ name -> name != it }.associateWith{0}.toMutableMap()) }
        gifts.forEach { g ->
            val (f1, f2) = g.split(" ")
            fs[f1]!!.giftScores[f2] = fs[f1]!!.giftScores[f2]!! + 1
            fs[f2]!!.giftScores[f1] = fs[f2]!!.giftScores[f1]!! - 1
        }
        
        fs.values.forEach{ it.renewScore() }
        
        return fs.values.map{ it.calcNextMonthGift(fs) }.maxOrNull() ?: -1
    }
    
    class Friend (
        val name: String,
        val giftScores: MutableMap<String, Int>
    ) {
        var totalScore = 0
        
        fun renewScore() {
            totalScore = giftScores.values.sum()
        }
        
        // 반드시 모든 Friend의 renewScore 이후 실행
        fun calcNextMonthGift(fs: Map<String, Friend>): Int {
            return giftScores.count { (name, score) ->
                score > 0 || (score == 0 && totalScore > fs[name]!!.totalScore)
            }
        }
    }
}