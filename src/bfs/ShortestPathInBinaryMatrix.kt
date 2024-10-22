package bfs

class ShortestPathInBinaryMatrix {
    fun shortestPathBinaryMatrix(grid: Array<IntArray>): Int {
        if (grid.isEmpty()) return -1
        val N = grid.size // represents number of rows
        val M = grid[0].size // represents number of columns
        val queue = ArrayDeque<Pair<Int, Int>>()
        val seen = mutableSetOf<Pair<Int, Int>>()

        if (grid[0][0] != 0) {
            return -1
        }

        queue.addLast(0 to 0)
        seen.add(0 to 0)
        var level = 0
        while (queue.isNotEmpty()) {
            repeat(queue.size) {
                val curr = queue.removeFirst()
                if (curr.first == N - 1 && curr.second == M - 1) {
                    return level
                }
                for (neighbor in grid.availableNeighbors(curr, seen)) {
                    seen.add(neighbor)
                    queue.addLast(neighbor)
                }
            }
            level++
        }
        return -1
    }
}

private fun Array<IntArray>.availableNeighbors(
    curr: Pair<Int, Int>,
    seen: MutableSet<Pair<Int, Int>>,
): List<Pair<Int, Int>> {
    val M = this.size
    val N = this[0].size
    val row = curr.first
    val col = curr.second

    val options = listOf(
        row to col + 1,
        row + 1 to col + 1,
        row + 1 to col,
        row + 1 to col - 1,
        row to col - 1,
        row - 1 to col - 1,
        row - 1 to col,
        row - 1 to col + 1,
    )

    return options
        .filter {
            val r = it.first
            val c = it.second
            if (r < 0 || r >= M) {
                return@filter false
            }
            if (c < 0 || c >= N) {
                return@filter  false
            }
            if (this[r][c] != 0) {
                return@filter false
            }
            seen.contains(r to c).not()
        }
}
