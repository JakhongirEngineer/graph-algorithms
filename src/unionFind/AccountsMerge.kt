package unionFind

class AccountsMerge {
    private class UnionFind(
        private val size: Int,
    ) {
        private val parents: IntArray = IntArray(size) { it }
        private val ranks: IntArray = IntArray(size) { 1 }

        fun find(node: Int): Int {
            if (parents[node] != node) {
                parents[node] = find(parents[node])
            }
            return parents[node]
        }

        fun union(node1: Int, node2: Int) {
            val root1 = find(node1)
            val root2 = find(node2)

            if (root1 == root2) {
                return
            }

            if (ranks[root1] < ranks[root2]) {
                parents[root1] = root2
            } else if (ranks[root2] < ranks[root1]) {
                parents[root2] = root1
            } else {
                parents[root2] = root1
                ranks[root1]++
            }
        }
    }

    fun accountsMerge(accounts: List<List<String>>): List<List<String>> {
        val uf = UnionFind(accounts.size)

        val emailToAccount = mutableMapOf<String, Int>()

        for (accountId in accounts.indices) {
            for (i in 1..<accounts[accountId].size) {
                val email = accounts[accountId][i]
                if (emailToAccount.containsKey(email)) {
                    uf.union(emailToAccount[email]!!, accountId)
                } else {
                    emailToAccount[email] = accountId
                }
            }
        }

        val components = mutableMapOf<Int, MutableList<String>>()

        for ((email, accountId) in emailToAccount) {
            val rootAccountId = uf.find(accountId)
            components.computeIfAbsent(rootAccountId) {
                mutableListOf()
            }.add(email)
        }

        val result = mutableListOf<List<String>>()

        for ((accountId, emails) in components) {
            val accountName = accounts[accountId][0]
            result.add(listOf(accountName) + emails.sorted())
        }
        return result
    }

}