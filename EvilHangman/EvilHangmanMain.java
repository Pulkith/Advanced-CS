package EvilHangman;

/**
 * EvilHangmanMain.java  06/04/2015
 *
 * @author - Robert Glen Martin
 * @author - School for the Talented and Gifted
 * @author - Dallas ISD
 */
public class EvilHangmanMain
{
	public static void main(String[] args)
	{
		EvilHangman evil = new EvilHangman("/Users/despicablemonkey/Desktop/Development/CS3/src/EvilHangman/test.txt", true);
//		EvilHangman evil = new EvilHangman("dictionary.txt", false);
		evil.playGame();
	}
}
