import java.util.*;
import java.io.*;

class Disk implements Comparable<Disk> {
  ArrayList<Integer> files = new ArrayList<>();
  int rmspace = 1000000, id = 0;
  public int compareTo(Disk other) {
    if(this.id == other.id) return 0;
    return this.rmspace < other.rmspace ? 1 : -1;
  }
  public Disk(int i) { this.id = i; }
}
class Functions {
  public void WorstFit(ArrayList<Integer> files) {
    PriorityQueue<Disk> q = new PriorityQueue<>((x, y) -> x.compareTo(y));
    int cur = 0, sum = 0;
    for(int i = 0; i < files.size(); ++i) {
      sum += files.get(i);
      Disk u;
      if(q.size() > 0 && q.peek().rmspace >= files.get(i)) u = q.poll();
      else u = new Disk(cur++);
      u.rmspace -= files.get(i);  u.files.add(files.get(i));
      q.add(u);
    }
    if(q.size() < 100) {
      System.out.print("Total size = " + (double)sum / 1000000 + " GB" + "\nDisks req'd = " +  q.size());
      while(q.size() > 0) {
        Disk u = q.poll();
        System.out.print("\n    " + u.id + " " + u.rmspace + ": ");
        for(int v : u.files) System.out.print(v + " ");
      }
    }
  }

  public void WorstFitSorted(ArrayList<Integer> files) {
    Collections.sort(files);
    Collections.reverse(files);
    WorstFit(files);
  }

  public void BestFit(ArrayList<Integer> files) {
    Collections.sort(files);  Collections.reverse(files);
    TreeSet<Disk> ts = new TreeSet<>((x, y) -> x.compareTo(y));
    int id = 0, sum = 0;
    Disk tmp = new Disk(-1);
    for(int i = 0; i < files.size(); ++i) {
      tmp.rmspace = files.get(i);
      Disk v = ts.floor(tmp), u;
      if(v == null) u = new Disk(id++);
      else {
        ts.remove(v); u = v;
      }
      u.files.add(files.get(i));  u.rmspace -= files.get(i);
      ts.add(u);
      sum += files.get(i);
    }
    if(ts.size() < 100) {
      System.out.print("Total size = " + (double)sum / 1000000 + " GB" + "\nDisks req'd = " +  ts.size());
      while(ts.size() > 0) {
        Disk u = ts.pollFirst();
        System.out.print("\n   " + u.id + " " + u.rmspace + ": ");
        for(int v : u.files) System.out.print(v + " ");
      }
    }
  }
}

public class Main {
  public static void main(String[] args) {
      try {
        Scanner scan = new Scanner(new File("/Users/despicablemonkey/Desktop/Development/CS3/src/BinPacking/input20.txt"));
        ArrayList<Integer> files = new ArrayList<>();
        while(scan.hasNext()) files.add(scan.nextInt());
        Functions x = new Functions();
        x.BestFit(files);
      } catch(Exception ex) {}
  }
}