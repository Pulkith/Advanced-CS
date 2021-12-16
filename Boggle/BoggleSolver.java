import java.util.*;
import java.io.*;

public class BoggleSolver
{
	// Initializes the data structure using the given array of strings as the dictionary.
	// (You can assume each word in the dictionary contains only the uppercase letters A - Z.)
  HashSet<String> dict;
  int in = 0;
	public BoggleSolver(String dictionaryName)
	{
		try{
      Scanner s = new Scanner(new File(dictionaryName));
      dict = new HashSet<>();
      while(s.hasNextLine()) {
        dict.add(s.nextLine());
      }
    } catch(Exception ex) {
      System.out.println(dictionaryName);
    }
	}

	// Returns the set of all valid words in the given Boggle board, as an Iterable object
	public Iterable<String> getAllValidWords(BoggleBoard board)
	{
		HashSet<String> ans = new HashSet<String>(); 
    for(int i = 0; i < board.rows(); ++i)
      for(int j = 0; j < board.cols(); ++j)
        dfs("", 1 << (i * 4 + j), ans, i, j, board);
		return ans;
	}

  public void dfs(String cur, int state, HashSet<String> ans, int x, int y, BoggleBoard board) {
    cur += board.getLetter(x, y);
    if(board.getLetter(x, y) == 'Q') cur += 'U';
    if(dict.contains(cur)) ans.add(cur);
    int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1}, dy = {0, 1, 1, 1, 0, -1, -1, -1};
    for(int i = 0; i < dx.length; ++i) {
      int u = x + dx[i], v = y + dy[i];
      if(u >= 0 && u < board.rows() && v >= 0 && v < board.cols()) {
        int index = u * 4 + v;
        if((state & (1 << index)) == 0) {
          dfs(cur, state | (1 << index), ans, u, v, board);
        }
      }
    }
  }
  // public boolean 

	// Returns the score of the given word if it is in the dictionary, zero otherwise.
	// (You can assume the word contains only the uppercase letters A - Z.)
	public int scoreOf(String word)
	{
    int l = word.length();
		if(l < 3) return 0;
    if(l < 5) return 1;
    if(l == 5) return 2;
    if(l == 6) return 3;
    if(l == 7) return 5;
		return 11;
	}

	public static void main(String[] args) {
		System.out.println("WORKING");

		final String PATH   = "/Users/despicablemonkey/Desktop/Development/CS3/src/Boggle/data/";
		BoggleBoard  board  = new BoggleBoard(PATH + "board-q.txt");
		BoggleSolver solver = new BoggleSolver(PATH + "dictionary-algs4.txt");

		int totalPoints = 0;

		for (String s : solver.getAllValidWords(board)) {
			System.out.println(s + ", points = " + solver.scoreOf(s));
			totalPoints += solver.scoreOf(s);
		}

		System.out.println("Score = " + totalPoints); //should print 84

		//new BoggleGame(4, 4);
	}

}
