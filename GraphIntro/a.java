package GraphIntro;

import java.util.*;


class n {
    n next;
    String value;
}


public class a {
    public static char c(int i) {
        return (char)(i + 'A');
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int T = scan.nextInt();
        for(int tt = 0; tt < T; ++tt){
            System.out.print("Case #" + (tt+1) +": ");
            int N = scan.nextInt(), K = scan.nextInt();
            int[] a = new int[N];
            for(int i = 0; i < N; ++i) a[i] = scan.nextInt();
            long sum1 = 0, sum2 = 0;
            for(int i = 0; i < N; ++i) {
                sum1 += a[i];
                sum2 += a[i] * a[i];
            }

            long v = sum2 -= sum1 * sum1;
            long k = (2 * sum1);
            if(sum1 == 0 && sum2 == 0) System.out.println("1");
            else if((k != 0) && Math.abs(v % k) == 0) System.out.println(v / k);
            else System.out.println("IMPOSSIBLE");



        }
    }
}