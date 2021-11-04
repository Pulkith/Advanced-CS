package Review;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Runner {
    public static void main(String[] args) throws FileNotFoundException {
        WelcomeBack wb = new WelcomeBack();
        System.out.println(wb.getMiddle("abc"));
        System.out.println(Arrays.toString(wb.sumNumbers(5)));
        System.out.println(wb.sumDigits(10));
        System.out.println(wb.keepSummingDigits(10));
        System.out.println(wb.getIntersection(new int[]{1, 2, 3}, new int[]{1, 2, 3}));
        System.out.println(wb.mapLengths(new String[]{"abc"}));
        System.out.println(wb.sumDigitsRecur(1));
        System.out.println(wb.sumWithoutCarry(16, 16));
        System.out.println(wb.buySell(new int[]{1, 2, 3, 4}));
        wb.zeck2("Review/zeck.txt");
    }
}

class WelcomeBack {
    public String getMiddle(String s) {
        return (s.length()%2==0) ? s.substring(s.length() / 2 - 1, s.length() / 2 + 1) : "" +s.charAt(s.length() / 2);
    }
    public int[] sumNumbers(int n) {
        int[] res = new int[n+1];
        for(int i = 1; i <= n; ++i)
            res[i] = res[i-1] + i ;
        return res;
    }

    public int sumDigits(int num) {
        int ans = 0;
        while(num > 0) {
            ans += num % 10;
            num /= 10;
        }
        return ans;
    }

    public int keepSummingDigits(int num) {
        while((""+num).length() > 1)
            num = sumDigits(num);
        return num;
    }

    public String getIntersection(int[] a, int[] b) {
        String ans = "";
        HashSet<Integer> cur = new HashSet<>();
        for(Integer e : a)
            cur.add(e);
        for(Integer e : b) {
            if(cur.contains(e)) {
                ans += e;
                cur.remove(e);
            }
        }
        return ans;
    }

    HashMap<Integer, Integer> mapLengths (String[] words) {
        HashMap<Integer, Integer> res = new HashMap<Integer, Integer>();
        for(String word : words) {
            if(!res.containsKey(word.length())) res.put(word.length(), 0);
            res.put(word.length(), res.get(word.length()) + 1);
        }
        return res;
    }

    int sumDigitsRecur(int n) {
        return (""+n).length() > 1 ? sumDigitsRecur(n / 10) + n%10 : n;
    }

    int sumWithoutCarry(int a, int b) {
        String res = "";
        for(int i = 0; i < (""+a).length(); ++i)  res += (((""+a).charAt(i) - 48) + ((""+b).charAt(i) - 48)) % 10;
        return Integer.parseInt(res);
    }

    int buySell (int[] stock) {
        int max = 0;
        for(int i = 0; i < stock.length; ++i)
            for(int j = i+1; j < stock.length; ++j)
                max = Math.max(max, stock[j] - stock[i]);
        return max;
    }

    void zeck(String fileName) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(fileName));
        int n = scan.nextInt();
        while(n-- > 0) {
            int N  = scan.nextInt();
            ArrayList<Integer> fib = new ArrayList<Integer>();
            fib.add(1); fib.add(1);

            while(fib.get(fib.size() - 1) < N) {
                fib.add(fib.get(fib.size() - 1) + fib.get(fib.size() - 2));
            }
            fib.remove(0);
            for(int i = 1; i <= (1 << fib.size()); ++i) {
                boolean[] active = new boolean[fib.size()];
                for(int j = 0; j < fib.size(); ++j) {
                    if((i&(1<<j)) != 0) {
                        active[j] = true;
                    }
                }
                boolean bad = false;
                for(int j = 1; j < active.length; ++j) {
                    bad |= (active[j] && active[j-1]);
                }
                if(bad) continue;
                int sum = 0;
                for(int j = 0; j < active.length; ++j) {
                    if(active[j]) {
                        sum += fib.get(j);
                    }
                }

                if(sum == N) {
                    System.out.print(N + " = ");
                    int curSum = 0;
                    for(int j = active.length - 1; j >= 0; --j) {
                        if(active[j]) {
                            if(curSum != 0) System.out.print(" + ");
                            curSum += fib.get(j);
                            System.out.print(fib.get(j));
                        }
                    }
                    System.out.println();
                    break;
                }
            }
        }
    }

    void zeck2(String fileName) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(fileName));
        int n = scan.nextInt();
        while(n-- > 0) {
            int N  = scan.nextInt();
            ArrayList<Integer> fib = new ArrayList<Integer>();
            fib.add(1); fib.add(1);

            while(fib.get(fib.size() - 1) < N)
                fib.add(fib.get(fib.size() - 1) + fib.get(fib.size() - 2));
            fib.remove(0);


            Collections.sort(fib);
            System.out.print(N + " = ");
            for(int i = fib.size() -1; i >= 0; --i) {
                if(N - fib.get(i) >= 0) {
                    N -= fib.get(i);
                    System.out.print(fib.get(i) + (((N > 0) ? " + " : "")));
                }
            }
            System.out.println();
        }
    }
}