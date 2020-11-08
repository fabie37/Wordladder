import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class WordTrie extends TrieTree<String> {
	
	List<String> wordlist;
	HashMap<String, Boolean> isFound; 
	int wordcount;
	
	
	public WordTrie() {
		super();
		this.wordlist = new ArrayList<String>();
		this.isFound = new HashMap<String, Boolean>();
		this.wordcount = 0;
	}
	
	public void addWord(String word) {
		TrieNode n = this.getRoot();
		for (Character c : word.toCharArray()) {
			if (n.getChild(c.toString()) == null) {
				n.addChild(c.toString());
			}
			n = n.getChild(c.toString());
		}
		n.setData(word);
		this.wordcount++;
		this.wordlist.add(word);
	}
	
	public int getWordCount() {
		return this.wordcount;
	}
	
	public List<String> getWordList() {
		return this.wordlist;
	}
	
	// Get strings which have a edit distance of limit to a word
	public List<String> getLevenstienDistances(String word, int limit) {
		List<String> adjWords = new ArrayList<String>();
		Stack<TrieNode> nodeStack = new Stack<TrieNode>();
		Stack<Integer> heightStack = new Stack<Integer>();
		Stack<Integer[]> previousColStack = new Stack<Integer[]>();
		
		// Base Case: check if trie is empty
		if (this.getRoot().children.isEmpty()) { return null; } 
		
		// Construct the column from null string to word
		Integer[] wordColumn = new Integer[word.length()+1]; 
		for (int i=0; i<word.length()+1; i++) { wordColumn[i] = i; }
		
		// Add this column and root node to current node, set height as 0
		previousColStack.add(wordColumn);
		for (TrieNode n : this.getRoot().children.values()) {
			nodeStack.add(n);
			heightStack.add(1);
		}
		
		// Iterate through trie
		while (!nodeStack.isEmpty()) {
			// Get the current node and start a new column
			TrieNode currentNode = nodeStack.pop();
			Integer height = heightStack.pop();
			Integer[] newCol = new Integer[word.length()+1];
			newCol[0] = height;
			
			// Calculate the new edit distance column
			for (int i=1; i<word.length()+1; i++) {
				int insertionCost = newCol[i-1] + 1;
				int deletionCost = previousColStack.peek()[i] + 1;
				int subCost;
				if (String.valueOf(word.charAt(i-1)).equals(currentNode.getIndex())) {
					subCost = previousColStack.peek()[i-1];
				} else {
					subCost = previousColStack.peek()[i-1] + 1;
				}
				
				newCol[i] = Math.min(insertionCost, Math.min(deletionCost, subCost));

			}
			
			// If node is a word and if edit distance is the limit, add to returned list
			if (newCol[word.length()] == limit && currentNode.getData() != null) {
				adjWords.add(currentNode.getData());
			}
			
			// Check if current node has any children
			if (!currentNode.children.isEmpty() && Collections.min(Arrays.asList(newCol)) <= limit) {
				Integer newHeight = height + 1;
				previousColStack.add(newCol);
				for (TrieNode n : currentNode.children.values()) {
					nodeStack.add(n);
					heightStack.add(newHeight);
				}
			} else {
				if (!nodeStack.isEmpty() && !heightStack.isEmpty()) {
					for (int i=height; i > heightStack.peek(); i--) {
						previousColStack.pop();
					}
				}
			}
		}
		return adjWords;
	}
	
}
