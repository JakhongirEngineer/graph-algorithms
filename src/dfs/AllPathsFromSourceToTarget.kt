package dfs

// https://leetcode.com/problems/all-paths-from-source-to-target/
class AllPathsFromSourceToTarget {
    fun allPathsSourceTarget(graph: Array<IntArray>): List<List<Int>> {
        if (graph.isEmpty()) return emptyList()

        val source = 0
        val target = graph.size - 1

        val stack = ArrayDeque<Pair<Int, MutableList<Int>>>()
        stack.addLast(source to mutableListOf())
        val res = mutableListOf<List<Int>>()
        while (stack.isNotEmpty()) {
            val (node, path) = stack.removeLast()
            path.add(node)
            if (node == target) {
                res.add(path.toList())
            } else {
                for (next in graph[node]) {
                    stack.addLast(next to path.toMutableList())
                }
            }
        }
        return res
    }
}