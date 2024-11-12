package matrixGraphs


fun main() {
    val heights = arrayOf(
        intArrayOf(1, 2, 2, 3, 5),
        intArrayOf(3, 2, 3, 4, 4),
        intArrayOf(2, 4, 5, 3, 1),
        intArrayOf(6, 7, 1, 4, 5),
        intArrayOf(5, 1, 1, 2, 4)
    )
    PacificAtlanticWaterFlow()
        .pacificAtlantic(
            heights
        )
}

class PacificAtlanticWaterFlow {
    private val directions = listOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)

    fun pacificAtlantic(heights: Array<IntArray>): List<List<Int>> {
        val rows = heights.size
        val cols = heights[0].size

        val pacificQueue = ArrayDeque<Pair<Int, Int>>()
        val atlanticQueue = ArrayDeque<Pair<Int, Int>>()
        val pacificVisited = Array(heights.size) {
            BooleanArray(heights[0].size) { false }
        }

        val atlanticVisited = Array(heights.size) {
            BooleanArray(heights[0].size) { false }
        }

        val graph = Array(rows) {
            Array<Pair<Boolean, Boolean>>(cols) {
                false to false
            }
        }

        for (row in 0..<rows) {
            pacificQueue.addLast(row to 0)
            atlanticQueue.addLast(row to (cols - 1))
        }

        for (col in 0..<cols) {
            pacificQueue.addLast(0 to col)
            atlanticQueue.addLast((rows - 1) to col)
        }

        while (pacificQueue.isNotEmpty()) {
            val (row, col) = pacificQueue.removeFirst()
            bfs(heights, row, col, pacificVisited) { currRow, currCol ->
                graph[currRow][currCol] = true to graph[currRow][currCol].second
            }
        }

        while (atlanticQueue.isNotEmpty()) {
            val (row, col) = atlanticQueue.removeFirst()
            bfs(heights, row, col, atlanticVisited) { currRow, currCol ->
                graph[currRow][currCol] = graph[currRow][currCol].first to true
            }
        }
        val res = mutableListOf<List<Int>>()
        for (row in 0..<rows) {
            for (col in 0..<cols) {
                val (f, s) = graph[row][col]
                if (f && s) {
                    res.add(listOf(row, col))
                }
            }
        }

        return res
    }

    private fun bfs(
        heights: Array<IntArray>,
        row: Int,
        col: Int,
        visited: Array<BooleanArray>,
        block: (currRow: Int, currCol: Int) -> Unit
    ) {
        val queue = ArrayDeque<Pair<Int, Int>>()

        queue.addLast(row to col)
        visited[row][col] = true

        while (queue.isNotEmpty()) {
            val (currRow, currCol) = queue.removeFirst()
            block(currRow, currCol)

            for ((x, y) in directions) {
                val nextRow = currRow + x
                val nextCol = currCol + y

                if (
                    nextRow >= 0
                    && nextCol >= 0
                    && nextRow < heights.size
                    && nextCol < heights[0].size
                    && visited[nextRow][nextCol].not()
                    && heights[nextRow][nextCol] >= heights[currRow][currCol]
                ) {
                    visited[nextRow][nextCol] = true
                    queue.addLast(nextRow to nextCol)
                }
            }
        }
    }
}