package matrixGraphs

val directions = listOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)

class ZeroOneMatrix {

    fun updateMatrix(mat: Array<IntArray>): Array<IntArray> {
        val rows = mat.size
        val cols = mat[0].size

        val zerosQueue = ArrayDeque<Pair<Int, Int>>()

        for (row in 0..<rows) {
            for (col in 0..<cols) {
                if (mat[row][col] == 0) {
                    zerosQueue.addLast(row to col)
                }
            }
        }
        // -1 -> unvisited yet
        val result = Array(rows) { row ->
            IntArray(cols) { col ->
                if (mat[row][col] == 0) {
                    0
                } else {
                    Int.MAX_VALUE
                }
            }
        }
        while (zerosQueue.isNotEmpty()) {
            val (zeroRow, zeroCol) = zerosQueue.removeFirst()
            mat.bfs(zeroRow, zeroCol, result)
        }
        return result
    }


}

private fun Array<IntArray>.bfs(zeroRow: Int, zeroCol: Int, result: Array<IntArray>) {
    var from = 1
    val queue = ArrayDeque<Pair<Int, Int>>()
    queue.addLast(zeroRow to zeroCol)

    while (queue.isNotEmpty()) {
        repeat(queue.size) {
            val (currRow, currCol) = queue.removeFirst()
            for ((x, y) in directions) {
                val nextRow = currRow + x
                val nextCol = currCol + y

                if (
                    nextRow >= 0
                    && nextCol >= 0
                    && nextRow < result.size
                    && nextCol < result[0].size
                    && result[nextRow][nextCol] > from
                ) {
                    result[nextRow][nextCol] = from
                    queue.addLast(nextRow to nextCol)
                }
            }
        }
        from++
    }
}
