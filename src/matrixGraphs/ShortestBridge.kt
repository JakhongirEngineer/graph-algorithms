package matrixGraphs

fun main() {
    ShortestBridge()
        .shortestBridge(
            arrayOf(
                intArrayOf(1, 1, 1, 1, 1),
                intArrayOf(1, 0, 0, 0, 1),
                intArrayOf(1, 0, 1, 0, 1),
                intArrayOf(1, 0, 0, 0, 1),
                intArrayOf(1, 1, 1, 1, 1),
            )
        )
}

class ShortestBridge {
    private val directions = listOf(
        0 to 1,
        0 to -1,
        1 to 0,
        -1 to 0,
    )

    fun shortestBridge(grid: Array<IntArray>): Int {
        val rows = grid.size
        val cols = grid[0].size
        val queue = ArrayDeque<Pair<Int, Int>>()

        outer@ for (row in 0..<rows) {
            inner@ for (col in 0..<cols) {
                if (grid[row][col] == 1) {
                    dfs(grid, row, col, queue)
                    break@outer
                }
            }
        }

        var level = 0

        while (queue.isNotEmpty()) {
            repeat(queue.size) {
                val (currRow, currCol) = queue.removeFirst()

                for ((x, y) in directions) {
                    val nextRow = currRow + x
                    val nextCol = currCol + y
                    if (nextRow >= 0 && nextRow < grid.size && nextCol >= 0 && nextCol < grid[0].size && grid[nextRow][nextCol] != 2) {
                        if (grid[nextRow][nextCol] == 1) {
                            return level
                        }
                        grid[nextRow][nextCol] = 2
                        queue.addLast(nextRow to nextCol)
                    }
                }
            }
            level++
        }

        return -1
    }

    private fun dfs(grid: Array<IntArray>, row: Int, col: Int, queue: ArrayDeque<Pair<Int, Int>>) {
        grid[row][col] = 2
        queue.addLast(row to col)
        for ((x, y) in directions) {
            val currRow = row + x
            val currCol = col + y
            if (currRow >= 0 && currRow < grid.size && currCol >= 0 && currCol < grid[0].size && grid[currRow][currCol] == 1) {
                dfs(grid, currRow, currCol, queue)
            }
        }
    }
}