import java.util.*;

public class RecursionProbs {
	public static void main(String[] args) {
		System.out.println(sumReciprocals(10));
		System.out.println(productOfEvens(4));
		System.out.println(conversion(1453, 8));
		System.out.println(matchingDigits(100, 1));
		Stack<Integer> d = new Stack<>(); d.push(3); d.push(7); d.push(12); d.push(9);
		System.out.print(d + " >>> ");
		doubleUp(d);
		System.out.println(d);
		printThis(7); System.out.println();
		printNums2(10); System.out.println();
	}
	static double sumReciprocals(int n) {
		return (n==1? 1 : 1.0 / n + sumReciprocals(n-1));
	}
	static int productOfEvens(int n) {
		return (n == 1 ? 2 : 2 * n * productOfEvens(n-1));
	}
	static String conversion(int num, int base) {
		return (num < base ? "" + num : conversion(num  / base, base) + num % base);
	}
	static int matchingDigits(int a, int b) {
		if(a == 0 || b == 0) return (a%10 == b%10 ? 1 : 0);
		if(b / 10 == 0 || a / 10 == 0) return(a%10 == b%10 ? 1 : 0);
		else return ((a%10 == b%10 ?  1 : 0) + matchingDigits(a / 10, b / 10));
	}
	static void doubleUp(Stack<Integer> nums) {
		int num = nums.pop();
		if(nums.size() > 0) doubleUp(nums);
		nums.push(num); nums.push(num);
	}
	static void printThis(int n) {
		if(n == 1) System.out.print("*");
		else if(n == 2) System.out.print("**"); 
		else {
			System.out.print("<"); 
			printThis(n-2);
			System.out.print(">");
		}
	}
	static void printNums2(int n) {
		if(n == 1) System.out.print(1 + " ");
		else if(n == 2) System.out.print("1 1 ");
		else {
			System.out.print((n+1) / 2 + " ");
			printNums2(n - 2);
			System.out.print((n+1) / 2 + " ");
		}
	}
	

}