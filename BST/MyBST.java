package BST;

import java.util.*;

public class MyBST<T> {
  BSTNode root;
  HashMap<BSTNode, Integer> heights = new HashMap<>();
  private class BSTNode {
    Integer val;
    Integer depth;
    BSTNode left, right;
    public BSTNode(Integer val) {
      this.val = val;
      left = right = null;
      depth = -1;
    }
    public String toString() {
      return "" + this.val;
    }
  }
  int size() { return size(root); } 
  int size(BSTNode n) {
    if(n == null) return 0;
    return 1 + size(n.left) + size(n.right);
  }
  void insert(Integer val) {
    if(root == null) root = new BSTNode(val);
    else insert(val, root);
  } 
  void insert(Integer val, BSTNode n) {
    if(val <= n.val) {
      if(n.left == null) n.left = new BSTNode(val);
      else insert(val, n.left);
    } else {
      if(n.right == null) n.right = new BSTNode(val);
      else insert(val, n.right);
    }
  }

  boolean contains(Integer v) { return contains(v, root);}
  boolean contains(Integer v, BSTNode n) {
    if(n == null) return false;
    if(n.val.equals(v)) return true;
    else return contains(v, n.left) || contains(v, n.right);
  }
  Integer getMax() { return getAbsolute(root, 1); }
  Integer getMin() { Integer min = getAbsolute(root, -1); return (min == null ? null : min * -1); }
  Integer getAbsolute(BSTNode n, int o) {
    if(n == null) return null;
    else return Math.max(n.val * o, Math.max((n.right == null ? n.val * o : getAbsolute(n.right, o)), (n.left == null ? n.val * o : getAbsolute(n.left, o))));
  }
  void delete(Integer v) { delete(v, root, null); }
  void delete(Integer val, BSTNode n, BSTNode p) {
    if(n == null) return;
    if(val.equals(n.val)) {
      if(n.left == null && n.right == null) {
        if(n == root) root = null;
        else if(p.left == n) p.left = null;
        else p.right = null;
      }
      else if(n.right == null) {
        if(root == n) root = n.left;
        else if(p.left == n) p.left = n.left;
        else p.right = n.left;
      }
      else if(n.left == null) {
        if(n == root) root = n.right;
        else if(p.left == n) p.left = n.right;
        else p.right = n.right;
      } else {
        Integer min = getAbsolute(n, -1) * -1;
        delete(min, n, null);
        n.val = min;
      }
    } else {
      delete(val, n.left, n);
      delete(val, n.right, n);
    }
  }
  void inOrder() { inOrder(root); }
  void inOrder(BSTNode n) {
    if(n == null) return;
    inOrder(n.left);
    System.out.print(n.val + " ");
    inOrder(n.right);
    if(n == root) System.out.println();
  }
  int height(BSTNode n, int depth) {
    if(n == null) return 0;
    n.depth = depth;
    heights.put(n, 1 + Math.max(height(n.left, depth + 1), height(n.right, depth + 1)));
    return heights.get(n);
  }

  void print() {
    int height = height(root, 0);
    int curdepth = 0, cursize = 0;
    if(height > 0) {
      Queue<BSTNode> bfs = new LinkedList<>();
      bfs.add(root);
      while(bfs.size() > 0) { 
        BSTNode t = bfs.poll();
        if(t == null) {
          if(curdepth < height) {
            bfs.add(null);
            System.out.print(s(5));
          }
        }
        if(t != null) {
          System.out.print(s(heights.get(t) * 3) + t.val + s(heights.get(t) * 3));
          bfs.add(t.left);
          bfs.add(t.right);
        }
        if(++cursize == (1 << curdepth)) {
          ++curdepth;
          cursize = 0;
          System.out.println("\n\n");
        }
      }
    }
  }
  private String s(int n) {
    String ans = "";
    for(int i = 0; i < n - 1; ++i) ans += ' ';
    return ans;
  }
}
