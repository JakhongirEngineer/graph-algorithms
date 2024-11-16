package graphTheory

class MaxNetworkRank {
    fun maximalNetworkRank(n: Int, roads: Array<IntArray>): Int {
        val degrees = IntArray(n)
        val connections = mutableSetOf<String>()

        for ((a, b) in roads) {
            degrees[a]++
            degrees[b]++
            connections.add("$a:$b")
            connections.add("$b:$a")
        }
        var res = -1
        for (i in 0..<n) {
            for (j in i+1..<n) {
                val curr = if (connections.contains("$i:$j")) {
                    degrees[i] + degrees[j] - 1
                } else {
                    degrees[i] + degrees[j]
                }
                res = maxOf(res, curr)
            }
        }
        return res
    }
}