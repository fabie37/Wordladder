package program2;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;

import javax.swing.event.ListSelectionEvent;

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
		
		if (table.get(start) == null || table.get(end) == null) {
			return null;
		}
		
		// Table to record Dijkstra search 
		HashMap<Integer, Integer> distanceMap = new HashMap<Integer, Integer>();
		PriorityQueue<AdjListNode> queue = new PriorityQueue<AdjListNode>(new AdjListNodeComparator());
		
		// Set up base cases
		
		for (Vertex<String> v : vertices) {
			v.setVisited(false);
			v.setPredecessor(-1);
			v.setDistanceFromPred(-1);
			distanceMap.put(v.getIndex(), null);
		}
		
		// Make start vertex have distance of 0
		distanceMap.put(this.getVertex(start).getIndex(), 0);
		this.getVertex(start).setVisited(true);
		
		// Populate queue with first vertices
		for (AdjListNode n : this.getVertex(start).getAdjList()) {
			queue.add(n);
			distanceMap.put(n.getVertexIndex(), n.getWeight());
			this.getVertex(n.getVertexIndex()).setDistanceFromPred(n.getWeight());
			this.getVertex(n.getVertexIndex()).setPredecessor(this.getVertex(start).getIndex());
		}
		
		// Start the search
		while (!queue.isEmpty()) {
			Vertex<String> v = this.getVertex(queue.poll().getVertexIndex());
			if (!v.getVisited()) {
				v.setVisited(true);
				for (AdjListNode n : v.getAdjList()) {
					int newWeight = distanceMap.get(v.getIndex()) + n.getWeight();
					if (distanceMap.get(n.getVertexIndex()) == null || newWeight < distanceMap.get(n.getVertexIndex())) {
						distanceMap.put(n.getVertexIndex(), newWeight);
						this.getVertex(n.getVertexIndex()).setDistanceFromPred(n.getWeight());
						this.getVertex(n.getVertexIndex()).setPredecessor(v.getIndex());
						queue.add(new AdjListNode(n.getVertexIndex(), newWeight));
					}
					
				}
			}
		}
		
		if (distanceMap.get(this.getVertex(end).getIndex()) == null) {
			return null;
		} else {
			return this.getVertex(end);
		}
	} 
	
	public void build(WordTrie trie) {
		
		int limit = 1;
		
		for (String s: trie.getWordList()) {
			HashMap<String, Integer> results  = trie.getWeightedAdjStrings(s);
			for (Entry<String, Integer> entry : results.entrySet()) {
				this.getVertex(entry.getKey()).addToAdjList(this.table.get(s), entry.getValue());
				this.getVertex(s).addToAdjList(this.table.get(entry.getKey()), entry.getValue());
			}
		} 
	}
	
	public void printDictionary() {
		for (Vertex<String> v : this.vertices) {
			System.out.print(v.getData() + " has children { ");
			int sum = 0;
			for (char c : v.getData().toCharArray()) {
				sum += (int) c;
			}
			for (AdjListNode n : v.getAdjList()) {
				int sum2 = 0;
				for (char c : this.getVertex(n.getVertexIndex()).getData().toCharArray()) {
					sum2 += (int) c;
				}
				System.out.print(this.getVertex(n.getVertexIndex()).getData() + ":" + n.getWeight() + ":" + Math.abs(sum-sum2) + " ");
			}
			System.out.print("} \n");
		}
	}
	

}
