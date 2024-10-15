package bfs

import java.lang.RuntimeException

class JumpGame3 {
    //https://leetcode.com/problems/jump-game-iii/description/
    // time: O(N) because each value in the array is visited only once
    // space: O(N) for the set and for the queue
    fun canReach(arr: IntArray, start: Int): Boolean {
        require(start >= 0 && start < arr.size)
        if (arr[start] == 0) return true

        val seen = mutableSetOf<Int>()
        val queue = ArrayDeque<Int>()
        seen.add(start)
        queue.addLast(start)

        while (queue.isNotEmpty()) {
            val currIdx = queue.removeFirst()
            if (arr[currIdx] == 0) {
                return true
            }
            val indices = arrayOf(currIdx + arr[currIdx], currIdx - arr[currIdx])
            for (index in indices) {
                if (index >= 0 && index < arr.size && !seen.contains(index)) {
                    seen.add(index)
                    queue.addLast(index)
                }
            }
        }
        return false
    }
}