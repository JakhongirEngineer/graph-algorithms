package matrixGraphs

class NumberOfEnclaves {
    private val directions = listOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)

    fun numEnclaves(grid: Array<IntArray>): Int {
        val rows = grid.size
        val cols = grid[0].size
        val queue = ArrayDeque<Pair<Int, Int>>()

        val visited = Array(rows) {
            BooleanArray(cols)
        }

        for (row in 0..<rows) {
            if (grid[row][0] == 1) {
                queue.addLast(row to 0)
                visited[row][0] = true
            }

            if (grid[row][cols - 1] == 1) {
                queue.addLast(row to (cols - 1))
                visited[row][cols - 1] = true
            }
        }

        for (col in 1..<(cols - 1)) {
            if (grid[0][col] == 1) {
                queue.addLast(0 to col)
                visited[0][col] = true
            }

            if (grid[rows - 1][col] == 1) {
                queue.addLast((rows - 1) to col)
                visited[rows - 1][col] = true
            }

        }

        while (queue.isNotEmpty()) {
            val (currRow, currCol) = queue.removeFirst()
            for ((x, y) in directions) {
                val nextRow = currRow + x
                val nextCol = currCol + y

                if (nextRow in 0..<rows && nextCol in 0..<cols && visited[nextRow][nextCol].not() && grid[nextRow][nextCol] == 1) {
                    visited[nextRow][nextCol] = true
                    queue.addLast(nextRow to nextCol)
                }
            }
        }

        var counter = 0
        for (r in 1..<(rows - 1)) {
            for (c in 1..<(cols - 1)) {
                if (visited[r][c].not() && grid[r][c] == 1) {
                    counter++
                }
            }
        }
        return counter
    }
}