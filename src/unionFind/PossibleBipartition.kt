package unionFind

class PossibleBipartition {

    private class UnionFind(
        private val size: Int,
    ) {
        private val parents = IntArray(size) { it }
        private val ranks = IntArray(size) { 1 }

        fun find(i: Int): Int {
            if (parents[i] != i) {
                parents[i] = find(parents[i])
            }
            return parents[i]
        }

        fun union(i: Int, j: Int) {
            val iRoot = find(i)
            val jRoot = find(j)

            if (iRoot == jRoot) {
                return
            }

            if (ranks[iRoot] > ranks[jRoot]) {
                parents[jRoot] = iRoot
            } else if (ranks[iRoot] < ranks[jRoot]) {
                parents[iRoot] = jRoot
            } else {
                parents[iRoot] = jRoot
                ranks[jRoot]++
            }
        }
    }

    fun possibleBipartition(n: Int, dislikes: Array<IntArray>): Boolean {
        val uf = UnionFind(n + 1)

        val graph = mutableMapOf<Int, MutableList<Int>>()

        for ((from, to) in dislikes) {
            graph.computeIfAbsent(from) {
                mutableListOf()
            }.add(to)

            graph.computeIfAbsent(to) {
                mutableListOf()
            }.add(from)
        }

        for (node in 1..n) {
            if (graph[node] == null) continue

            for (neighbor in graph[node]!!) {
                if (uf.find(neighbor) == uf.find(node)) {
                    return false
                }
                uf.union(graph[node]!![0], neighbor)
            }
        }
        return true
    }

}