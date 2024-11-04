package dfs

class CriticalConnections {
    fun criticalConnections(n: Int, connections: List<List<Int>>): List<List<Int>> {
        // Build the graph
        val graph = Array(n) { mutableListOf<Int>() }
        for ((u, v) in connections) {
            graph[u].add(v)
            graph[v].add(u)
        }

        // Initialize discovery and low arrays
        val discovery = IntArray(n) { -1 }
        val low = IntArray(n) { -1 }
        var time = 0
        val result = mutableListOf<List<Int>>()

        // Explicit stack for DFS
        val stack = ArrayDeque<Triple<Int, Int, Iterator<Int>>>()

        // Perform DFS for each unvisited node
        for (i in 0..<n) {
            if (discovery[i] == -1) {
                stack.addLast(Triple(i, -1, graph[i].iterator()))
                discovery[i] = time
                low[i] = time
                time++

                while (stack.isNotEmpty()) {
                    val (node, parent, neighbors) = stack.last()

                    if (neighbors.hasNext()) {
                        val neighbor = neighbors.next()

                        if (neighbor == parent) continue // Skip parent edge

                        if (discovery[neighbor] == -1) {
                            // Unvisited neighbor, push to stack
                            discovery[neighbor] = time
                            low[neighbor] = time
                            time++
                            stack.addLast(Triple(neighbor, node, graph[neighbor].iterator()))
                        } else {
                            // Back edge, update low value
                            low[node] = minOf(low[node], discovery[neighbor])
                        }
                    } else {
                        stack.removeLast()
                        if (parent != -1) {
                            low[parent] = minOf(low[parent], low[node])
                            if (low[node] > discovery[parent]) {
                                result.add(listOf(parent, node))
                            }
                        }
                    }
                }
            }
        }

        return result
    }
}