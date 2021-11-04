import java.util.*;
import java.io.*;
import java.awt.*;

public class main {
	public static void main(String[] args) throws Exception {
		HashSet<String> dict = new HashSet<>(), temp = new HashSet<>();
		Scanner _s_dict =  new Scanner(new File("dictionary.txt")), scan = new Scanner(new File("input.txt"));
		while(_s_dict.hasNext()) dict.add(_s_dict.nextLine().trim().toLowerCase());
		while(scan.hasNext()) {
			String[] line = scan.nextLine().split(" ");
			String origin = line[0].trim(), fin = line[1].trim();
			if(!dict.contains(origin)) System.out.println("No Ladder found between " + origin + " and " + fin);
			else if(origin.equals(fin)) System.out.println("Ladder Found! (size 1) >>> [" + origin + "]"); 
			else {
				Queue<Stack<String>> bfs = new LinkedList<>(); 
				Stack<String> _head = new Stack<>();
				_head.push(origin); bfs.offer(_head); temp.add(origin); dict.remove(origin);
				boolean found = false;
				while(bfs.size() > 0) {
					Stack<String> top = bfs.poll();
					String prevWord = top.peek();
					if(prevWord.equalsIgnoreCase(fin)) {
						String ans = "]"; int sz = top.size();
						while(top.size() > 0) ans = top.pop() + (ans.length() > 1 ? ", "  : "") + ans;
						System.out.println("Ladder Found! (size " + sz + ") >>> [" + ans);
						found = true; break;
					} else {
						char[] word = prevWord.toCharArray();
						for(int i = 0; i < word.length; ++i)
							for(int j = 0; j < 26; ++j) {
								word[i] = (char)(((word[i]+1) - 97)%26 + 97);
								String ladder = String.valueOf(word);
								if(dict.contains(ladder)) {
									dict.remove(ladder); temp.add(ladder);
									Stack<String> copy = new Stack<String>();
									copy.addAll(top); copy.push(ladder); bfs.offer(copy);
								}
							}
					}
				}
				if(!found) System.out.println("No Ladder found between " + origin + " and " + fin);
				dict.addAll(temp); temp.clear();
			}
		}
	}
}