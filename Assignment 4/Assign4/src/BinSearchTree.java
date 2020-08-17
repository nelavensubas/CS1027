/*
 * Assignment 4: This is the file that will use a linked structure
 * to implement a binary search tree.
 */

public class BinSearchTree {
	/**
	 * Reference the the root node of the binary search tree.
	 */
	private BinSearchTreeNode root;

	/**
	 * getWord method to figure out the node of the binary search tree storing the
	 * word the user is looking for
	 * 
	 * @param searchWord the word to look for
	 * @return the node storing searchWord or null if no node stores it
	 */
	public BinSearchTreeNode getWord(String searchWord) {
		return search(root, searchWord);
	}

	/**
	 * insertWord method to add a new node storing the new word to the binary search
	 * tree
	 * 
	 * @param theWord     the word to look for
	 * @param theFileName the name of the file to store the word
	 * @param thePosition the position to store the word
	 */
	public void insertWord(String theWord, String theFileName, int thePosition) {
		// If root is null that means the binary search tree, so set the new node
		// as the root
		if (root == null) {
			root = new BinSearchTreeNode(theWord, theFileName, thePosition);
		} else {
			insert(root, theWord, theFileName, thePosition);
		}
	}

	/**
	 * search method to find the node storing a specific word
	 * 
	 * @param rootOfTree the root node of the binary search tree
	 * @param searchWord the word to look for
	 * @return the node that stores the specific word if it exists
	 */
	public BinSearchTreeNode search(BinSearchTreeNode rootOfTree, String searchWord) {
		// If root is null then the binary search tree is empty
		if (rootOfTree == null) {
			return null;
			// The word is present at the root if it equals the specific word
		} else if (rootOfTree.getWord().equals(searchWord)) {
			return rootOfTree;
			// If the word is less than the root, search through the left subtree
		} else if (searchWord.compareTo(rootOfTree.getWord()) < 0) {
			return search(rootOfTree.getLeft(), searchWord);
			// The word is greater than the root, so search through the right subtree
		} else {
			return search(rootOfTree.getRight(), searchWord);
		}
	}

	/**
	 * insert method to add a new node to the binary search tree
	 * 
	 * @param rootOfTree  the root node of the binary search tree
	 * @param theWord     the word to look for
	 * @param theFileName the name of the file to store the word
	 * @param thePosition the position to store the word
	 * @return the new node that stores the new word
	 */
	public void insert(BinSearchTreeNode rootOfTree, String theWord, String theFile, int thePosition) {
		// If the word is less than the root, it should be inserted in the left subtree
		if (theWord.compareTo(rootOfTree.getWord()) < 0) {
			if (rootOfTree.getLeft() == null) {
				// Create a new node and then store the word, the file name, and the position
				// and set it as the left child of the root
				BinSearchTreeNode newLeft = new BinSearchTreeNode(theWord, theFile, thePosition);
				rootOfTree.setLeft(newLeft);
			} else {
				insert(rootOfTree.getLeft(), theWord, theFile, thePosition);
			}
			// If the word is greater than the root, it should be inserted in the right
			// subtree
		} else if (theWord.compareTo(rootOfTree.getWord()) > 0) {
			if (rootOfTree.getRight() == null) {
				// Create a new node and then store the word, the file name, and the position
				// and set it as the right child of the root
				BinSearchTreeNode newRight = new BinSearchTreeNode(theWord, theFile, thePosition);
				rootOfTree.setRight(newRight);
			} else {
				insert(rootOfTree.getRight(), theWord, theFile, thePosition);
			}
		} else {
			// Get the list of file node objects stored in the root
			LinkedList files = rootOfTree.getFiles();
			// Add the file name and position to the linked list
			files.insertWord(theFile, thePosition);
		}
	}
}
