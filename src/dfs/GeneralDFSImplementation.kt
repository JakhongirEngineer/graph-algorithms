package dfs

fun main() {
    Graph(
        mapOf(
            0 to mutableListOf(1,2,3),
            1 to mutableListOf(2,3,4),
            2 to mutableListOf(0,1),
            3 to mutableListOf(2, 4),
            4 to mutableListOf(1)
        )
    ).dfsRecursive(0) {
        println("Node=$it")
    }
}

private class Graph(
    val adjacencyList: Map<Int, MutableList<Int>> = emptyMap()
) {
    fun dfsRecursive(start: Int, visit: (Int) -> Unit) {
        val visited = mutableSetOf<Int>()
        val stack = ArrayDeque<Int>()
        visited.add(start)
        stack.addLast(start)

        while (stack.isNotEmpty()) {
            val curr = stack.removeLast()
            visit(curr)
            for (node in adjacencyList[curr] ?: emptyList()) {
                if (node !in visited) {
                    visited.add(node)
                    stack.addFirst(node)
                }
            }
        }
    }
}