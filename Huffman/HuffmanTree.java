package Huffman;

import java.util.*;

public class HuffmanTree {
  int idCount = 0;
  N root;
  HuffmanTree(int[] counts) {
    HashMap<Integer, N> hm = new HashMap<>();
    PriorityQueue<Integer> pq = new PriorityQueue<>((node1, node2) -> Integer.compare(hm.get(node1).count, hm.get(node2).count));
    for(int i = 0; i < counts.length; ++i)
      if(counts[i] > 0) {
        hm.put(++idCount, new N(counts[i], idCount, i));
        pq.add(idCount);
      }
    while(pq.size() > 1) {
      N l = hm.get(pq.poll()), r= hm.get(pq.poll());
      N n = new N(l.count + r.count, ++this.idCount, -1);
      n.l = l; n.r = r;
      hm.put(idCount, n);
      pq.add(idCount);
    }
    root = hm.get(pq.poll());
  }

}

class Runner {
  public static void main(String[] args) {
    int[] vals = new int[256];
    vals['a'] = 3;
    vals['b'] = 3;
    vals['c'] = 1;
    vals['x'] = 2;
    vals['y'] = 2;
    HuffmanTree hf= new HuffmanTree(vals);
    TreePrinter.printTree(hf.root);
  }
}