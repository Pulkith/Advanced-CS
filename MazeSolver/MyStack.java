package MazeSolver;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class MyStack implements StackADT {
    private Square[] stack;
    private ArrayList<Square> min,max;
    private int size, index;
    public MyStack() {
        this(256);
    }
    public MyStack(int maxCap) {
        stack =new Square[maxCap];
        min = new ArrayList<>(); max = new ArrayList<>();
        size = maxCap;
        index = -1;
    }
    public void reverse() {
        for(int i = 0; i < index / 2; ++i) {
            Square temp = stack[i];
            stack[i] = stack[index - i];
            stack[index - i] = temp;
        }
    }
    public boolean isEmpty() {
        return index == -1;
    }

    public void clear() {
        while(!isEmpty()) pop();
    }

    public void resize(int newSize) {
        Square[] res =  new Square[newSize];
        for (int j = 0; j < Math.min(newSize, size); ++j) res[j] = stack[j];
        this.stack = res;
        this.size = newSize;
    }
    private void doubleCapacity() { this.resize(this.size * 2);}
    public Square peek() {
        if(this.isEmpty()) throw new EmptyStackException();
        else return stack[index];
    }

    public Square pop() {
        if(this.isEmpty()) throw new EmptyStackException();
        Square element = stack[index];
        stack[index--] = null;
        return element;
    }
    public void push(Square arg) {
        if(index == size - 1) this.doubleCapacity();
        stack[++index] = arg;
    }
    public String toString() {
        String res = "";
        for(int i = index; i >= 0; --i)
            res += stack[i] + "\n";
        return res;
    }
    public Square getMin() {
        if(this.isEmpty()) throw new EmptyStackException();
        return min.get(min.size() - 1);
    }
    public Square getMax() {
        if(this.isEmpty()) throw new EmptyStackException();
        return max.get(max.size() - 1);
    }
    public int size() { return this.index + 1; };
    public int maxSize() { return this.size; }
}
