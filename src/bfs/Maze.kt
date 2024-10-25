package bfs

fun main() {
    val maze = arrayOf(
        intArrayOf(0, 0, 1, 0, 0),
        intArrayOf(0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 1, 0),
        intArrayOf(1, 1, 0, 1, 1),
        intArrayOf(0, 0, 0, 0, 0),
    )
    val start = intArrayOf(0, 4)
    val destination = intArrayOf(4, 4)

    Maze()
        .hasPath(maze, start, destination)
}

class Maze {
    fun hasPath(maze: Array<IntArray>, start: IntArray, destination: IntArray): Boolean {
        val queue = ArrayDeque<Pair<Int, Int>>()
        val seen = mutableSetOf<Pair<Int, Int>>()
        if (maze[start[0]][start[1]] != 0) return false

        queue.addLast(start[0] to start[1])
        seen.add(start[0] to start[1])

        while (queue.isNotEmpty()) {
            repeat(queue.size) {
                val currentPosition = queue.removeFirst()
                if (currentPosition == destination[0] to destination[1]) {
                    return true
                }
                for (nextPosition in getNextPositions(maze, currentPosition, seen)) {
                    queue.addLast(nextPosition)
                    seen.add(nextPosition)
                }
            }
        }
        return false
    }

    private fun getNextPositions(
        maze: Array<IntArray>,
        currentPosition: Pair<Int, Int>,
        seen: Set<Pair<Int, Int>>
    ): List<Pair<Int, Int>> {
        val row = currentPosition.first
        val col = currentPosition.second

        // to right
        // [0,0,0,0]
        // [0,0,0,0]
        // [0,0,0,0]
        var rcol = col
        while (rcol < maze[row].size && maze[row][rcol] == 0) {
            rcol++
        }
        rcol--

        var lcol = col
        while (lcol >= 0 && maze[row][lcol] == 0) {
            lcol--
        }
        lcol++

        var urow = row
        while (urow >= 0 && maze[urow][col] == 0) {
            urow--
        }
        urow++

        var drow = row
        while (drow < maze.size && maze[drow][col] == 0) {
            drow++
        }
        drow--

        return listOf(
            row to rcol,
            row to lcol,
            urow to col,
            drow to col,
        ).filter { !seen.contains(it) }
    }
}