class UnionFind(
    private val size: Int,
) {
    private val parent: IntArray = IntArray(size) { it } // Each element starts as its own parent
    private val rank: IntArray = IntArray(size) { 1 }   // Initialize rank to 1 for all elements

    /**
     * Finds the representative (root) of the set containing element `i`.
     * Uses path compression for optimization.
     */
    fun find(i: Int): Int {
        require(i in 0..<size) { "Index $i is out of bounds (0 to ${size - 1})" }
        if (parent[i] != i) {
            parent[i] = find(parent[i]) // Path compression
        }
        return parent[i]
    }

    /**
     * Unites the sets containing elements `i` and `j`.
     * Uses union by rank for optimization.
     */
    fun union(i: Int, j: Int) {
        require(i in 0..<size && j in 0..<size) { "Indices $i and/or $j are out of bounds (0 to ${size - 1})" }
        val iRoot = find(i)
        val jRoot = find(j)
        if (iRoot == jRoot) {
            return // Already in the same set
        }

        // Union by rank
        if (rank[iRoot] < rank[jRoot]) {
            parent[iRoot] = jRoot
        } else if (rank[iRoot] > rank[jRoot]) {
            parent[jRoot] = iRoot
        } else {
            parent[jRoot] = iRoot
            rank[iRoot]++
        }
    }

    /**
     * Checks if elements `i` and `j` are in the same set.
     */
    fun connected(i: Int, j: Int): Boolean {
        require(i in 0..<size && j in 0..<size) { "Indices $i and/or $j are out of bounds (0 to ${size - 1})" }
        return find(i) == find(j)
    }

    /**
     * Returns the number of connected components in the UnionFind structure.
     */
    fun componentCount(): Int {
        return parent.indices.count { it == parent[it] }
    }
}
