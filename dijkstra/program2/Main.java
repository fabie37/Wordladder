package program2;
import java.io.*;
import java.util.*;

/**
 program to find word ladder with shortest distance for two words in a dictionary
 distance between elements of the word ladder is the absolute difference in the
 positions of the alphabet of the non-matching letter
 */
public class Main {

	public static void main(String[] args) throws IOException {

		long start = System.currentTimeMillis();

		String inputFileName = args[0]; // dictionary
		String word1 = args[1]; // first word
		String word2 = args[2]; // second word
  
		FileReader reader = new FileReader(inputFileName);
		Scanner in = new Scanner(reader);
		
		// read in the data here
		String word;
		WordTrie trie = new WordTrie();
		while (in.hasNextLine()) {
			word = in.nextLine();
			trie.addWord(word);
		}
		
		// create graph here
		Dictionary dict = new Dictionary(trie.getWordCount());
		for (int i=0; i<trie.getWordCount(); i++) {
			dict.setVertex(trie.getWordList().get(i), i);
		}
		dict.build(trie);
		reader.close(); 
		in.close();

		// do the work here
		Vertex<String> result = dict.search(word1, word2);
		if (result == null) { 
			System.out.println("No ladder found");
		} else {
			LadderBuilder wordlader = new LadderBuilder();
			wordlader.construct(word1, result, dict);
			wordlader.printLader();
		}
		// end timer and print total time
		long end = System.currentTimeMillis();
		System.out.println("\nElapsed time: " + (end - start) + " milliseconds");
	}

}
