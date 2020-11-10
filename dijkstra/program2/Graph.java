package program2;
import java.util.LinkedList;
import java.util.Queue;

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

	/**
	 visit vertex v, with predecessor index p,
	 during a depth first traversal of the graph
	*/
	private void Visit(Vertex<Type> v, int p) {
		v.setVisited(true);
		v.setPredecessor(p);
		LinkedList<AdjListNode> L = v.getAdjList();
		for (AdjListNode node : L) {
			int n = node.getVertexIndex();
			if (!vertices[n].getVisited()) {
				Visit(vertices[n], v.getIndex());
			}
		}
	}

	/**
     carry out a depth first search/traversal of the graph
	*/
	public void dfs() {
		for (Vertex<Type> v : vertices)
			v.setVisited(false);
		for (Vertex<Type> v : vertices)
			if (!v.getVisited())
				Visit(v, -1);
	}

	 //carry out a breadth first search/traversal of the graph
	 //psedocode version
	 
	public void bfs() {
		for (Vertex<Type> v : vertices) {
			v.setVisited(false);
		}
		Queue<Vertex<Type>> q = new LinkedList<Vertex<Type>>();
		for (Vertex<Type> v : vertices) {
			if (v.getVisited() != true) {
				v.setVisited(true);
				v.setPredecessor(v.getIndex());
				q.add(v);
				while (!q.isEmpty()) {
					Vertex<Type> w = q.remove();
					for (AdjListNode u : w.getAdjList()) {
						int index = u.getVertexIndex();
						if (!vertices[index].getVisited()) {
							vertices[index].setVisited(true);
							vertices[index].setPredecessor(w.getIndex());
							q.add(vertices[index]);
						}
					}
				}
			}
		}
	} 

}
