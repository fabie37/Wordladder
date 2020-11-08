import java.io.*;
import java.util.*;

/**
 program to find word ladder with shortest path (i.e. minimum number edges
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
		// Build Word Trie For LevenStein Distance
		String word;
		WordTrie trie = new WordTrie();
		while (in.hasNextLine()) {
			word = in.nextLine();
			trie.addWord(word);
		}
		
		// create graph here
		/*Dictionary dict = new Dictionary(trie.getWordCount());
		for (int i=0; i<trie.getWordCount(); i++) {
			dict.setVertex(trie.getWordList().get(i), i);
		}
		dict.build();
		reader.close(); */
		HashMap<String, Boolean> found = new HashMap<String, Boolean>();
		for (String s: trie.getWordList()) {
			for (String d: trie.getWordList()) {
				Boolean b = found.get(d);
				if (b == null) {continue;} 
				else if (b == true) { continue;}
				else {LevenstienDistance.getDistance(s, d, 1);}
			}
			found.put(s, true);
			/*List<String> results  = trie.getLevenstienDistances(s, 1);
			int i = 1;*/
			
		}
		

		/*
		// do the work here
		Vertex<String> result = dict.search(word1, word2);
		if (result == null) { 
			System.out.println("No ladder found");
		} else {
			LaderBuilder wordlader = new LaderBuilder();
			wordlader.construct(word1, result, dict);
			wordlader.printLader();
		}*/
		// end timer and print total time
		long end = System.currentTimeMillis();
		System.out.println("\nElapsed time: " + (end - start) + " milliseconds");
	}

}
