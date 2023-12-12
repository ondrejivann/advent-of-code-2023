package day08

import lcmOfList

data class DesertNetwork(private val inputs: List<String>) {

    private val graph = AdjacencyList<DesertNode>()

    init {
        // Fill graph with vertexes
        inputs.subList(2, inputs.size).map { DesertNode(it) }.forEach { graph.createVertex(it) }

        // Add edges
        graph.vertexes.forEach { sourceVertex ->
            graph.findVertex { it.data.name == sourceVertex.data.leftName }?.let {
                graph.addDirectedEdge(sourceVertex, it, 1.0)
            }
            graph.findVertex { it.data.name == sourceVertex.data.rightName }?.let {
                graph.addDirectedEdge(sourceVertex, it, 1.0)
            }
        }
    }

    private val instructions = inputs.first().toCharArray().toList()

    private val startVertex = graph.findVertex { it.data.name == "AAA" } ?: error("Start vertex not found")

    private val endVertex = graph.findVertex { it.data.name == "ZZZ" } ?: error("End vertex not found")

    private val vertexesEndingWithA = graph.vertexes.filter { vertex -> vertex.data.name.endsWith('A') }

    private val vertexesEndingWithZ = graph.vertexes.filter { vertex -> vertex.data.name.endsWith('Z') }

    val numberOfRequiredSteps = getNumberOfRequiredSteps(
            source = startVertex,
            destinations = listOf(endVertex),
            steps = instructions,
    )

    val numberOfRequiredStepsMultipleVertexes = getNumberOfRequiredStepsMultipleVertex(
            startingVertexes = vertexesEndingWithA,
            endingVertexes = vertexesEndingWithZ,
            steps = instructions,
    )

    private fun getNumberOfRequiredStepsMultipleVertex(
            startingVertexes: List<Vertex<DesertNode>>,
            endingVertexes: List<Vertex<DesertNode>>,
            steps: List<Char>,
    ): Long = lcmOfList(startingVertexes.map { source ->
        getNumberOfRequiredSteps(
                source = source,
                destinations = endingVertexes,
                steps = steps,
        )
    })

    // This will take all your life
    private fun doNotUseThisFunction(
            startingVertexes: List<Vertex<DesertNode>>,
            steps: List<Char>,
    ): Long {
        var count = 0L
        val sources = mutableListOf<Vertex<DesertNode>>().apply {
            addAll(startingVertexes)
        }
        val destinations = mutableListOf<Vertex<DesertNode>>()

        do {
            steps.forEach { step ->
                count++
                destinations.clear()
                destinations.addAll(sources.map { source ->
                    if (step == 'R') {
                        graph.edges(source).last().destination
                    } else {
                        graph.edges(source).first().destination
                    }
                })

                if (destinations.all { it.data.name.endsWith('Z') }) {
                    return@forEach
                } else {
                    sources.clear()
                    sources.addAll(destinations)
                }
            }
        } while (!destinations.all { it.data.name.endsWith('Z') })
        return count
    }

    private fun getNumberOfRequiredSteps(
            source: Vertex<DesertNode>,
            destinations: List<Vertex<DesertNode>>,
            steps: List<Char>,
    ): Long {
        var count = 0L
        var s = source
        var d: Vertex<DesertNode>? = null
        while (d !in destinations) steps.forEach { step ->
            count++
            d = if (step == 'R') {
                graph.edges(s).last().destination
            } else {
                graph.edges(s).first().destination
            }
            if (d in destinations) {
                return@forEach
            } else {
                d?.let { s = it }
            }
        }
        return count
    }

    data class DesertNode(private val row: String) {
        val name = row.substringBefore(" ")

        val leftName = row.substringAfter("(").substringBefore(",").trim()
        val rightName = row.substringAfter(",").substringBefore(")").trim()
    }
}