package matrixGraphs

class NumberOfIslands {
    private val directions = listOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)

    fun numIslands(grid: Array<CharArray>): Int {
        val rows = grid.size
        val cols = grid[0].size
        var counter = 0
        val visited = Array(rows) {
            BooleanArray(cols)
        }

        for (row in 0..<rows) {
            for (col in 0..<cols) {
                if (visited[row][col].not() && grid[row][col] == '1') {
                    counter++
                    bfs(grid, row, col, visited)
                }
            }
        }

        return counter
    }

    private fun bfs(grid: Array<CharArray>, row: Int, col: Int, visited: Array<BooleanArray>) {
        val queue = ArrayDeque<Pair<Int, Int>>()
        queue.addLast(row to col)
        visited[row][col] = true
        while (queue.isNotEmpty()) {
            val (currRow, currCol) = queue.removeFirst()
            for ((x, y) in directions) {
                val nextRow = x + currRow
                val nextCol = y + currCol
                if (nextRow >= 0 && nextCol >= 0 && nextRow < grid.size && nextCol < grid[0].size && visited[nextRow][nextCol].not() && grid[nextRow][nextCol] == '1') {
                    queue.addLast(nextRow to nextCol)
                    visited[nextRow][nextCol] = true
                }
            }
        }
    }
}