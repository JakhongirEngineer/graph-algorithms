package dfs

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.ArrayList
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.Set
import kotlin.collections.isNotEmpty
import kotlin.collections.mutableListOf
import kotlin.collections.mutableSetOf
import kotlin.collections.plus
import kotlin.collections.plusAssign


class FindEventualSafeStates {
    fun eventualSafeNodes(graph: Array<IntArray>): List<Int> {
        val N = graph.size - 1

        val res = mutableListOf<Int>()
        for (i in 0..N) {
            if(hasCycleFrom(graph, i).not()) {
                res.add(i)
            }
        }
        return res
    }

    private fun hasCycleFrom(graph: Array<IntArray>, start: Int): Boolean {
        val visited = mutableSetOf<Int>()
        val stack = ArrayDeque<Pair<Int,Set<Int>>>()
        stack.addLast(start to mutableSetOf(start))

        while (stack.isNotEmpty()) {
            val (currNode, currPath) = stack.removeLast()
            if (currNode in visited) continue
            visited += currNode

            for (nextNode in graph[currNode]) {
                if (nextNode in currPath) {
                    return true
                }
                stack.addLast(nextNode to (currPath + nextNode))

            }
        }
        return false
    }

    fun eventualSafeNodes1(graph: Array<IntArray>): List<Int> {
        val n = graph.size - 1
        val visited = IntArray(n) // 0: unvisited, 1: visiting, -1: safe
        val result = mutableListOf<Int>()

        for (i in 0..n) {
            if (dfs(graph, visited, i)) {
                result.add(i)
            }
        }

        return result.sorted()
    }

    private fun dfsIterative(graph: Array<IntArray>, visited: IntArray, node: Int): Boolean {
        val stack = ArrayDeque<Pair<Int, List<Int>>>()
        stack.addLast(node to graph[node].toList())

        visited[node] = 1 // Mark the node as visiting

        while (stack.isNotEmpty()) {
            val (currentNode, neighbors) = stack.removeLast()

            for (nextNode in neighbors) {
                if (visited[nextNode] == -1) {
                    // Adjacent node is already marked safe
                    continue
                }
                if (visited[nextNode] == 1) {
                    // Adjacent node is part of a cycle
                    return false
                }
                // Mark nextNode as visiting and push it onto the stack
                visited[nextNode] = 1
                stack.addLast(nextNode to graph[nextNode].toList())
            }
            visited[currentNode] = -1
        }

        return true
    }

    private fun dfs(graph: Array<IntArray>, visited: IntArray, node: Int): Boolean {
        if (visited[node] == -1) return true // If node is marked as safe

        if (visited[node] == 1) return false // If node is part of a cycle


        visited[node] = 1 // Mark the node as visiting
        for (next in graph[node]) {
            if (!dfs(graph, visited, next)) {
                return false // If any adjacent node is not safe
            }
        }

        visited[node] = -1 // Mark the node as safe
        return true
    }

    private fun hasCycleFrom(graph: Array<IntArray>, visited: IntArray, node: Int): Boolean {
        val stack = ArrayDeque<Int>()

    }


}