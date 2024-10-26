package bfs

// https://leetcode.com/problems/bus-routes/description/
class BussRoutes {

    fun numBusesToDestination(
        routes: Array<IntArray>,
        source: Int,
        target: Int
    ): Int {
        if (source == target) {
            return 0
        }

        val stopToBusses = mutableMapOf<Int, MutableList<Int>>()
        routes.forEachIndexed { busNumber, route ->
            route.forEach { stop ->
                stopToBusses.computeIfAbsent(stop) { mutableListOf() }.add(busNumber)
            }
        }

        val visitedStops = mutableSetOf<Int>()
        val usedBuses = mutableSetOf<Int>()

        val queue = ArrayDeque<Pair<Int, Int>>()
        queue.addLast(source to 0)
        visitedStops.add(source)

        while (queue.isNotEmpty()) {
            val (stop, busses) = queue.removeFirst()

            for (bus in stopToBusses[stop] ?: emptyList()) {
                if(usedBuses.contains(bus)) {
                    continue
                }
                usedBuses.add(bus)

                for (nextStop in routes[bus]) {
                    if (nextStop == target) {
                        return busses + 1
                    }
                    if (!visitedStops.contains(nextStop)) {
                        visitedStops.add(nextStop)
                        queue.addLast(nextStop to busses + 1)
                    }
                }

            }

        }
        return -1
    }
}