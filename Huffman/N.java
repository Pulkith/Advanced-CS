package Huffman;

public class N {
    N l,r;
    int count;
    int val = -1;
    int id;
    public N(int c, int ID, int val) {this.count = c; this.id = ID; this.val = val;}
    public String toString() {
        return this.count + " - " + this.val;
    }
  }