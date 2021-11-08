package GemMatching;

import LinkedListQueue.MyLinkedList;

import java.util.ArrayList;
import java.util.List;

public class GemList
{
	Node head = null;
	private class Node {
		private Gem gem;
		private Node next;
		public Node(Gem g) { this.gem = g; }
	}
	int size() {
		Node cur = head;
		int sz = 0;
		while(cur != null) {
			++sz;
			cur = cur.next;
		}
		return sz;
	}
	void draw(double y) {
		Node cur = head;
		int index = 0;
		while(cur != null) {
			cur.gem.draw(GemGame.indexToX(index), y);
			cur = cur.next; ++index;
		}
	}
	public String toString() {
		String res = "";
		Node cur = head;
		while(cur != null) {
			res += "[" + cur.gem + "]" + (cur.next != null ? " -> " : "");
			cur = cur.next;
		}
		return res;
	}

	void insertBefore(Gem gem, int index) {
		int sz = this.size();
		index = Math.min(index, sz);
		Node e = new Node(gem);

		if(index == 0) {
			e.next = this.head;
			this.head = e;
		} else {
			Node cur = this.head;
			for(int i = 0; i <= sz; ++i) {
				if(i == index-1) {
					e.next = cur.next;
					cur.next = e;
					return;
				}
				cur = cur.next;
			}
		}
	}

	int score() {
		Node cur = head;
		int ans = 0, cursum = 0, multi = 0;
		GemType last = null;
		while(cur != null) {
			if(last != null && cur.gem.color == last) {
				cursum +=cur.gem.getPoints();
				++multi;
			} else {
				ans += cursum * multi;
				multi = 1;
				cursum = cur.gem.getPoints();
				last = cur.gem.color;
			}
			cur = cur.next;
		}
		ans += cursum * multi;
		return ans;
	}

	
	public static void main(String [] args)
	{
		GemList list = new GemList();
		System.out.println(list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.9);

		list.insertBefore(new Gem(GemType.BLUE, 10), 0);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.8);

		list.insertBefore(new Gem(GemType.BLUE, 20), 99);  //not a mistake, should still work
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.7);

		list.insertBefore(new Gem(GemType.ORANGE, 30), 1);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.6);

		list.insertBefore(new Gem(GemType.ORANGE, 10), 2);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.5);

		list.insertBefore(new Gem(GemType.ORANGE, 50), 3);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.4);

		list.insertBefore(new Gem(GemType.GREEN, 50), 2);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.3);
	}	
}
