package program2;
import java.util.LinkedList;


/**
 class to represent a vertex in a graph
*/
public class Vertex<Type> {
   
    private LinkedList<AdjListNode> adjList ; // the adjacency list of the vertex 
    private int index; // the index of the vertex
    private Type data;
    // possibly other fields, for example representing data
    // stored at the node, whether the vertex has been visited
    // in a traversal, its predecessor in such a traversal, etc.
    
    boolean visited; // whether vertex has been visited in a traversal
    int predecessor; // index of predecessor vertex in a traversal
    int distanceFromPred; // distance from predecessor

    /**
	 creates a new instance of Vertex
	*/
    public Vertex(int n) {
    	adjList = new LinkedList<AdjListNode>();
    	index = n;
    	visited = false;
    	this.data = null;
    }
    
    /**
	 copy constructor
	*/
    public Vertex(Vertex<Type> v){
    	adjList = v.getAdjList();
    	index = v.getIndex();
    	visited = v.getVisited();
    	data = v.getData();
    }
     
    public LinkedList<AdjListNode> getAdjList(){
        return adjList;
    }
    
    public int getIndex(){
    	return index;
    }
    
    public void setIndex(int n){
    	index = n;
    }
    
    public boolean getVisited(){
    	return visited;
    }
    
    public void setVisited(boolean b){
    	visited = b;
    }
    
    public int getPredecessor(){
    	return predecessor;
    }
    
    public void setPredecessor(int n){
    	predecessor = n;
    }
    
    public Type getData() {
    	return this.data;
    }
    
    public void setData(Type data) {
    	this.data = data;
    }
    
    public int getDistanceFromPred() {
    	return this.distanceFromPred;
    }
    
    public void setDistanceFromPred(int d) {
    	this.distanceFromPred = d;
    }
    
    public void addToAdjList(int n){
        adjList.addLast(new AdjListNode(n));
    }
    
    public void addToAdjList(int n, int weight){
        adjList.addLast(new AdjListNode(n, weight));
    }
    
    public int vertexDegree(){
        return adjList.size();
    }
}
