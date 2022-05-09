package GraphIntro;

import java.util.Scanner;


//1
//        3 2 1
//        1 2
//        2 3
//        2
public class Scooby {
    public static boolean dfs(char n, boolean[][] m, boolean[] v, char t) {
        if(n == t) return true;
        boolean res = false;
        v[n - 'A'] = true;
        for(int i = 0; i < 26; ++i)
            if(m[n - 'A'][i] && !v[i])
                res |= dfs((char)(i + 'A'), m, v, t);
         return res;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = Integer.parseInt(scan.nextLine());
        while(N-- > 0) {
            String[] paths = scan.nextLine().split(" ");
            String path = scan.nextLine();
            boolean[][] matrix = new boolean[26][26];
            boolean[] visited = new boolean[26];
            for(String p : paths) {
                matrix[p.charAt(0) - 'A'][p.charAt(1) - 'A'] = true;
                matrix[p.charAt(1) - 'A'][p.charAt(0) - 'A'] = true;
            }
            System.out.println(dfs(path.charAt(0), matrix, visited, path.charAt(1)) ? "yes" : "no");
        }
    }
}
