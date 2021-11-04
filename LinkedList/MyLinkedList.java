package LinkedList;
import java.util.*;
import java.util.stream.*;

class Runner {
	public static void main(String[] args) {
		MyLinkedList<Integer> a = new MyLinkedList<>();
		MyLinkedList<Integer> b = new MyLinkedList<>(20);
		
		System.out.println(a); //test no-parameter constructor and toString() method, should be []
		System.out.println(b); //test parameterized constructor and toString() method, should be [20]
		
		MyLinkedList<Integer> list = new MyLinkedList<>();
		list.add(4); list.add(5); list.add(6);
		System.out.println(list); //test one parameter add() method, toString() method, should be [4, 5, 6]
		
		System.out.println(list.contains(3)); //test contains() method, should be false
		System.out.println(list.contains(4)); //test contains() method, should be true
		
		System.out.println(list.indexOf(6)); //test indexOf() method, should be 2
		
		list.add(10);
		System.out.println(list.get(list.size() - 1)); //test get() method, by getting last element in the list, should be 10
		
		list.add(7); list.add(8); list.add(9);
		list.set(100, 4);
		System.out.println(list); //test set() method
		System.out.println(list.size()); //test size method, should be 7
		
		list.remove(1); //test remove() method, removes at index 1, not the first value 1
		System.out.println(list);
		
		list.add(1000, 2); //test two parameter add() method
		System.out.println(list); 
		
		for(Integer x : list) {
			System.out.print(x + " ");
		}
		System.out.println();

		while (!list.isEmpty()) { //test isEmpty() and remove() methods
			System.out.print(list.remove(0) + " "); //should print list in order
		}
		
		System.out.println("\n" + list.size()); //test size() method, should be back to 0
	}
}

public class MyLinkedList<U> implements Iterable<U>{
	private ListNode<U> head, tail;
	private int size;
	private class ListNode<T> {
		T val;
		ListNode<T> next;
		public ListNode(T val) {
			this.val = val;
			this.next = null;
		} 

		@Override
		public String toString() {
			return "" + this.val;
		}
	}
	public MyLinkedList() { this.head = null; this.tail = null;}
	public MyLinkedList(U val) { this.head = new ListNode<U>(val); this.tail = this.head; }
	public MyLinkedList(U... vals) {
		this();
		for(U e : vals)
			this.add(e);
	}
	boolean contains(U target) {
		return this.indexOf(target) != -1;
	}
	U get(int index) {
		return this.getRef(index).val;
	} 
	ListNode<U> getRef(int index) {
		ListNode<U> cur = this.head;
		int curIndex = 0;
		while(cur != null) {
			if(curIndex == index) return cur;
			cur = cur.next;
			++curIndex;
		}
		throw new IndexOutOfBoundsException();
	}
	int indexOf(U target) {
		ListNode<U> cur = this.head;
		int index = 0;
		while(cur != null) {
			if(cur.val == target) return index;
			cur = cur.next;
			++index;
		}
		return -1;
	}
	private U removeIndex(ListNode<U> target) {
		ListNode<U> cur = this.head;
		int index = 0;
		while(cur != null) {
			if(cur == target) return this.remove(index);
			cur = cur.next;
			++index;
		}
		throw new NullPointerException();
	}
	void set(U val, int index) {
		this.getRef(index).val = val;
	}
	int size() {
		return this.size;
	}
	int sizeRecursive(ListNode<U> cur) {
		if(cur == null) return 0;
		return 1 + sizeRecursive(cur.next);
	}
	boolean isEmpty() {
		return this.size() == 0;
	}
	U remove(int index) {
		if(this.size() <= index) throw new IndexOutOfBoundsException();
		--size;
		ListNode<U> cur = this.getRef(index), prev = index > 0 ? this.getRef(index-1) : null;
		if(index == 0) {
			this.head = (this.size() > 0) ? this.getRef(1) : null;
			this.tail = (this.size() > 0 ? this.tail : null);
		} else {
			if(cur == this.tail) this.tail = prev;
			prev.next = cur.next;
		}
		return cur.val;
	}
	void add(U newVal, int index) {
		if(index > this.size()) throw new IndexOutOfBoundsException();
		ListNode<U> e = new ListNode<>(newVal);
		if(index == 0) {
			e.next = this.head;
			this.head = e;
		} else {
			if(index != this.size())
				e.next = this.getRef(index);
			this.getRef(index-1).next = e;
		}
		if(index == this.size()) 
			this.tail = e;
		++size;
	}
	void add(U val) {
		this.add(val, this.size);
	}
	@Override
	public String toString() {
		String res = "[";
		ListNode<U> cur = this.head;
		while(cur != null) {
			res += (cur) + (cur.next != null ? ", " : "");
			cur = cur.next;
		}
		return res + "]";
	}

	private class LinkedListIterator implements Iterator<U> {
	        ListNode<U> cur;
	        public LinkedListIterator() { cur = head; }
	        @Override
	        public boolean hasNext() { return cur != null; }
	        @Override
	        public U next() { 
	        	U e = cur.val;
	        	cur = cur.next;
	        	return e;
	        }
	        @Override
	        public void remove() { removeIndex(cur); }
	    }
	    public Iterator<U> iterator() { return new LinkedListIterator(); }
}