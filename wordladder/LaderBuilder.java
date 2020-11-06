
import java.util.Stack;

public final class LaderBuilder {
	
	private int length;
	private Stack<String> words;
	
	public LaderBuilder() {
		this.length = 0;
		this.words = new Stack<String>();
	}
	
	public void construct(String start, Vertex<String> end, Graph<String> g) {
		Vertex<String> word = end;
		while (!word.getData().equals(start)) {
			words.push(word.getData());
			length++;
			word = g.getVertex(word.getPredecessor());
		}
		words.push(start);
		length++;
	}
	
	public int getLength() {
		return this.length;
	}
	
	public void printLader() {
		if (this.words == null) return;
		while (!this.words.isEmpty()) {
			System.out.print(words.pop() + "->");
		}
		System.out.println(length);
	}
}
