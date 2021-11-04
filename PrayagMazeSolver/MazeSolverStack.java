package PrayagMazeSolver;

import java.util.*;
import java.io.*;
public class MazeSolverStack extends MazeSolver {

    public MazeSolverStack(Maze maze) {
        super(maze);

    }

    @Override
    public void makeEmpty() {
        stack = new MyStack();

    }

    @Override
    public Square next() {
        return stack.pop();
    }

    Square peekNext() {
        return stack.peek();
    }

    @Override
    public void add(Square s) {
        stack.push(s);

    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public int size() { return stack.size; }
}

