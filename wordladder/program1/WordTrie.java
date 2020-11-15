package program1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class WordTrie extends TrieTree<String> {
	
	List<String> wordlist;
	int wordcount;
	
	
	public WordTrie() {
		super();
		this.wordlist = new ArrayList<String>();
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
	
	public void deleteWord(String word) {
		TrieNode root = this.getRoot();
		int index = 0;
		deleteWordRecurssive(root, word, index);
		this.wordcount--;
	}
	
	public TrieNode deleteWordRecurssive(TrieNode node, String word, int index) {
		// Base Case: If child has no children or is the word itself
		if (node == null) {
			return null;
		}
		
		if (index-1 == word.length() - 1) {
			if (!node.children.isEmpty()) {
				node.setData(null);
				node = null;
			} 
			return node;
		}
		
		node.children.remove(String.valueOf(word.charAt(index)), deleteWordRecurssive(node.getChild(String.valueOf(word.charAt(index))), word, index + 1));
		
		if (node.children.isEmpty() && node.getData() == null) {
			return node;
		}
		return null;
		
	}
	
	public int getWordCount() {
		return this.wordcount;
	}
	
	public List<String> getWordList() {
		return this.wordlist;
	}
	
	// Get adj strings 
	public List<String> getAdjStrings(String word) {
		List<String> list = new ArrayList<String>();
		for (int cursor = 0; cursor < word.length(); cursor++) {
			fetch(list, word, 0, cursor, this.getRoot());
		}
		this.deleteWord(word);
		return list;
	}
	
	public void fetch(List<String> l, String word, int mypos, int cursor, TrieNode node) {
		if (mypos == cursor) {
			for (TrieNode m : node.getChildren().values()) {
				if (!String.valueOf(word.charAt(cursor)).equals(m.getIndex())) {
					fetch(l, word, mypos+1, cursor, m);
				}
			}
		} else {
			if (mypos > word.length()) {
				return; 
			} else if (mypos == word.length() && node.getData() != word) {
				l.add(node.getData());
				return;
			} else if (node.getChild(String.valueOf(word.charAt(mypos))) != null) {
				TrieNode nextNode = node.getChild(String.valueOf(word.charAt(mypos)));
				fetch(l, word, mypos+1, cursor, nextNode);
			} else {
				return;
			}
		}
	}
}
