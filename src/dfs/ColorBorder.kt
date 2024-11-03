package dfs

// https://leetcode.com/problems/coloring-a-border/
class ColorBorder {

    fun colorBorder(grid: Array<IntArray>, row: Int, col: Int, color: Int): Array<IntArray> {
        val start = row to col
        val rowN = grid.size
        val colN = grid[0].size

        val visited = Array(rowN) {
            BooleanArray(colN) { false }
        }

        val stack = ArrayDeque<Pair<Int,Int>>()
        stack.addLast(start)
        visited[start.first][start.second] = true
        val directions = listOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)
        val borders = mutableListOf<Pair<Int,Int>>()
        while(stack.isNotEmpty()) {
            val (currRow, currCol) = stack.removeLast()
            for (direction in directions) {
                val newRow = currRow + direction.first
                val newCol = currCol + direction.second

                if (newRow < 0 || newRow >= rowN || newCol < 0 || newCol >= colN || grid[currRow][currCol] != grid[newRow][newCol]) {
                    borders.add(currRow to currCol)
                } else {
                    if (visited[newRow][newCol].not()) {
                        stack.addLast(newRow to newCol)
                        visited[newRow][newCol] = true
                    }
                }
            }
        }
        borders.forEach {
            grid[it.first][it.second] = color
        }
        return grid
    }
}