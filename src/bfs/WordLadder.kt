package bfs

fun main() {
    WordLadder()
        .ladderLength("hit", "cog", listOf("hot","dot","dog","lot","log","cog"))
}

class WordLadder {
    // https://leetcode.com/problems/word-ladder/
    fun ladderLength(beginWord: String, endWord: String, wordList: List<String>): Int {

        var level = 0
        val seen = mutableSetOf<String>()
        val queue = ArrayDeque<String>()
        queue.addLast(beginWord)

        while (queue.isNotEmpty()) {
            repeat(queue.size) {
                val curr = queue.removeFirst()
                if (curr == endWord) return level + 1
                for (neighbor in getNeighbors(curr, wordList, seen)) {
                    queue.addLast(neighbor)
                    seen.add(neighbor)
                }
            }
            level++
        }
        return 0
    }

    private fun getNeighbors(curr: String, wordList: List<String>, seen: MutableSet<String>): List<String> {
        return wordList.filter { !seen.contains(it) }
            .filter { curr.differByOneLetter(it) }
    }

    private fun String.differByOneLetter(another: String): Boolean {
        if (this.length != another.length) return false
        var oneDifference = false
        for ((idx, ch) in this.withIndex()) {
            if (ch != another[idx] && !oneDifference) {
                oneDifference = true
            } else if (ch != another[idx] && oneDifference) {
                return false
            }
        }
        return true
    }
}