package program2;

/**
 class to represent an entry in the adjacency list of a vertex
 in a graph
 */
public class AdjListNode {

	private int vertexIndex;
	private int weight;
	// could be other fields, for example representing
	// properties of the edge - weight, capacity, ...
	
    /* creates a new instance */
	public AdjListNode(int n){
		this.vertexIndex = n;
		this.weight  = 0;
	}
	
	public AdjListNode(int n, int weight) {
		this.vertexIndex = n;
		this.weight = weight;
	}
	
	public AdjListNode(AdjListNode n) {
		this(n.getVertexIndex(),n.getWeight());
	}
	
	public int getWeight() {
		return this.weight;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public int getVertexIndex(){
		return vertexIndex;
	}
	
	public void setVertexIndex(int n){
		vertexIndex = n;
	}
	
}
