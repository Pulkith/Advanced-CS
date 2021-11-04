package MyStack;

import java.util.*;


public class StackProbs {
    Class<Integer> T = Integer.class;
    public static void main(String[] args) {
        System.out.println(shiftByN(makeStack(new int[]{7, 23, -7, 0, 22, -8, 4, 5}), 3));
        System.out.println(doubleUp(makeStack(new int[]{1})));
        System.out.println(posAndNeg(makeStack(new int[]{-1, 1, 1, -1})));
        System.out.println(reverseVowels("hello how are you"));
        System.out.println(bracketBalance("(([()])))"));
        System.out.println(bracketBalance("([()[]()])()"));

    }
    public static Stack<Integer> makeStack(int[] nums) {
        Stack<Integer> stack = new Stack<Integer>();
        for(int n : nums) stack.push(n);
        return stack;
    }
    public static Stack<Integer> rev(Stack<Integer> s) {
        Stack<Integer> ans = new Stack<>();
        while(!s.empty()) ans.push(s.pop());
        return ans;
    }
    public static Stack<Integer> doubleUp(Stack<Integer> nums) {
        Stack<Integer> temp = new Stack<>();
        while(nums.size() > 0) {
            temp.push(nums.peek());
            temp.push(nums.pop());
        }
        while(temp.size() > 0) {
            nums.push(temp.pop());
        }

        return nums;
    }
    public static Stack<Integer> posAndNeg(Stack<Integer> s) {
        Stack<Integer> neg = new Stack<>(), pos = new Stack<>();
        while(s.size() > 0) {
            if(s.peek() < 0) neg.add(s.pop());
            else pos.add(s.pop());
        }
        while(neg.size() > 0)
            s.push(neg.pop());
        while(pos.size() > 0)
            s.push(pos.pop());
        return s;
    }
    public static Stack<Integer> shiftByN(Stack<Integer> s, int N) {
        N %= s.size(); int size = s.size();
        Stack<Integer> temp = new Stack<>(), ans = new Stack<>();
        for(int i = 0; i < size - N; ++i) temp.add(s.pop());
        while(!temp.isEmpty()) ans.add(temp.pop());
        s = rev(s);
        while(!s.isEmpty()) ans.add(s.pop());
        return ans;
    }
    public static String reverseVowels(String s) {
        Stack<String> elements = new Stack<>();
        for(int it = 0; it < 2; ++it)
            for(int i = 0; i < s.length(); ++i)
                if((""+s.charAt(i)).matches("[aeiouAEIOU]"))
                    if(it == 0)
                        elements.push(""+s.charAt(i));
                    else
                        s = s.substring(0, i) + elements.pop() + s.substring(i+1);
         return s;
    }
    public static boolean bracketBalance(String s) {
        Stack<Character> st = new Stack<>();
        for(char c : s.toCharArray())
            if(c == '(' || c == '[')  st.add(c);
            else{
                if(!st.isEmpty() && ((st.peek() == '(' && c == ')') || (st.peek() == '[' && c == ']' ))) {
                    st.pop(); continue;
                }
                return false;
            }
        return st.isEmpty();
    }
}
