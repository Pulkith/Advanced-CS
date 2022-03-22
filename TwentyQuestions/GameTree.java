package TwentyQuestions;
import MazeSolver.StackADT;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.*;

/**
 * A model for the game of 20 questions
 *
 * @author Rick Mercer
 * Has feathers?
 * Barnyard?
 * chicken
 * owl
 * Is it a mammal?
 * tiger
 * rattlesnake
 */
public class GameTree
{
	/**
	 * Constructor needed to create the game.
	 *
	 * @param fileName
	 *          this is the name of the file we need to import the game questions
	 *          and answers from.
	 */
	private String filename_save = "";
  private class Node {
    public Node(String s) { this.data = s; }
    Node left, right;
    String data;
  }
  private Node root, current;
	public GameTree(String fileName) {
		try {
      Scanner scan = new Scanner(new File(fileName));
      addinitial(scan, null, false);
      current = root;
      filename_save = fileName;
    } catch(Exception ex) {}
	}
  public void addinitial(Scanner s, Node parent, boolean left) {
    Node cur;
    if(parent == null) {
      root = new Node(s.nextLine().trim());
      cur = root;
    } else {
      cur = new Node(s.nextLine().trim());
      if(left)
        parent.left = cur;
      else
        parent.right = cur;
    }
    if(cur.data.charAt(cur.data.length() - 1) == '?') {
      	addinitial(s, cur, true);
      	addinitial(s, cur, false);
    }
  }


	/*
	 * Add a new question and answer to the currentNode. If the current node has
	 * the answer chicken, theGame.add("Does it swim?", "goose"); should change
	 * that node like this:
	 */
	// -----------Feathers?-----------------Feathers?------
	// -------------/----\------------------/-------\------
	// ------- chicken  horse-----Does it swim?-----horse--
	// -----------------------------/------\---------------
	// --------------------------goose--chicken-----------
	/**
	 * @param newQ
	 *          The question to add where the old answer was.
	 * @param newA
	 *          The new Yes answer for the new question.
	 */
	public void add(String newQ, String newA)
	{
		Queue<Node> search = new LinkedList<>();
		search.add(root);
		while(search.size() > 0) {
			Node c = search.poll();
			if((c.left != null && c.right != null)) {
				if(c.left == current) {
					Node tmp = c.left;
					c.left = new Node(newQ.trim());
					c.left.left = new Node(newA.trim());
					c.left.right = tmp;
					return;
				}
				else if(c.right == current) {
					Node tmp = c.right;
					c.right = new Node(newQ.trim());
					c.right.left = new Node(newA.trim());
					c.right.right = tmp;
					return;
				}
				else {
					search.add(c.left);
					search.add(c.right);
				}
			}
		}
	}

	/**
	 * True if getCurrent() returns an answer rather than a question.
	 *
	 * @return False if the current node is an internal node rather than an answer
	 *         at a leaf.
	 */
	public boolean foundAnswer()
	{
		return current.left == null && current.right == null;
	}

	/**
	 * Return the data for the current node, which could be a question or an
	 * answer.  Current will change based on the users progress through the game.
	 *
	 * @return The current question or answer.
	 */
	public String getCurrent()
	{
	   return current.data;
	}

	/**
	 * Ask the game to update the current node by going left for Choice.yes or
	 * right for Choice.no Example code: theGame.playerSelected(Choice.Yes);
	 *
	 * @param yesOrNo
	 */
	public void playerSelected(Choice yesOrNo)
	{
		if(yesOrNo == Choice.Yes) current = current.left;
    	else current = current.right;
	}

	/**
	 * Begin a game at the root of the tree. getCurrent should return the question
	 * at the root of this GameTree.
	 */
	public void reStart()
	{
		current = root;
	}

	@Override
	public String toString() {
    return print(root, "", 0);
	}
  public String print(Node cur, String str, int depth) {
    	String hyp = "";
    	for(int i = 0; i < depth; ++i) hyp += "- ";
    	if(cur.right != null) str = print(cur.right, str, depth + 1);
    	str += hyp + cur.data + "\n";
    	if(cur.left != null) str = print(cur.left, str, depth + 1);
    	return str;
  }

	/**
	 * Overwrite the old file for this gameTree with the current state that may
	 * have new questions added since the game started.
	 *
	 */
	public void saveGame(){
    	if(filename_save.length() > 0) {
    		try {
				System.out.println(filename_save);
				PrintWriter pw = new PrintWriter(filename_save);
				Stack<Node> rem = new Stack<>();
				rem.add(root);
				while(rem.size() > 0) {
					Node r = rem.pop();
					pw.write(r.data + "\n");
					if(r.right != null) rem.add(r.right);
					if(r.left != null) rem.add(r.left);
				}
				pw.close();
			} catch (Exception ex) {}
		}
	}
}
