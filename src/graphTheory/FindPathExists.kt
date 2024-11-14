package graphTheory

class FindPathExists {
    fun validPath(n: Int, edges: Array<IntArray>, source: Int, destination: Int): Boolean {
        val graph = mutableMapOf<Int, MutableList<Int>>()

        for((parent, child) in edges) {
            graph.computeIfAbsent(parent) { mutableListOf() }
                .add(child)
            graph.computeIfAbsent(child) { mutableListOf() }
                .add(parent)
        }

        val queue = ArrayDeque<Int>()
        queue.addLast(source)
        val visited = mutableSetOf(source)

        while (queue.isNotEmpty()) {
            val curr = queue.removeFirst()
            if (curr == destination) return true
            for (neighbor in graph[curr] ?: emptyList()) {
                if (neighbor !in visited) {
                    visited += neighbor
                    queue.addLast(neighbor)
                }
            }
        }
        return false
    }
}