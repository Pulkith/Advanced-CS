package ShortestPath;

import java.awt.*;
import java.io.File;
import java.lang.reflect.Array;
import java.util.*;

class Pair {
    int x; double y;
    public Pair(int x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return pair.x == x;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

public class Dijakstra {

    static ArrayList<Integer>[] adj;
    static int[] dx, dy;
    static double[] cost;
    static HashSet<Integer> spset;
    static PriorityQueue<Pair> pq;
    static int INF = 1000000000;

    public static void d(int src, int V) {
        for(int i = 0; i < V; ++i) {
            if(i != src) {
                pq.add(new Pair(i, INF));
                cost[i] = INF;
            }
        }
        pq.add(new Pair(src, 0));

        while(pq.size() > 0) {
            Pair top = pq.poll();
            spset.add(top.x);
            for(int v : adj[top.x]) {
                double distance = Math.sqrt(Math.pow(dx[v] - dx[top.x], 2) + Math.pow(dy[v] - dy[top.x], 2));
                if(distance + cost[top.x] < cost[v]) {
                    pq.remove(new Pair(v, 0));
                    pq.add(new Pair(v, distance + cost[top.x]));
                    cost[v] = distance + cost[top.x];
                }
            }
        }

    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int V = s.nextInt(), E = s.nextInt();

        adj = new ArrayList[V+1];
        dx = new int[V+1];
        dy = new int[V+1];
        cost = new double[V+1];
        spset = new HashSet<>();
        pq =new PriorityQueue<Pair>((a, b) -> Double.compare(a.y, b.y));

        for(int i = 0; i < V; ++i) {
            adj[i] = new ArrayList<>();
            int I = s.nextInt(), x = s.nextInt(), y = s.nextInt();
            dx[i] = x;
            dy[i] = y;
        }
        for(int i = 0; i < E; ++i) {
            int u = s.nextInt(), v = s.nextInt();
            adj[u].add(v);
            adj[v].add(u);
        }

        int u = s.nextInt();
        int v = s.nextInt();

        d(u, V);

        System.out.println(String.format("%.2f", cost[v]));
    }
}
