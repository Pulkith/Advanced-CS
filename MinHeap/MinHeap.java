package MinHeap;

class MinHeap {
  int size = 0;
  static final int DEFAULT_CAPACITY= 8;
  Integer[] heap = new Integer[DEFAULT_CAPACITY];
  public MinHeap(){}
  public MinHeap(Integer[] v) {
    buildHeap(v);
  }
  void buildHeap(Integer[] v) {
    for(Integer k : v)
      this.insert(k);
  }

  int getsize() {
    return this.size;
  }
  boolean isEmpty() { return this.size == 0; }
  Integer peekMinimum() { return !this.isEmpty() ? this.heap[1] : null; }
  int gLCIndex(int i) { return i * 2; }
  int gRCIndex(int i) { return 2 * i + 1;}
  int gPIndex(int i) {return i / 2;}
  private void doubleCapacity() {
    int len = this.heap.length * 2;
    Integer[] nwhp = new Integer[len];
    for(int i = 0; i < len / 2; ++i)
      nwhp[i] = heap[i];
    heap= nwhp;
  }
  void insert(Integer u) {
    if(this.getsize() + 1 == this.heap.length) doubleCapacity();
    this.heap[++this.size] = u;
    this.bubbleUp(this.size);
  }
  void bubbleUp(int index) {
    if(index > 1 && heap[gPIndex(index)] > heap[index]) {
      int sv = heap[gPIndex(index)];
      heap[gPIndex(index)] = heap[index];
      heap[index] = sv;
      bubbleUp(gPIndex(index));
    }
  }

  Integer popMinimum() {
    int v = peekMinimum();
    if(size > 1) {
      heap[1] = heap[size];
      heap[size] = null;
      --size;
      siftDown(1);
    }
    return v;
  }
  void siftDown(int index) {
    Integer left = heap[this.gLCIndex(index)];
    Integer right = heap[this.gRCIndex(index)];
    Integer cur = heap[index];
    int in = -1;
    if(left != null && cur > left && (right == null || left <= right))
      in = gLCIndex(index);
    else if(right != null && cur > right && (right == null || right <= left))
      in = gLCIndex(index);
    if(in != -1) {
      int sv = heap[in];
      heap[in] = heap[index];
      heap[index] = sv;
      siftDown(in);
    }
  }
  public String toString() {
    String output = "";
    for(int i = 1; i <= getsize(); ++i)
      output += heap[i] + ", ";
    return output.substring(0, output.lastIndexOf(","));
  }
  public void display() {
    int nBlanks = 32, itemsPerRow = 1, column = 0, j = 1;
    String dots = "...............................";
    System.out.println(dots + dots);
    while (j <= this.getsize()){
      if (column == 0)
        for (int k = 0; k < nBlanks; k++)
          System.out.print(' ');
      System.out.print((heap[j] == null)? "" : heap[j]);
      if (++column == itemsPerRow) {
        nBlanks /= 2;
        itemsPerRow *= 2;
        column = 0;
        System.out.println();
      }
      else
        for (int k = 0; k < nBlanks * 2-2; k++)
          System.out.print(' ');
      j++;
    }
    System.out.println("\n" + dots + dots);
  }
}