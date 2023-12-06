package day05

data class AlmanacMap(private val inputs: List<String>) {

    private val ranges: List<AlmanacRange> = inputs
            .takeLast(inputs.size - 1)
            .map { AlmanacRange(it) }

    fun getDestination(source: Long): Long = ranges.find { it.isSourceInSourceRange(source) }?.getDestinationFromSource(source) ?: source
}