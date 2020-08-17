/*
 * Assignment 4: This is the file prints information about whether it found
 * a certain word in a collection of text files or not.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Searcher {
	/**
	 * Reference the table of binary search trees that implements the lexicon data
	 * structure.
	 */
	private HashTable table;
	/**
	 * The name of the input file storing all words that will be searched for in the
	 * lexicon data structure.
	 */
	private String inputFile;

	/**
	 * Constructor will create the hash table storing the binary search trees.
	 * 
	 * @param table        the table of binary search trees
	 * @param theInputTest the name of the input file that stores all the words
	 */
	public Searcher(HashTable table, String theInputTestFile) {
		this.table = table;
		this.inputFile = theInputTestFile;
	}

	/**
	 * findAllWords method prints out information about each word if it appears in
	 * any of the text files
	 */
	public void findAllWords() {
		try {
			BufferedReader in = new BufferedReader(new FileReader(this.inputFile));
			String line = in.readLine();
			// Go through each line in the input file
			while (line != null) {
				// Get the words in each line
				String[] words = line.split(" ");
				// See if each word in the line exists in the binary search tree
				for (String word : words) {
					if (!(word.equals(""))) {
						this.findWord(word);
					}
				}
				line = in.readLine();
			}
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * findWord method checks to see if a specific word exists in any files
	 * 
	 * @param searchWord the specific word to look for
	 */
	public void findWord(String searchWord) {
		// Determine the index of the entry table that might contain the specific word
		int searchWordEntry = this.table.computeIndex(searchWord);
		BinSearchTree[] tableOfTrees = this.table.getTable();
		// Get the node that contains the word if it exists
		BinSearchTreeNode wordNode = tableOfTrees[searchWordEntry].getWord(searchWord);

		// If the node is null that means the word doesn't exist in any files
		if (wordNode == null) {
			CustomPrinter.wordNotFound(searchWord, inputFile);
		} else {
			CustomPrinter.wordFound(searchWord, inputFile);
			// Get the list of file node objects
			LinkedList lp = wordNode.getFiles();
			// The first node of the linked list
			FileNode p = lp.getHead();
			// Go through the binary search tree
			while (p != null) {
				// Print out the name of the files and positions that store the specific word
				CustomPrinter.printPositionsPerFileFound(p.getFilename(), p.getPositions(), inputFile);
				p = p.getNext();
			}
		}
	}

}
