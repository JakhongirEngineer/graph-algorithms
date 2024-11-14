package graphTheory

class TownJudge {
    fun findJudge(n: Int, trust: Array<IntArray>): Int {
        // node -> {0..n-1}
        // 1 - 3, 4
        // 2 - 3, 4
        // 3 -
        // 4 - 3

        // everyone should trust -> every node except itself has en edge with it.
        // inDegree of it = n - 1
        // it does not have neighbor

        val graph = mutableMapOf<Int, MutableList<Int>>()
        val inDegree = IntArray(n + 1)

        for((trustee, truster) in trust) {
            graph.computeIfAbsent(trustee) { mutableListOf() }
                .add(truster)

            inDegree[trustee]++
            inDegree[truster]++
        }

        for (i in 1..n) {
            if (graph[i] == null) {
                if (inDegree[i] == n - 1) return i
            }
        }

        return -1
    }
}