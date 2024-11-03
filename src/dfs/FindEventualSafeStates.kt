package dfs

class FindEventualSafeStates {
    fun eventualSafeNodes(graph: Array<IntArray>): List<Int> {
        val N = graph.size
        // states:
        //  0 - unvisited
        //  1 - visiting
        //  2 - completed visiting, and it is safe, meaning that it is not in the circle.
        val state = IntArray(N)
        val res = mutableListOf<Int>()

        for(i in 0..<N) {
            if(dfs(graph, state, i)) {
                res.add(i)
            }
        }
        return res
    }

    private fun dfs(graph: Array<IntArray>, state: IntArray, node: Int): Boolean {
        // node is being visited, indicating that it is in the circle
        if(state[node] == 1) {
            return false
        }
        // node has already been determined to be a safe node, meaning that it is not in the circle
        if(state[node] == 2) {
            return true
        }
        // make node visiting
        state[node] = 1

        for(next in graph[node]) {
            if(dfs(graph, state, next).not()) {
                return false
            }
        }
        state[node] = 2
        return true
    }
}