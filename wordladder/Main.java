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
		String word = in.nextLine();
		List<String> wordlist = new ArrayList<String>();
		while (in.hasNextLine()) {
			wordlist.add(word);
			word = in.nextLine();
		}
		// create graph here
		Dictionary dict = new Dictionary(wordlist.size());
		for (int i=0; i<wordlist.size(); i++) {
			dict.setVertex(wordlist.get(i), i);
		}
		dict.build();
		reader.close();

        
		// do the work here
		Vertex<String> result = dict.search(word1, word2);
		if (result == null) { 
			System.out.println("No ladder found");
		} else {
			LaderBuilder wordlader = new LaderBuilder();
			wordlader.construct(word1, result, dict);
			wordlader.printLader();
		}
		// end timer and print total time
		long end = System.currentTimeMillis();
		System.out.println("\nElapsed time: " + (end - start) + " milliseconds");
	}

}
