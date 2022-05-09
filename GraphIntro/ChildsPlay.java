package GraphIntro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ChildsPlay {
    public static void dfs(int u, boolean[] vis, ArrayList<ArrayList<Integer>> adj) {
        vis[u] = true;
        for(int v : adj.get(u))
            if(!vis[v])
                dfs(v, vis, adj);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        while(N -- > 0) {
            int n = scanner.nextInt(), m = scanner.nextInt(), l = scanner.nextInt();
            ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
            for(int i = 0; i <= n; ++i) adj.add(new ArrayList<Integer>());
            for(int i = 0; i < m; ++i) {
                int u = scanner.nextInt(),v = scanner.nextInt();
                adj.get(u).add(v);
            }
            ArrayList<Integer> hit = new ArrayList<>();
            for(int i = 0; i < l; ++i)
                hit.add(scanner.nextInt());
            boolean[] vis = new boolean[n+1];
            for(int i = 0; i < l; ++i) {
                dfs(hit.get(i), vis, adj);
            }
            int count = 0;
            for(boolean i : vis) count += (i ? 1 : 0);
            System.out.println(count);
        }
    }
}
