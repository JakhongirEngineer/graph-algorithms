package basics

abstract class Graph(
    val adjacencyList: MutableMap<Int, MutableList<Int>>
) {
    abstract fun addVertex(vertex: Int)

    abstract fun removeVertex(vertex: Int)

    abstract fun addEdge(vertex1: Int, vertex2: Int)

    abstract fun removeEdge(vertex1: Int, vertex2: Int)

    abstract fun getVertices(): List<Int>

    abstract fun getNeighbors(vertex: Int): List<Int>

    abstract fun isAdjacent(vertex1: Int, vertex2: Int): Boolean

    abstract fun getVertexCount(): Int

    abstract fun getEdgeCount(): Int
}

class MyGraph(adjacencyList: MutableMap<Int, MutableList<Int>>): Graph(adjacencyList) {
    override fun addVertex(vertex: Int) {
        adjacencyList.putIfAbsent(vertex, mutableListOf<Int>())
    }

    override fun removeVertex(vertex: Int) {
        adjacencyList.remove(vertex)?.let {
            adjacencyList.values.forEach { it.remove(vertex) }
        }
    }

    override fun addEdge(vertex1: Int, vertex2: Int) {
        adjacencyList.computeIfAbsent(vertex1) { mutableListOf() }.add(vertex2)
        adjacencyList.computeIfAbsent(vertex2) { mutableListOf() }.add(vertex1)
    }

    override fun removeEdge(vertex1: Int, vertex2: Int) {
        adjacencyList[vertex1]?.removeIf { it == vertex2 }
        adjacencyList[vertex2]?.removeIf { it == vertex1 }
    }

    override fun getVertices(): List<Int> {
        return adjacencyList.keys.toList()
    }

    override fun getNeighbors(vertex: Int): List<Int> {
        return adjacencyList[vertex].orEmpty()
    }

    override fun isAdjacent(vertex1: Int, vertex2: Int): Boolean {
        return adjacencyList[vertex1]?.contains(vertex2) ?: false
    }

    override fun getVertexCount(): Int {
        return adjacencyList.size
    }

    override fun getEdgeCount(): Int {
        return adjacencyList.values.sumOf { it.size } / 2
    }
}
