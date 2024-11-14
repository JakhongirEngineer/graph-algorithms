package graphTheory

class ValidTree {
    fun validTree(n: Int, edges: Array<IntArray>): Boolean {
        val graph = mutableMapOf<Int, MutableList<Int>>()

        for ((parent, child) in edges) {
            graph.computeIfAbsent(parent) { mutableListOf() }
                .add(child)

            graph.computeIfAbsent(child) { mutableListOf() }
                .add(parent)
        }

        val visited = mutableSetOf<Int>()

        if (!hasCycle(graph, -1, 0, visited)) {
            return visited.size == n
        }
        return false
    }

    private fun hasCycle(
        graph: MutableMap<Int, MutableList<Int>>,
        parent: Int,
        node: Int,
        visited: MutableSet<Int>,
    ): Boolean {
        visited += node
        for (neighbor in graph[node] ?: emptyList()) {
            if (neighbor !in visited) {
                if (hasCycle(graph, node, neighbor, visited)) {
                    return true
                }
            } else if (neighbor != parent) {
                return true
            }
        }

        return false
    }
}