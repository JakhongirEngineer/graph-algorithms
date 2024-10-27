package dfs

class TimeNeededToInformAllEmployees {
    fun numOfMinutes(n: Int, headID: Int, manager: IntArray, informTime: IntArray): Int {
        return dfs(n, headID, manager, informTime)
    }

    private fun dfs(n: Int, headID: Int, manager: IntArray, informTime: IntArray): Int {
        val graph = mutableMapOf<Int, MutableList<Int>>()
        manager.forEachIndexed { emp, man ->
            if (man != -1) {
                graph.computeIfAbsent(man) {
                    mutableListOf()
                }.add(emp)
            }
        }
        val visited = mutableSetOf(headID)
        val stack = ArrayDeque<Pair<Int, Int>>()
        stack.addLast(headID to 0)
        var totalTime = 0
        while (stack.isNotEmpty()) {
            val (emp, currTime) = stack.removeLast()
            totalTime = maxOf(totalTime, currTime)
            for (next in graph[emp] ?: emptyList()) {
                if (next !in visited) {
                    stack.addLast(next to (currTime + informTime[emp]))
                }
            }
        }
        return totalTime
    }
}