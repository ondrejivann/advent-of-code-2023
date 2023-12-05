package day04

typealias Visitor = (ScratchcardTreeNode) -> Unit

data class ScratchcardTreeNode(val value: Scratchcard) {

    private val children: MutableList<ScratchcardTreeNode> = mutableListOf()
    private fun addAll(childrenToAdd: List<ScratchcardTreeNode>) = children.addAll(childrenToAdd)

    fun createTree(scratchcards: List<Scratchcard>): ScratchcardTreeNode {
        val children = getChildren(scratchcards)
        addAll(children)
        children.forEach { child -> child.createTree(scratchcards) }
        return this
    }

    private fun getChildren(
            scratchcards: List<Scratchcard>,
    ): List<ScratchcardTreeNode> {
        val indexOfScratchCard = scratchcards.indexOf(value)
        val nextIndex = indexOfScratchCard + 1
        val winScratchcards = scratchcards.subList(
                fromIndex = nextIndex.coerceAtMost(scratchcards.size),
                toIndex = (nextIndex + value.matchedNumbersCount).coerceAtMost(scratchcards.size)
        )

        return winScratchcards.map { ScratchcardTreeNode(it) }
    }

    fun forEachDepthFirst(visit: Visitor) {
        visit(this)
        children.forEach {
            it.forEachDepthFirst(visit)
        }
    }
}