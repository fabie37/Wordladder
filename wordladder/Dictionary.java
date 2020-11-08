import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Dictionary extends Graph<String>{

	private HashMap<String, Integer> table;
	
	public Dictionary(int n) {
		super(n);
		this.table = new HashMap<String, Integer>();
	}
	
	public Vertex<String> getVertex(String word) {
		return (table.get(word)) != null ? this.getVertex(table.get(word)) : null;
	}
	
	public void setVertex(String word, int i) {
		table.put(word, i);
		this.setVertex(i);
		this.getVertex(i).setData(word);
	}
	
	public Vertex<String> search(String start, String end) {
		for (Vertex<String> v : vertices) {
			v.setVisited(false);
		}
		Queue<Vertex<String>> q = new LinkedList<Vertex<String>>();
		Vertex<String> v = this.vertices[table.get(start)];
		if (v.getVisited() != true) {
			v.setVisited(true);
			v.setPredecessor(v.getIndex());
			q.add(v);
			while (!q.isEmpty()) {
				Vertex<String> w = q.remove();
				for (AdjListNode u : w.getAdjList()) {
					int index = u.getVertexIndex();
					if (!vertices[index].getVisited()) {
						vertices[index].setVisited(true);
						vertices[index].setPredecessor(w.getIndex());
						q.add(vertices[index]);
					}
					if (end.equals(vertices[index].getData())) {
						return vertices[index];
					}
				}
			}
		}
		return null;
	} 
	
	public void build() {
		
		int limit = 1;
		
		for (int i=0; i<this.size(); i++) {
			for (int j=0; j<this.size(); j++) {
				int dist = LevenstienDistance.getDistance(this.getVertex(i).getData(), this.getVertex(j).getData(), limit);
				if (dist == 1) {
					this.getVertex(i).addToAdjList(j);
				}
			}
		}
	}
	

}
