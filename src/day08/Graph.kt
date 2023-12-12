package day08

// Base classes for Graph obtained from Kodeco
// https://www.kodeco.com/books/data-structures-algorithms-in-kotlin/v2.0/chapters/19-graphs
interface Graph<T : Any> {

    fun createVertex(data: T): Vertex<T>

    fun addDirectedEdge(source: Vertex<T>, destination: Vertex<T>, weight: Double?)

    fun addUndirectedEdge(source: Vertex<T>, destination: Vertex<T>, weight: Double?)

    fun add(edge: EdgeType, source: Vertex<T>, destination: Vertex<T>, weight: Double?)

    fun edges(source: Vertex<T>): ArrayList<Edge<T>>

    fun weight(source: Vertex<T>, destination: Vertex<T>): Double?

}

enum class EdgeType {
    DIRECTED, UNDIRECTED
}

data class Vertex<T : Any>(val index: Int, val data: T)

data class Edge<T : Any>(val source: Vertex<T>, val destination: Vertex<T>, val weight: Double? = null)