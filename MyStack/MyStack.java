package MyStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EmptyStackException;

public class MyStack<T> {
    private T[] stack;
    private ArrayList<T> min,max;
    private int size, index;
    public MyStack() {
        this(256);
    }
    public MyStack(int maxCap) {
        stack = (T[]) new Object[maxCap];
        min = new ArrayList<>(); max = new ArrayList<>();
        size = maxCap;
        index = -1;
    }
    public void reverse() {
        for(int i = 0; i < index / 2; ++i) {
            T temp = stack[i];
            stack[i] = stack[index - i];
            stack[index - i] = temp;
        }
    }
    public boolean isEmpty() {
        return index == -1;
    }
    public void resize(int newSize) {
        T[] res = (T[]) new Object[newSize];
        for (int j = 0; j < Math.min(newSize, size); ++j) res[j] = stack[j];
        this.stack = res;
        this.size = newSize;
    }
    private void doubleCapacity() { this.resize(this.size * 2);}
    public T peek() {
        if(this.isEmpty()) throw new EmptyStackException();
        else return stack[index];
    }
    public T pop() {
        if(this.isEmpty()) throw new EmptyStackException();
        T element = stack[index];
        if(element instanceof Integer) {
            if(stack[index] == this.getMax()) max.remove(max.size() - 1);
            if(stack[index] == this.getMin()) min.remove(min.size() - 1);
        }
        stack[index--] = null;
        return element;
    }
    public void push(T arg) {
        if(index == size - 1) this.doubleCapacity();
        stack[++index] = arg;
        if(arg instanceof Integer) {
            if(index == 0 || (Integer)stack[index] >= (Integer)this.getMax()) max.add(arg);
            if(index == 0 || (Integer)stack[index] <= (Integer)this.getMin()) min.add(arg);
        }
    }
    public String toString() {
        String res = "";
        for(int i = index; i >= 0; --i)
            res += stack[i] + "\n";
        return res;
    }
    public T getMin() {
        if(this.isEmpty()) throw new EmptyStackException();
        return min.get(min.size() - 1);
    }
    public T getMax() {
        if(this.isEmpty()) throw new EmptyStackException();
        return max.get(max.size() - 1);
    }
    public int size() { return this.index + 1; };
    public int maxSize() { return this.size; }
    public void clear() {

    }

}

