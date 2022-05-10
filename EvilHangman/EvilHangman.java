package EvilHangman;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

/**
 * @author - Jane Doe
 * @author - Period n
 * @author - Id nnnnnnn
 *
 * @author - I received help from ...
 */
public class EvilHangman
{
	private boolean      debug;
	private Scanner      in;
	private List<String> wordList;
	private int          wordLength;
	private int          remainingGuesses;
	private String       solution;
	private String       guessedLetters;


	/**
	 * Construct an EvilHangman object.
	 * @param fileName the name of the file that contains the word list.
	 * @param debug indicates if the size of the remaining word list
	 *        should be included in the toString result.
	 */
   public EvilHangman(String fileName, boolean debug)
   {
		this.debug = debug;
		in = new Scanner(System.in);
		inputWords(fileName);
	   	System.out.print("Number of guesses? ");
	   	this.remainingGuesses = in.nextInt();
	   	solution = "";
	   	for(int i = 0; i < wordLength; ++i)
	   		solution += '-';
	   	guessedLetters = "";
   }

	/**
	 * Plays one the game.  The user guesses letters until either
	 * they guess the word, or they run out of guesses.
	 * Game status is printed each turn and winning / losing
	 * information is printed at the conclusion of the game.
	 */
   public void playGame()
   {
   	while(remainingGuesses > 0 && solution.indexOf('-') > -1) {
		System.out.println(this);
		String let = inputLetter();
		guessedLetters += let;
		List<Partition> p = getPartitionList(let);
		removeSmallerPartitions(p);
		this.wordList = getWordsFromOptimalPartition(p);
		String cur = solution;
		substitute(wordList.get(0), let);
		if(cur.equals(solution))
			--remainingGuesses;
	}
   	if(remainingGuesses > 0)
		System.out.println("You win, congratulations!");
   	else {
		System.out.println("You lose, sorry!\n ");
		System.out.println("The word was\"" + wordList.get((int) (Math.random() * wordList.size())) + "\"");
	}
   }

	/**
	 * Creates and returns a status string that indicates the
	 * state of the game.
	 * @return the game status string.
	 */
   public String toString()
   {
		String res = "Remaining guesses: " + remainingGuesses + "\n" + "Guessed letters: " + guessedLetters + "\n" + "Solution: " + solution + '\n';
		if(debug) res += "Remaining words: " + wordList.size() + "\n";
		return res;

   }


	////////// PRIVATE HELPER METHODS //////////

	/**
	 * Helper method for the constructor:
	 * Inputs the word length from the user, reads in the words from
	 * the fileName file, and initializes the wordList instance variable
	 * with the words of the correct length.  This method loops until
	 * a valid word length is entered.
	 * @param fileName the name of the file that contains the word list.
	 */
   private void inputWords(String fileName)
   {
   	wordList = new ArrayList<>();
   	try {
   		Scanner words = new Scanner(new File(fileName));
   		while(wordList.size() == 0) {
			System.out.print("Word length? ");
			this.wordLength = in.nextInt();
			while(words.hasNext()) {
				String w = words.next();
				if(w.length() == wordLength)
					wordList.add(w);
			}
			if(wordList.size() == 0)
				System.out.println("There are no words with " + wordLength + " letters.");
		}
	} catch (Exception ex) {
		System.out.println("Can't find dictionary file you idiot");
	}

   }

	/**
	 * Helper method for playGame:
	 * Inputs a one-letter string from the user.
	 * Loops until the string is a one-character character in the range
	 * a-z or A-Z.
	 * @return the single-letter string converted to upper-case.
	 */
	private String inputLetter()
	{
		while(true) {
			System.out.print("Next letter? ");
			String i = in.next();
			if(i.toUpperCase().matches("[A-Z]"))
				return i;
			System.out.println("Invalid input!");
		}
	}

	/**
	 * Helper method for getPartitionList:
	 * Uses word and letter to create a pattern.  The pattern string
	 * has the same number of letter as word.  If a character in
	 * word is the same as letter, the pattern is letter; Otherwise
	 * it's "-".
	 * @param word the word used to create the pattern
	 * @param letter the letter used to create the pattern
	 * @return the pattern
	 */
	private String getPattern(String word, String letter)
	{
		String ret = "";
		for(char c : word.toCharArray())
			ret += ((""+c).equals(letter) ? letter : '-');
		return ret;
	}

	/**
	 * Helper method for playGame:
	 * Partitions each string in wordList based on their patterns.
	 * @param letter the letter used to find the pattern for
	 *        each word in wordList.
	 * @return the list of partitions.
	 */
	private List<Partition> getPartitionList(String letter)
	{
		ArrayList<Partition> arr = new ArrayList<>();
		for(String w : wordList) {
			String s = getPattern(w, letter);
			boolean good = false;
			for(Partition p : arr)
				good |= p.addIfMatches(s, w);
			if(!good)
				arr.add(new Partition(s, w));
		}
		return arr;
	}

	/**
	 * Helper method for playGame:
	 * Removes all but the largest (most words) partitions from partitions.
	 * @param partitions the list of partitions.
	 *        Precondition: partitions.size() > 0
	 * Postcondition: partitions
	 * 1) contains all of the partitions with the most words.
	 * 2) does not contain any of the partitions with fewer than the most words.
	 */
	private void removeSmallerPartitions(List<Partition> partitions)
	{
		int index = 0;
		for(int i = 0; i < partitions.size(); ++i)
			if(partitions.get(i).getWords().size() > partitions.get(index).getWords().size())
				index = i;
		int len = partitions.get(index).getWords().size();
		for(int i = 0; i < partitions.size(); ++i) {
			if(partitions.get(i).getWords().size() < len) {
				partitions.remove(i);
				--i;
			}
		}
	}

	/**
	 * Helper method for playGame:
	 * Returns the words from one of the optimal partitions,
	 *    that is a partition with the most dashes in its pattern.
	 * @param partitions the list of partitions.
	 * @return the optimal partition.
	 */
	private List<String> getWordsFromOptimalPartition(List<Partition> partitions)
	{
		int index = 0;
		for(int i = 0; i < partitions.size(); ++i)
			if(partitions.get(i).getPatternDashCount() > partitions.get(index).getPatternDashCount())
				index = i;
		return partitions.get(index).getWords();

	}

	/**
	 * Helper method for playGame:
	 * Creates a new string for solution.  If the ith letter of
	 * found equals letter, then the ith letter of solution is
	 * changed to letter; Otherwise it is unchanged.
	 * @param found the string to check for occurances of letter.
	 * @param letter the letter to check for in found.
	 */
	private void substitute(String found, String letter)
	{
		String res = "";
		for(int i = 0; i < solution.length(); ++i) {
			if(found.charAt(i) == letter.charAt(0))
				res += found.charAt(i);
			else
				res += solution.charAt(i);
		}
		solution = res;
	}
}
