package program1;

import java.util.Stack;

public final class LadderBuilder {
	
	private int length;
	private Stack<String> words;
	private int dictionarySize;
	private String start;
	private String end;
	
	public LadderBuilder() {
		this.length = 0;
		this.dictionarySize = 0;
		this.start = null;
		this.end = null;
		this.words = new Stack<String>();
	}
	
	public void construct(String start, Vertex<String> end, Graph<String> g) {
		this.start = start;
		this.end = end.getData();
		this.dictionarySize = g.size();
		Vertex<String> word = end;
		while (!word.getData().equals(start)) {
			words.push(word.getData());
			length++;
			word = g.getVertex(word.getPredecessor());
		}
		words.push(start);
	}
	
	public int getLength() {
		return this.length;
	}
	
	public int getDictionarySize() {
		return this.dictionarySize;
	}
	
	public String getStartWord() {
		return this.start;
	}
	
	public String getEndWord() {
		return this.end;
	}
	
	public void printLader() {
		if (this.words == null) {
			System.out.println("no word ladder exists");
			return;
		};
		System.out.println("size of dictionary = "+ this.getDictionarySize());
		System.out.println("word = "+ this.getStartWord());
		System.out.println("word = "+ this.getEndWord());
		System.out.println("minimum path distance "+this.getLength());
		while (!this.words.isEmpty()) {
			System.out.println("\t" + words.pop());
		}
	}
}
