package bfs

private class Graph(
    val adjacencyList: MutableMap<Int, MutableList<Int>> = mutableMapOf()
) {
    fun addVertex(vertex: Int) {
        adjacencyList.putIfAbsent(vertex, mutableListOf())
    }

    fun addEdge(vertex1: Int, vertex2: Int) {
        adjacencyList.computeIfAbsent(vertex1) { mutableListOf() }.add(vertex2)
        adjacencyList.computeIfAbsent(vertex2) { mutableListOf() }.add(vertex1)
    }

    fun printBFS(startWith: Int) {
        requireNotNull(adjacencyList[startWith])
        val visited = mutableSetOf<Int>()
        val queue = ArrayDeque<Int>()
        visited.add(startWith)
        queue.addLast(startWith)

        while (queue.isNotEmpty()) {
            val currentVertex = queue.removeFirst()
            println("currentVertex=$currentVertex")

            adjacencyList[currentVertex]?.forEach {
                if (!visited.contains(it)) {
                    visited.add(it)
                    queue.addLast(it)
                }
            }
        }
    }
}

fun main() {
    val graph = Graph()
    graph.addEdge(0, 1)
    graph.addEdge(0, 3)
    graph.addEdge(0, 2)
    graph.addEdge(1, 2)
    graph.addEdge(2, 4)

    graph.printBFS(0)
}