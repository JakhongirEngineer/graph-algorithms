package unionFind

class IsGraphBarpartite {

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

            if (ranks[iRoot] < ranks[jRoot]) {
                parents[iRoot] = jRoot
            } else if (ranks[jRoot] < ranks[iRoot]) {
                parents[jRoot] = iRoot
            } else {
                parents[jRoot] = iRoot
                ranks[iRoot]++
            }
        }
    }


    fun isBipartite(graph: Array<IntArray>): Boolean {
        val uf = UnionFind(graph.size)

        for (node in graph.indices) {
            for (neighbor in graph[node]) {
                if (uf.find(neighbor) == uf.find(node)) {
                    return false
                }
                uf.union(graph[node][0], neighbor)
            }
        }
        return true
    }
}