package bfs

class MinimumGeneticMutation {
    fun minMutation(startGene: String, endGene: String, bank: Array<String>): Int {
        // val choices = listOf('A', 'C', 'G', 'T')
        val queue = ArrayDeque<String>()
        val seen = mutableSetOf<String>()
        queue.addLast(startGene)
        seen.add(startGene)
        var level = 0
        while (queue.isNotEmpty()) {
            repeat(queue.size) {
                val gene = queue.removeFirst()
                if (gene == endGene) {
                    return level
                }
                for (neighborGene in getNeighborGenes(bank, seen, gene)) {
                    seen.add(neighborGene)
                    queue.addLast(neighborGene)
                }
            }
            level++
        }
        return -1
    }

    private fun getNeighborGenes(bank: Array<String>, seen: Set<String>, gene: String): List<String> {
        return bank.filter { !seen.contains(it) }.filter { gene.hasOneMutationWith(it) }
    }

    private fun String.hasOneMutationWith(another: String): Boolean {
        if (this.length != another.length) {
            return false
        }
        var mutatedOnce = false
        for ((idx, ch) in this.withIndex()) {
            if (ch != another[idx] && !mutatedOnce) {
                mutatedOnce = true
            } else if (ch != another[idx] && mutatedOnce) {
                return false
            }
        }
        return true
    }
}