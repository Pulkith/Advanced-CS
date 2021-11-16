package ForestFire;

import java.util.*;


public class BacktrackingProbs {
	public static void main(String[] args) {
		// printBinary(3, "");
		// climbStairs(4, "");
		// campsite(2, 1, "");
		// System.out.println(getMax(Arrays.asList(7, 30, 8, 22, 6, 1, 14), 19, 7, 0));
		// System.out.println(makeChange(25, 0));
		// System.out.println(makeChange(100, 0));
		// System.out.println(" P  N  D  Q");
		// System.out.println("------------");
		// makeChangePrint(11, 0, new int[4]);
		System.out.println(longestCommonSub("ABCDEFG","BGCEHAF", 7, ""));
		System.out.println(longestCommonSub("12345","543212154321", 5, ""));

	}	

	static void printBinary(int len, String s) {
		if(len == 0) System.out.print(s + " ");
		else {
			printBinary(len -1 ,  s + "0");
			printBinary(len-1, s + "1");
		}
	}
	static void climbStairs(int steps, String s) {
		if(steps == 0) System.out.println(s);
		else if(steps == 1) System.out.println(s + (s.length() > 0 ? ", 1" : "1"));
		else {
			climbStairs(steps - 1, s + (s.length() > 0 ? ", 1" : "1"));
			climbStairs(steps - 2, s + (s.length() > 0 ? ", 2" : "2"));
		}
	}
	static void campsite(int X, int Y, String s) {
		if(X == 0 && Y == 0) System.out.println(s);
		else {
			if(X != 0) campsite(X - 1, Y, s + "E ");
			if(Y != 0) campsite(X, Y - 1, s + "N ");
			if(X != 0 && Y != 0) campsite(X - 1, Y - 1, s + "NE " );
		}
	}
	static int getMax(List<Integer> nums, int limit, int size, int curSum) {
		if(size == 0) return (curSum > limit ? -1 : curSum);
		int max = -1;
		max = Math.max(getMax(nums, limit, size - 1, curSum + nums.get(size - 1)), getMax(nums, limit, size - 1, curSum));	
		return max;
	}
	static int makeChange(int amt, int index) { 
		if(index >= 4) return 0;
		if(amt <= 0) return (amt < 0 ? 0 : 1);
		int [] d = {25, 10, 5, 1};
		while(d[index] > amt) ++index;
		return makeChange(amt - d[index], index) + makeChange(amt, index + 1);
	}
	static void makeChangePrint(int amt, int index, int[] a) {
		if(index >= 4) return;
		if(amt <= 0) {
			if(amt == 0) System.out.println(Arrays.toString(a));
			return;
		}
		int [] d = {25, 10, 5, 1};
		while(d[index] > amt) ++index;
		int[] c = a.clone(); ++c[3 - index];
		makeChangePrint(amt - d[index], index, c);
		makeChangePrint(amt, index + 1, new int[]{a[0], a[1], a[2], a[3]});
	}
	static String longestCommonSub(String a, String b, int size, String curString) {
		if(size == 0) {
			int ind = 0;
			for(int i = 0; i < b.length() && ind < curString.length(); ++i) 
				ind += (b.charAt(i) == curString.charAt(ind) ? 1 : 0);
			return (ind == curString.length() ? curString : "");
		}
		String ans = longestCommonSub(a, b, size-1, a.charAt(size-1) + curString), op2 = longestCommonSub(a, b, size-1, curString);
		return (op2.length() > ans.length() ? op2 : ans); 
	}
}
