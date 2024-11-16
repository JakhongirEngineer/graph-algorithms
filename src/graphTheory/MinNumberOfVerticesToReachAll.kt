package graphTheory

class MinNumberOfVerticesToReachAll {
    fun findSmallestSetOfVertices(n: Int, edges: List<List<Int>>): List<Int> {
        val inDegree = IntArray(n)

        for((from, to) in edges) {
            inDegree[to]++
        }

        return inDegree
            .mapIndexed { index, value -> if (value == 0) index else -1 }
            .filter { it != -1 }
    }
}