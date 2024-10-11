package bfs

fun main() {
    val n = 3
    val redEdges = arrayOf(intArrayOf(0, 1), intArrayOf(1, 2))
    val blueEdges = arrayOf<IntArray>()

    shortestAlternatingPaths(n, redEdges, blueEdges)
}

// https://leetcode.com/problems/shortest-path-with-alternating-colors/
private fun shortestAlternatingPathsWithMap(n: Int, redEdges: Array<IntArray>, blueEdges: Array<IntArray>): IntArray {
    val redPathGraph = mutableMapOf<Int, MutableList<Int>>()
    val bluePathGraph = mutableMapOf<Int, MutableList<Int>>()

    redEdges.forEach {
        redPathGraph[it[0]] = redPathGraph.getOrDefault(it[0], mutableListOf()).apply {
            add(it[1])
        }
    }

    blueEdges.forEach {
        bluePathGraph[it[0]] = bluePathGraph.getOrDefault(it[0], mutableListOf()).apply {
            add(it[1])
        }
    }

    val graph = listOf(redPathGraph, bluePathGraph)

    // each row for each color
    val res = Array(2) {
        IntArray(n) { Int.MAX_VALUE }
    }
    res[0][0] = 0
    res[0][1] = 0
    //  pair: vertex, color: 0 -> red, 1 -> blue
    val queue = ArrayDeque<Pair<Int, Int>>()
    queue.apply {
        addLast(0 to 0)
        addLast(0 to 1)
    }

    while (queue.isNotEmpty()) {
        val (node, color) = queue.removeFirst()

        for (next in (graph[1 - color][node]) ?: emptyList()) {
            if (res[1 - color][next] == Int.MAX_VALUE) {
                res[1 - color][next] = res[color][node] + 1
                queue.addLast(Pair(1 - color, next))
            }
        }
    }

    val refinedResult = IntArray(n)
    for (i in 0..<n) {
        refinedResult[i] = minOf(res[0][i], res[1][i])
        if (refinedResult[i] == Int.MAX_VALUE) {
            refinedResult[i] = -1
        }
    }
    return refinedResult
}

// with list: indices of the list act like a key of the map
private fun shortestAlternatingPaths(n: Int, redEdges: Array<IntArray>, blueEdges: Array<IntArray>): IntArray {
    // row0 -> red
    // row1 -> blue
    val graph = Array(2) {
        List<MutableList<Int>>(n) {
            mutableListOf()
        }
    }

    redEdges.forEach {
        graph[0][it[0]].add(it[1])
    }

    blueEdges.forEach {
        graph[1][it[0]].add(it[1])
    }

    val res = Array(2) { IntArray(n) { Int.MAX_VALUE } }
    // for the first vertex (0)
    res[0][0] = 0
    res[1][0] = 0

    val queue = ArrayDeque<Pair<Int, Int>>()
    // add the first vertex (0) to the queue as if it were accessed by both colors
    queue.addLast(0 to 0)
    queue.addLast(1 to 0)

    while (queue.isNotEmpty()) {
        val (color, node) = queue.removeFirst()
        for (next in graph[1 - color][node]) {
            if (res[1 - color][next] == Int.MAX_VALUE) {
                res[1 - color][next] = 1 + res[color][node]
                queue.addLast(Pair(1 - color, next))
            }
        }
    }

    return IntArray(n) {
        val r = minOf(res[0][it], res[1][it])
        if (r == Int.MAX_VALUE) {
            -1
        } else {
            r
        }
    }
}



