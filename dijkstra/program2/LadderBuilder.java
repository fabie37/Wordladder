package program2;

import java.util.Stack;

public final class LadderBuilder {
	
	private int length;
	private Stack<String> words;
	
	public LadderBuilder() {
		this.length = 0;
		this.words = new Stack<String>();
	}
	
	public void construct(String start, Vertex<String> end, Graph<String> g) {
		Vertex<String> word = end;
		while (!word.getData().equals(start)) {
			words.push(word.getData());
			length += word.getDistanceFromPred();
			word = g.getVertex(word.getPredecessor());
		}
		words.push(start);
	}
	
	public int getLength() {
		return this.length;
	}
	
	public void printLader() {
		if (this.words == null) {
			System.out.println("no word ladder exists");
			return;
		};
		
		System.out.println("shortest word ladder of length "+this.getLength());
		while (!this.words.isEmpty()) {
			System.out.println("\t" + words.pop());
		}
	}
}
