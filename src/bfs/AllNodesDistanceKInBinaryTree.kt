package bfs

class AllNodesDistanceKInBinaryTree {
    class TreeNode(var `val`: Int = 0) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
    // https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
    // step1: build graph
    // step2: use BFS to visit level by level manner starting from the target node
    // time and space: O(N)
    fun distanceK(root: TreeNode?, target: TreeNode?, k: Int): List<Int> {
        if (root == null || target == null) return emptyList()

        val graph = mutableMapOf<Int, MutableList<Int>>()
        inOrder(root, graph)

        val queue = ArrayDeque<Int>()
        val seen = mutableSetOf<Int>()
        queue.addLast(target.`val`)
        seen.add(target.`val`)
        var currLevel = 0
        while (queue.isNotEmpty()) {
            if (currLevel == k) {
                return queue.toList()
            }
            repeat(queue.size) {
                val vertex = queue.removeFirst()

                val neighbors = graph[vertex] ?: emptyList()
                for (neighbor in neighbors) {
                    if (!seen.contains(neighbor)) {
                        queue.addLast(neighbor)
                        seen.add(neighbor)
                    }
                }
            }
            currLevel++
        }
        return emptyList()
    }

    private fun inOrder(root: TreeNode?, graph: MutableMap<Int, MutableList<Int>>) {
        if (root == null) return
        if (root.left != null) {
            graph[root.`val`] = graph.getOrDefault(root.`val`, mutableListOf()).apply { add(root.left!!.`val`) }
            graph[root.left!!.`val`] = graph.getOrDefault(root.left!!.`val`, mutableListOf()).apply { add(root.`val`) }
            inOrder(root.left, graph)
        }
        if (root.right != null) {
            graph[root.`val`] = graph.getOrDefault(root.`val`, mutableListOf()).apply { add(root.right!!.`val`) }
            graph[root.right!!.`val`] = graph.getOrDefault(root.right!!.`val`, mutableListOf()).apply { add(root.`val`) }
            inOrder(root.right, graph)
        }
    }
}



