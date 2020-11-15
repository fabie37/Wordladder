package program2;

/**
 class to represent an undirected graph using adjacency lists
 */
public class Graph<Type> {

	protected Vertex<Type>[] vertices; // the (array of) vertices
	protected int numVertices = 0; // number of vertices

	// possibly other fields representing properties of the graph

	/** 
	 creates a new instance of Graph with n vertices
	*/
	@SuppressWarnings("unchecked")
	public Graph(int n) {
		numVertices = n;
		vertices = (Vertex<Type>[]) new Vertex[n];
		for (int i = 0; i < n; i++)
			vertices[i] = new Vertex<Type>(i);
	}

	public int size() {
		return numVertices;
	}

	public Vertex<Type> getVertex(int i) {
		return vertices[i];
	}

	public void setVertex(int i) {
		vertices[i] = new Vertex<Type>(i);
	}



}
