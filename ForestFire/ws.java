public class ws {
	public static void main(String[] args) {
		System.out.println(m(3));
	}
	public static int m(int n) {
		if(n < 0) return 2;
		return m(n-1) + m(n-3);
	}	
}