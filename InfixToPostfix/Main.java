package InfixToPostfix;

import java.io.File;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static int lvl(char c) {
        if(c == '^') return 3;
        if(c == '*' || c == '/') return 2;
        if(c == '+' || c == '-') return 1;
        return 0;
    }

    public static void main(String[] args) throws Exception{
        Scanner s = new Scanner(new File("/Users/despicablemonkey/Desktop/CS3/src/InfixToPostfix/in.txt"));
        int N = Integer.parseInt(s.nextLine());
        while(N-- > 0) {
            String infix = s.nextLine(), ans = "";
            Stack<Character> st = new Stack<>();
            for(char c : infix.toCharArray()) {
                if(c == ' ') continue;
                if(c > '0' && c <= '9') ans += c + " ";
                else {
                    if(c == '(') st.push(c);
                    else if(c == ')') {
                        while (st.peek() != '(') ans += st.pop() + " ";
                        st.pop();
                    } else {
                        while(st.size() > 0 && lvl(st.peek()) >= lvl(c)) ans += st.pop() + " ";
                        st.push(c);
                    }
                }
            }
            while(st.size() > 0) ans += st.pop() + " ";
            System.out.println(ans);
        }
    }
}
